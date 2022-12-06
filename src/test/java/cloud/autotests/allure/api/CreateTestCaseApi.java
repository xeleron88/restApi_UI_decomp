package cloud.autotests.allure.api;

import cloud.autotests.allure.models.CreateTestCaseBody;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;

import static cloud.autotests.allure.specs.LoginSpecs.getRequestSpec;

import static org.hamcrest.CoreMatchers.is;

public class CreateTestCaseApi {
    public static int createTestCase() {
        Faker faker = new Faker();
        String testCaseName = faker.name().nameWithMiddle();

        CreateTestCaseBody createTestCaseBody = new CreateTestCaseBody();
        createTestCaseBody.setName(testCaseName);

        byte[] testCaseBody;
        return RestAssured.given(getRequestSpec())
                .body(createTestCaseBody)
                .queryParam("projectId", "1731")
                .post("/api/rs/testcasetree/leaf")
                .then()
                .log().body()
                .statusCode(200)
                .body("name", is(testCaseName))
                .body("automated", is(false))
                .body("external", is(false))
                .extract()
                .path("id");
    }
}
