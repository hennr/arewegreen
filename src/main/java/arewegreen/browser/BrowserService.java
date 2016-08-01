package arewegreen.browser;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.net.URI;

@Service
class BrowserService {

    void openUri(String uri) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(uri));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
