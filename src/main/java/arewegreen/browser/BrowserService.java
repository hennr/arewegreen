package arewegreen.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.net.URI;

@Service
class BrowserService {

    private static Logger log = LoggerFactory.getLogger(BrowserService.class);

    void openUri(String uri) {
        try {
            Desktop.getDesktop().browse(new URI(uri));
        } catch (Throwable e) {
            log.error("Failed to open arewegreen in your browser. Do you have a default browser configured and in your path?");
        }
    }
}
