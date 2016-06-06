package arewegreen;

import arewegreen.browser.BrowserDriverFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(ignoreResourceNotFound = true, value = "file:${user.home}/arewegreen/application.properties")
public class Application {

    @Bean
    BrowserDriverFactory browserDriverFactory() {
        return new BrowserDriverFactory();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
