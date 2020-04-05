package arewegreen;

import arewegreen.staticContent.CustomWebFilter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@WebFluxTest(value = {Application.class, CustomWebFilter.class}, properties = "user.home=${java.io.tmpdir}")
public class DashboardLoadsTest {

    @Autowired
    private WebTestClient webClient;

    @Test
    public void appStarts() {
        // expect
        webClient.get().uri("/").exchange().expectStatus().is2xxSuccessful();
    }
}
