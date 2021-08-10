package arewegreen.config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Properties;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.mock.env.MockEnvironment;

public class DefaultFilesManagerTest {

    private MockEnvironment environment = new MockEnvironment();
    private DefaultFilesManager defaultFilesManager = new DefaultFilesManager(environment, new DefaultResourceLoader());

    @TempDir
    Path tempDir;

    @BeforeEach
    public void createNewTemporaryUserHomeFolder() throws IOException {
        environment.setProperty("user.home", tempDir.resolve(UUID.randomUUID().toString()).toAbsolutePath().toString());
    }

    @Test
    public void createsNewConfigIfNoConfigExists() throws IOException {
        defaultFilesManager.onApplicationEvent(new ApplicationReadyEvent(mock(SpringApplication.class), new String[] {}, mock(ConfigurableApplicationContext.class)));

        File applicationProperties = new File(environment.getProperty("user.home") + "/arewegreen/application.properties");

        assertThat(applicationProperties.exists()).isTrue();
        FileInputStream applicationPropertiesStream = new FileInputStream(applicationProperties);
        Properties properties = new Properties();
        properties.load(applicationPropertiesStream);
        assertThat(properties.getProperty("startBrowserAutomatically").isEmpty()).isFalse();
        assertThat(properties.getProperty("scriptTimeoutInSeconds")).isEqualTo("2");
    }

    @Test
    public void doesNothingIfUserConfigIsComplete() {
        // when
        defaultFilesManager.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        assertThat(defaultFilesManager.userConfigIsIncomplete()).isFalse();
    }

    @Test
    public void determinesIncompleteUserConfiguration() throws IOException {
        givenAnIncompleteDefaultConfig();
        // then
        assertThat(defaultFilesManager.userConfigIsIncomplete()).isTrue();
    }

    @Test
    public void updatesUserConfigOnNewDefaultSettings() throws IOException {
        File properties = givenAnIncompleteDefaultConfig();
        givenAnIncompleteDefaultConfig();

        // when
        defaultFilesManager.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // then
        FileInputStream applicationPropertiesStream = new FileInputStream(properties);
        Properties props = new Properties();
        props.load(applicationPropertiesStream);

        assertThat(props.getProperty("scriptTimeoutInSeconds")).isEqualTo("666");
        assertThat(props.getProperty("startBrowserAutomatically")).isEqualTo("false");
    }

    private File givenAnIncompleteDefaultConfig() throws IOException {
        // create fresh config
        defaultFilesManager.onApplicationEvent(mock(ApplicationReadyEvent.class));

        // alter config
        File applicationProperties = new File(environment.getProperty("user.home") + "/arewegreen/application.properties");
        FileInputStream applicationPropertiesStream = new FileInputStream(applicationProperties);
        Properties properties = new Properties();
        properties.load(applicationPropertiesStream);
        // remove one config value
        properties.remove("startBrowserAutomatically");
        // alter one config value
        properties.put("scriptTimeoutInSeconds", "666");
        FileOutputStream writer = new FileOutputStream(applicationProperties);
        properties.store(writer, "");
        writer.close();
        return applicationProperties.getCanonicalFile();
    }
}
