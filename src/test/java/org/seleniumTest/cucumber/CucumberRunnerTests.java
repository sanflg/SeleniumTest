package org.seleniumTest.cucumber;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

//TODO Avoid repetition of cucumber scenarios in allure report
//TODO Learn more about allure implementations for cucumber
//TODO Allure categories, environment,
@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.seleniumTest"},
        plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Regression"
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    //TODO More test cases for other suite

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}