package arewegreen.browser;

import arewegreen.config.AreWeGreenProperties;
import org.junit.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.core.env.Environment;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class BrowserStartupHookTest {

    AreWeGreenProperties areWeGreenProperties = mock(AreWeGreenProperties.class);

    BrowserService browserServiceMock = mock(BrowserService.class);

    Environment environmentMock = mock(Environment.class);

    BrowserStartupHook browserStartupHook = new BrowserStartupHook(areWeGreenProperties, browserServiceMock, environmentMock);

    @Test
    public void honoursDisabledConfigSwitch() {
        given(areWeGreenProperties.getStartBrowserAutomatically()).willReturn(false);

        //when
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verifyZeroInteractions(browserServiceMock);
    }

    @Test
    public void honoursEnabledConfigSwitch() {
        given(areWeGreenProperties.getStartBrowserAutomatically()).willReturn(true);
        given(environmentMock.getProperty("local.server.port")).willReturn("8080");

        // when
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verify(browserServiceMock).openUri("http://localhost:8080");
    }
}
