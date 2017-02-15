package arewegreen.config;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.env.MockEnvironment;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ConfigManagerTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    ConfigManager configManager;

    @Before
    public void setup() throws IOException {
        AreWeGreenProperties properties = new AreWeGreenProperties();
        properties.setCreateDefaultConfigFile(true);

        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("user.home", temporaryFolder.getRoot().getCanonicalPath());

        configManager = new ConfigManager(environment, properties);
    }

    @Test
    public void doesNothingIfTheAreWeGreenFolderExistsAlready() throws IOException {
        temporaryFolder.newFolder("arewegreen");

        assertTrue(configManager.configExists());
    }

    @Test
    public void createsNewConfigIfNoConfigExists() throws IOException {
        configManager.onApplicationEvent(new ApplicationReadyEvent(mock(SpringApplication.class), new String[]{}, mock(ConfigurableApplicationContext.class)));

        File tempFolder = temporaryFolder.getRoot();
        File applicationProperties = new File(tempFolder.getCanonicalPath() + "/arewegreen/application.properties");

        assertTrue(applicationProperties.exists());
        FileInputStream applicationPropertiesStream = new FileInputStream(applicationProperties);
        Properties properties = new Properties();
        properties.load(applicationPropertiesStream);
        assertThat(properties.getProperty("startBrowserAutomatically"), equalTo("true"));
    }
}
