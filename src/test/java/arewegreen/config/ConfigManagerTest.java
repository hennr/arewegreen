package arewegreen.config;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConfigManagerTest.AvoidStartupOfTheRealApplicationContextToAvoidActionsTakenInOtherBeansOnStartup.class)
public class ConfigManagerTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Autowired
    ConfigManager configManager;

    @Before
    public void setup() throws IOException {
        MockEnvironment environment = new MockEnvironment();
        environment.setProperty("user.home", folder.getRoot().getCanonicalPath());
        configManager.environment = environment;
    }

    @Test
    public void doesNothingIfTheConfigExistsAlready() throws IOException {
        folder.newFolder("arewegreen");
        folder.newFile("arewegreen/application.properties");

        assertTrue(configManager.configExists());
    }

    @Test
    public void createsNewConfigIfNoConfigExists() throws IOException {
        configManager.onApplicationEvent(new ApplicationReadyEvent(mock(SpringApplication.class), new String[]{}, mock(ConfigurableApplicationContext.class)));

        File tempFolder = folder.getRoot();
        File applicationProperties = new File(tempFolder.getCanonicalPath() + "/arewegreen/application.properties");

        assertTrue(applicationProperties.exists());
        // TODO check property values
    }

    static class AvoidStartupOfTheRealApplicationContextToAvoidActionsTakenInOtherBeansOnStartup {
        @Bean
        ConfigManager configManager() {
            return new ConfigManager();
        }
    }
}
