package arewegreen.browser;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.mock.env.MockEnvironment;

import arewegreen.config.AreWeGreenProperties;

public class BrowserStartupHookTest {

    private AreWeGreenProperties areWeGreenProperties = mock(AreWeGreenProperties.class);
    private BrowserService browserServiceMock = mock(BrowserService.class);
    private MockEnvironment environmentMock = new MockEnvironment();
    private BrowserStartupHook browserStartupHook = new BrowserStartupHook(areWeGreenProperties, browserServiceMock, environmentMock);

    @Test
    public void honoursDisabledConfigSwitch() {
        given(areWeGreenProperties.getStartBrowserAutomatically()).willReturn(false);

        //when
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verifyNoInteractions(browserServiceMock);
    }

    @Test
    public void honoursEnabledConfigSwitch() {
        given(areWeGreenProperties.getStartBrowserAutomatically()).willReturn(true);
        environmentMock.setProperty("local.server.port", "8080");

        // when
        browserStartupHook.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        verify(browserServiceMock).openUri("http://localhost:8080");
    }
}
