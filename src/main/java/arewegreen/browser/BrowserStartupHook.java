package arewegreen.browser;

import arewegreen.config.AreWeGreenProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
class BrowserStartupHook implements ApplicationListener<ApplicationReadyEvent> {

    private AreWeGreenProperties configuration;

    private BrowserService browserService;

    private Environment environment;

    @Autowired
    public BrowserStartupHook(AreWeGreenProperties configuration,
                              BrowserService browserService,
                              Environment environment) {
        this.configuration = configuration;
        this.browserService = browserService;
        this.environment = environment;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (configuration.getStartBrowserAutomatically()) {
            browserService.openUri("http://localhost:" + port());
        }
    }

    private String port() {
        return environment.getProperty("local.server.port");
    }

}
