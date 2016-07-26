package arewegreen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
class ConfigManager implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    Environment environment;

    @Autowired
    AreWeGreenProperties properties;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (properties.getCreateDefaultConfigFile() && !configExists()) {
            try {
                File arewegreenHome = new File(environment.getProperty("user.home") + "/arewegreen");
                File arewegreenApplicationProperties = new File(arewegreenHome.getCanonicalPath() + "/application.properties");

                Files.createDirectory(arewegreenHome.toPath());
                Files.createFile(arewegreenApplicationProperties.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    boolean configExists() {
        String userHome = environment.getProperty("user.home");
        File areWeGreenConfigFolder = new File(userHome + "/arewegreen/application.properties");

        return areWeGreenConfigFolder.exists();
    }

    private void createDefaultConfig() {

    }
}
