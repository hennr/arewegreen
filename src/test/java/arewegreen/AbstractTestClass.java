package arewegreen;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE, properties = "user.home=${java.io.tmpdir}")

public class AbstractTestClass {
    // Avoids using the users home dir for while running test but use java.io.tmpdir instead.
}
