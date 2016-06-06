package arewegreen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties
public class AreWeGreenProperties {

    private boolean startBrowserAutomatically = true;

    public boolean getStartBrowserAutomatically() {
        return startBrowserAutomatically;
    }

    public void setStartBrowserAutomatically(boolean startBrowserAutomatically) {
        this.startBrowserAutomatically = startBrowserAutomatically;
    }
}
