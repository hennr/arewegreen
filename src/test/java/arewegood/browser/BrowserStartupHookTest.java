package arewegood.browser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrowserStartupHookTest.SomeConfig.class)
public class BrowserStartupHookTest {

    @Autowired
    BrowserStartupHook browserStartupHook;

    @Autowired
    BrowserDriverFactory browserDriverFactory;

    @Configuration
    @ComponentScan("arewegood")
    static class SomeConfig {

        @Bean
        BrowserStartupHook browserStartupHook() {
            return new BrowserStartupHook();
        }

        @Bean
        BrowserDriverFactory browserDriverFactory() {
            return mock(BrowserDriverFactory.class);
        }

        // because @PropertySource doesnt work in annotation only land
        @Bean
        PropertyPlaceholderConfigurer propConfig() {
            PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
            ppc.setLocation(new ClassPathResource("application.properties"));
            return ppc;
        }
    }

    @Test
    public void honoursConfigSwitch() {
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        verifyZeroInteractions(browserDriverFactory);
    }

}
