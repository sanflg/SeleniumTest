package org.seleniumtest.cucumber.web;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.qameta.allure.Allure;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

@Test
@CucumberOptions(
        features = {"src/test/resources/features/web"},
        glue = {"org.seleniumtest.cucumber.web"},
        plugin = {"pretty",
                "org.seleniumtest.allure.screenshot.StepScreenshotPlugin",
                "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Regression"
)
public class CucumberWebRunner extends AbstractTestNGCucumberTests {

    public CucumberWebRunner() {
        Allure.getLifecycle();
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}