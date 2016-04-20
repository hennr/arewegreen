package arewegood.browser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrowserStartupHookTest.SomeConfig.class)
public class BrowserStartupHookTest {

    @Autowired
    BrowserStartupHook browserStartupHook;

    @Autowired
    BrowserDriverFactory browserDriverFactory;

    static BrowserDriverFactory browserDriverFactoryMock = mock(BrowserDriverFactory.class);

    static FirefoxDriver firefoxDriverMock = mock(FirefoxDriver.class);

    @Configuration
    @ComponentScan("arewegood")
    @PropertySource(value = "application.properties")
    static class SomeConfig {

        @Bean
        BrowserStartupHook browserStartupHook() {
            return new BrowserStartupHook();
        }

        @Bean
        BrowserDriverFactory browserDriverFactory() {
            return browserDriverFactoryMock;
        }
    }

    @Test
    public void honoursDisabledConfigSwitch() {
        //when
        browserStartupHook.startAutomatically = "false";
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verifyZeroInteractions(browserDriverFactory);
    }

    @Test
    public void honoursEnabledConfigSwitch() {
        // given
        when(browserDriverFactoryMock.getDriver()).thenReturn(firefoxDriverMock);
        WebDriver.Options options = mock(WebDriver.Options.class);
        when(options.window()).thenReturn(mock(WebDriver.Window.class));
        when(firefoxDriverMock.manage()).thenReturn(options);

        // when
        browserStartupHook.startAutomatically = "true";
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verify(browserDriverFactory).getDriver();
    }
}
