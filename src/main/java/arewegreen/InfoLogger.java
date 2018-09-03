package arewegreen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class InfoLogger implements ApplicationListener<ApplicationReadyEvent> {

    private static Logger logger = LoggerFactory.getLogger(InfoLogger.class);

    @Autowired
    Environment environment;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent applicationReadyEvent) {
        logger.info("Running on port: " + environment.getProperty("local.server.port"));
    }
}
