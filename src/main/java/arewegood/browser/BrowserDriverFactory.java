package arewegood.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.stereotype.Service;

@Service
public class BrowserDriverFactory {

    WebDriver getDriver() {
        return new FirefoxDriver();
    }

}
