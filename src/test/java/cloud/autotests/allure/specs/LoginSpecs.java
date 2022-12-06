package cloud.autotests.allure.specs;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static api.AuthorizationApi.ALLURE_TESTOPS_SESSION;
import static cloud.autotests.allure.tests.TestBase.loginConfig;
import static helpers.CustomApiListener.withCustomTemplates;

public class LoginSpecs {

    public final static String USERNAME = loginConfig.username();
    public final static String PASSWORD = loginConfig.password();
    public final static String TOKEN = loginConfig.token();

    public static RequestSpecification getRequestSpec() {

        api.AuthorizationApi authorizationApi = new api.AuthorizationApi();
        String xsrfToken = authorizationApi.getXsrfToken(TOKEN);
        String authorizationCookie = authorizationApi
                .getAuthorizationCookie(TOKEN, USERNAME, PASSWORD);

        return RestAssured
                .given()
                .log().all()
                .filter(withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .cookies("XSRF-TOKEN", xsrfToken,
                        ALLURE_TESTOPS_SESSION, authorizationCookie)
                .contentType(ContentType.JSON);

    }

}
