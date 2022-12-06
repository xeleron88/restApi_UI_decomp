package cloud.autotests.allure.api;

import cloud.autotests.allure.models.TestCaseSteps;
import io.restassured.RestAssured;

import static cloud.autotests.allure.specs.LoginSpecs.getRequestSpec;
import static org.hamcrest.Matchers.hasSize;

public class CreateTestCaseStepApi {
    public static void createTestStep(int id, TestCaseSteps payload) {
        RestAssured.given(getRequestSpec())
                .body(payload)
                .post(String.format("/api/rs/testcase/%s/scenario", id))
                .then()
                .log().body()
                .statusCode(200)
                .body("steps", hasSize(5));
    }
}
