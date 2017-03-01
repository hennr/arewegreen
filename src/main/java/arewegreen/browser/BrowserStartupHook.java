package arewegreen.browser;

import arewegreen.config.AreWeGreenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class BrowserStartupHook implements ApplicationListener<ApplicationReadyEvent> {

    private AreWeGreenProperties areWeGreenProperties;

    private BrowserService browserService;

    private Environment environment;

    @Autowired
    public BrowserStartupHook(AreWeGreenProperties areWeGreenProperties,
                              BrowserService browserService,
                              Environment environment) {
        this.areWeGreenProperties = areWeGreenProperties;
        this.browserService = browserService;
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (areWeGreenProperties.getStartBrowserAutomatically()) {
            browserService.openUri("http://localhost:" + port());
        }
    }

    private String port() {
        return environment.getProperty("local.server.port");
    }

}
