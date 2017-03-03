package arewegreen;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "user.home=${java.io.tmpdir}")
public class AbstractRestTestClass {

    private static Logger log = LoggerFactory.getLogger(AbstractRestTestClass.class);

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @After
    public void cleanUp() {
        File tempDir = new File(System.getProperty("java.io.tmpdir") + "/arewegreen/");
        try {
            String newPath = tempDir + " - " + new Date();
            log.info("Archiving temporary arewegreen home folder at: " + newPath);
            Files.move(tempDir.toPath(), Paths.get(newPath));
        } catch (Exception e) {
            log.warn("Unable to archive arewegreen temporary home dir" + tempDir + " after test");
        }
    }
}
