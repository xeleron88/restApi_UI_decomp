package cloud.autotests.allure.models;

import lombok.Data;

import java.util.List;

@Data
public class TestCaseSteps {
    private List<Step> steps;
}
