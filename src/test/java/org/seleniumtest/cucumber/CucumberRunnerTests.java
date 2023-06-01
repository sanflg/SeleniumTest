package org.seleniumtest.cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.seleniumtest"},
        plugin = {"pretty",
                "org.seleniumtest.allure.screenshot.StepScreenshotPlugin",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Regression"
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

    public CucumberRunnerTests() {
        Allure.getLifecycle();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}