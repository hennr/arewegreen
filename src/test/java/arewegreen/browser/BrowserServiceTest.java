package arewegreen.browser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Desktop.class, BrowserService.class})
public class BrowserServiceTest {
    
    @Test
    public void openBrowserIfSupported() throws URISyntaxException, IOException {
        Desktop desktop = givenDesktop();
        
        // when:
        new BrowserService().openUri(exampleUri());
        
        // then:
        verify(desktop).browse(new URI(exampleUri()));
    }

    private String exampleUri() {
        return "http://www.example.org";
    }

    private Desktop givenDesktop() {
        mockStatic(Desktop.class);

        Desktop desktop = mock(Desktop.class);
        
        Mockito.when(Desktop.getDesktop()).thenReturn(desktop);
        Mockito.when(Desktop.isDesktopSupported()).thenReturn(true);
        return desktop;
    }
}
