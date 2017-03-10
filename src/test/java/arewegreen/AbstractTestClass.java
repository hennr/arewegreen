package arewegreen;

import io.restassured.RestAssured;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, properties = "user.home=${java.io.tmpdir}")
public class AbstractTestClass {

    private static Logger log = LoggerFactory.getLogger(AbstractTestClass.class);

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @AfterClass
    public static void cleanUp() {
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
