package arewegreen;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "user.home=${java.io.tmpdir}")
public class AbstractTestClass {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void checkIfConfigurationIsGenerated() throws IOException {
        File configurationFile = new File("/tmp/arewegreen/application.properties");
        assertTrue(configurationFile.exists());

        FileInputStream applicationPropertiesStream = new FileInputStream(configurationFile);
        Properties properties = new Properties();
        properties.load(applicationPropertiesStream);

        assertThat(properties.getProperty("startBrowserAutomatically").isEmpty(), is(false));
        assertThat(properties.getProperty("scriptTimeoutInSeconds").isEmpty(), is(false));

    }
}
