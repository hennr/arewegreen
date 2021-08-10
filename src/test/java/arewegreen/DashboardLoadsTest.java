package arewegreen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import arewegreen.staticContent.CustomWebFilter;

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
