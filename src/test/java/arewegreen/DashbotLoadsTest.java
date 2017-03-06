package arewegreen;

import static io.restassured.RestAssured.get;

import org.junit.Test;

public class DashbotLoadsTest extends AbstractTestClass {

    @Test
    public void appStarts() {
	// expect
	get("/").then().assertThat().statusCode(200);
    }
}
