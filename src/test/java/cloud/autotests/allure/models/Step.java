package cloud.autotests.allure.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.javafaker.Faker;
import lombok.Data;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Step {
    @Nonnull
    private String name;
    private ArrayList<Object> attachments;
    private ArrayList<Object> steps;
    private boolean leaf;
    private int stepsCount;
    private boolean hasContent;
    @Nonnull
    private String keyword;
    private String spacing;

    public static Step generateFakeStep() {
        Faker faker = new Faker();
        String testStepName = faker.funnyName().name();
        String testStepKeyWord = faker.artist().name();
        return new Step(testStepName, testStepKeyWord);

    }
}
