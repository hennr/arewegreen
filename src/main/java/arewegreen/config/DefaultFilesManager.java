package arewegreen.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class DefaultFilesManager implements ApplicationListener<ApplicationReadyEvent> {

    private File arewegreenHome;
    private AreWeGreenProperties areWeGreenProperties;
    private ResourceLoader resourceLoader;
    private static final String layoutJson = "layout.json";
    private static final String applicationProperties = "application.properties";
    private static final String demoScript = "demo.sh";

    @Autowired
    public DefaultFilesManager(Environment environment, AreWeGreenProperties areWeGreenProperties, ResourceLoader resourceLoader) {
        arewegreenHome = new File(environment.getProperty("user.home") + "/arewegreen/");
        this.areWeGreenProperties = areWeGreenProperties;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (areWeGreenProperties.getCreateDefaultConfigFile() && !configExists()) {
            createDefaultConfig();
        }
    }

    boolean configExists() {
        return Files.exists(arewegreenHome.toPath());
    }

    private void createDefaultConfig() {
        try {
            Files.createDirectory(arewegreenHome.toPath());

            // data dir
            Files.createDirectory(Paths.get(getDataDirectoryLocation()));
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/data/" + demoScript).getInputStream(),
                    Paths.get(arewegreenHome + "/data/" + demoScript)
            );

            // properties
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/" + applicationProperties).getInputStream(),
                    Paths.get(arewegreenHome + "/" +  applicationProperties)
            );

            // layout.json
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/" + layoutJson).getInputStream(),
                    Paths.get(getLayoutJsonLocation())
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getDataDirectoryLocation() {
        return arewegreenHome.toString() + "/data";
    }

    public String getLayoutJsonLocation() {
        return arewegreenHome + "/" + layoutJson;
    }
}
