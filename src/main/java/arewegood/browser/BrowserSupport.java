package arewegood.browser;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class BrowserSupport implements ApplicationListener<ApplicationReadyEvent> {

    private void openBrowser() {
        // TODO create and use the same profile again on each start to get the images cached
        FirefoxDriver firefoxDriver = new FirefoxDriver();
        firefoxDriver.manage().window().maximize();
        firefoxDriver.get("http://localhost:9000");
        // dashbot needs to be reloaded to work properly in firefox...
        firefoxDriver.get("http://localhost:9000");
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        openBrowser();
    }
}
