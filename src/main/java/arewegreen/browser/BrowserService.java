package arewegreen.browser;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
class BrowserService {

    private static Logger log = LoggerFactory.getLogger(BrowserService.class);

    void openUri(String uri) {
        if (isDesktopSupported()) {
            browseTo(uri);
        } else {
            log.error("Failed to open arewegreen in your browser. Do you have a default browser configured and in your path?");
        }
    }

    private void browseTo(String uri)  {
        try {
            Desktop.getDesktop().browse(new URI(uri));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isDesktopSupported() {
        return Desktop.isDesktopSupported();
    }

}
