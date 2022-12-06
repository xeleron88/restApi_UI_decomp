package cloud.autotests.allure.tests;

import cloud.autotests.allure.api.CreateTestCaseApi;
import cloud.autotests.allure.models.Step;
import cloud.autotests.allure.models.TestCaseSteps;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.AuthorizationApi.ALLURE_TESTOPS_SESSION;
import static cloud.autotests.allure.api.CreateTestCaseStepApi.createTestStep;
import static cloud.autotests.allure.specs.LoginSpecs.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;


import org.openqa.selenium.Cookie;

import java.util.List;

public class AllureTestopsTests extends TestBase {

    private void setCookies() {
        String authorizationCookie = new api.AuthorizationApi()
                .getAuthorizationCookie(TOKEN, USERNAME, PASSWORD);

        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie(ALLURE_TESTOPS_SESSION, authorizationCookie));
    }

    @Test
    @DisplayName("Create test case")
    void createTestCase() {
        TestCaseSteps testCaseSteps = new TestCaseSteps();
        Step stepOne = Step.generateFakeStep();
        Step stepTwo = Step.generateFakeStep();
        Step stepThree = Step.generateFakeStep();
        Step stepFour = Step.generateFakeStep();
        Step stepFive = Step.generateFakeStep();
        testCaseSteps.setSteps(List.of(stepOne, stepTwo, stepThree, stepFour, stepFive));


        int id = step("Create new test case",
                CreateTestCaseApi::createTestCase);

        step("Create Test steps",
                () -> createTestStep(id, testCaseSteps));

        step("Check steps and their values using the UI", () -> {
                    setCookies();
                    List<Step> list = testCaseSteps.getSteps();
                    open("/project/1731/test-cases/" + id);
                    ElementsCollection collection = $$(".TreeElement > li")
                            .shouldHave(CollectionCondition.size(list.size()));

                    for (int i = 0; i < collection.size(); i++) {
                        collection.get(i).$(".StepKeyword").shouldHave(text(list.get(i).getKeyword()));
                        collection.get(i).$(".Multiline").shouldHave(text(list.get(i).getName()));
                    }
                }
        );
    }
}
