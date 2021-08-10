package arewegreen;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = NONE, properties = "user.home=${java.io.tmpdir}")
public class AbstractTestClass {
    // Avoids using the users home dir for while running test but use java.io.tmpdir instead.
}
