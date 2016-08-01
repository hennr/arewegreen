package arewegreen.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.NotConnectedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import arewegreen.config.AreWeGreenProperties;

@Component
class BrowserStartupHook implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger log = LoggerFactory.getLogger(BrowserStartupHook.class);

    @Autowired
    AreWeGreenProperties configuration;

    @Autowired
    private BrowserDriverFactory driverFactory;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (configuration.getStartBrowserAutomatically()) {
            openBrowser();
        }
    }

    private void openBrowser() {
        try {
            String port = environment.getProperty("local.server.port");
            WebDriver webDriver = driverFactory.getDriver();
            // TODO create and use the same profile again on each start to get the images cached
            webDriver.manage().window().maximize();
            webDriver.get("http://localhost:" + port);
            // dashbot needs to be reloaded to work properly in firefox...
            webDriver.get("http://localhost:" + port);
        } catch (RuntimeException e) {
            log.error("Failed to start browser automatically. Check for firefox in your path or for arewegreen updates.");
        }
    }

}
