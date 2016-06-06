package arewegreen;

import static com.jayway.restassured.RestAssured.get;

import org.junit.Test;

public class DashbotLoadsTest extends AbstractRestTestClass {

    @Test
    public void appStarts() {
	// expect
	get("/").then().assertThat().statusCode(200);
    }
}
