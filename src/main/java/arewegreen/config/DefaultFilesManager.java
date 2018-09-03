package arewegreen.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class DefaultFilesManager implements ApplicationListener<ApplicationReadyEvent> {

    private Environment environment;
    private ResourceLoader resourceLoader;
    private static final String layoutJson = "layout.json";
    private static final String applicationProperties = "application.properties";
    private static final String demoScript = "demo.sh";

    private static Logger logger = LoggerFactory.getLogger(DefaultFilesManager.class);

    @Autowired
    public DefaultFilesManager(Environment environment, ResourceLoader resourceLoader) {
        this.environment = environment;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (userConfigDoesNotExist()) {
            logger.info("No default config found. Creating.");
            createDefaultConfig();
        } else if (userConfigIsIncomplete()) {
            logger.info("Configuration outdated. Writing updates.");
            complementMissingConfigurationValues();
        } else {
            logger.info("Config is up to date.");
        }
    }

    boolean userConfigIsIncomplete() {
        try {
            return !getUserProperties().keySet().containsAll(getDefaultProperties().keySet());
        } catch (IOException e) {
            return false;
        }
    }

    private void complementMissingConfigurationValues() {
        try {
            Properties defaultProperties = getDefaultProperties();
            Properties userProperties = getUserProperties();
            defaultProperties.putAll(userProperties);

            FileOutputStream writer = new FileOutputStream(getApplicationPropertiesLocation().toString());
            defaultProperties.store(writer, "Updated on:");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Properties getDefaultProperties() throws IOException {
        InputStream defaultConfig = resourceLoader.getResource("classpath:defaultConfig/" + applicationProperties).getInputStream();
        Properties defaultProperties = new Properties();
        defaultProperties.load(defaultConfig);
        return defaultProperties;
    }

    private Properties getUserProperties() throws IOException {
        FileInputStream applicationPropertiesStream = new FileInputStream(getApplicationPropertiesLocation().toString());
        Properties properties = new Properties();
        properties.load(applicationPropertiesStream);
        return properties;
    }

    private boolean userConfigDoesNotExist() {
        return !Files.exists(getApplicationPropertiesLocation());
    }

    private void createDefaultConfig() {
        try {
            Files.createDirectory(getArewegreenHome());

            // data dir
            Files.createDirectory(Paths.get(getDataDirectoryLocation()));
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/data/" + demoScript).getInputStream(),
                    Paths.get(getDataDirectoryLocation() + "/" + demoScript)
            );

            // properties
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/" + applicationProperties).getInputStream(),
                    getApplicationPropertiesLocation()
            );

            // layout.json
            Files.copy(
                    resourceLoader.getResource("classpath:defaultConfig/" + layoutJson).getInputStream(),
                    getLayoutJsonLocation()
            );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Path getArewegreenHome() {
        return Paths.get(environment.getProperty("user.home") + "/arewegreen/");
    }

    private Path getApplicationPropertiesLocation() {
        return Paths.get(getArewegreenHome() + "/" + applicationProperties);
    }

    public String getDataDirectoryLocation() {
        return getArewegreenHome() + "/data";
    }

    public Path getLayoutJsonLocation() {
        return Paths.get(getArewegreenHome() + "/" + layoutJson);
    }
}
