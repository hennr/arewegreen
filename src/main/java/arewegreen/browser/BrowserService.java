package arewegreen.browser;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
class BrowserService {

    void openUri(String uri) {
        if (isDesktopSupported()) {
            browseTo(uri);
        }
    }

    private void browseTo(String uri)  {
        try {
            Desktop.getDesktop().browse(new URI(uri));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    boolean isDesktopSupported() {
        return Desktop.isDesktopSupported();
    }

}
