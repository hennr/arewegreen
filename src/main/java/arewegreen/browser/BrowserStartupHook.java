package arewegreen.browser;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class BrowserStartupHook implements ApplicationListener<ApplicationReadyEvent> {

    @Value("${startBrowserAutomatically}")
    String startAutomatically;

    @Autowired
    private BrowserDriverFactory driverFactory;

    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (Boolean.parseBoolean(startAutomatically)) {
            openBrowser();
        }
    }

    private void openBrowser() {
        String port = environment.getProperty("local.server.port");
        WebDriver webDriver = driverFactory.getDriver();
        // TODO create and use the same profile again on each start to get the images cached
        webDriver.manage().window().maximize();
        webDriver.get("http://localhost:" + port);
        // dashbot needs to be reloaded to work properly in firefox...
        webDriver.get("http://localhost:" + port);
    }

}
