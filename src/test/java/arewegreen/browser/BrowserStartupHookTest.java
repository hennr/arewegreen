package arewegreen.browser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import arewegreen.config.AreWeGreenProperties;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BrowserStartupHookTest.SomeConfig.class)
public class BrowserStartupHookTest {

    @Autowired
    AreWeGreenProperties areWeGreenProperties;

    @Autowired
    BrowserStartupHook browserStartupHook;

    static BrowserDriverFactory browserDriverFactoryMock = mock(BrowserDriverFactory.class);

    @ComponentScan("arewegreen")
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
        areWeGreenProperties.setStartBrowserAutomatically(false);
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verifyZeroInteractions(browserDriverFactoryMock);
    }

    @Test
    public void honoursEnabledConfigSwitch() {
        // given
        FirefoxDriver firefoxDriverMock = mock(FirefoxDriver.class);
        when(browserDriverFactoryMock.getDriver()).thenReturn(firefoxDriverMock);
        WebDriver.Options options = mock(WebDriver.Options.class);
        when(options.window()).thenReturn(mock(WebDriver.Window.class));
        when(firefoxDriverMock.manage()).thenReturn(options);

        // when
        areWeGreenProperties.setStartBrowserAutomatically(true);
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verify(browserDriverFactoryMock).getDriver();
    }
}
