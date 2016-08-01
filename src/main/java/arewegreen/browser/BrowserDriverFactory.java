package arewegreen.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class BrowserDriverFactory {

    WebDriver getDriver() {
        try {
            return new FirefoxDriver();
        } catch (Exception e) {
            throw new RuntimeException("unable to start firefox");
        }
    }

}
