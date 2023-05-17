package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import org.seleniumTest.DriverManager;
import org.testng.annotations.Test;

//TODO Avoid repetition of cucumber scenarios in allure report
//TODO Learn more about allure implementations for cucumber
//TODO Allure categories, environment,
@Test
@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.seleniumTest"},
        plugin = {"pretty","io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
        tags = "@Regression"
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    @BeforeAll
    public static void setupAll() {
        DriverManager.setupAll();
    }

    @Before
    public void setup() {
        DriverManager.getDriverManager().setup();
    }

    //TODO Add allure screenshot on failure with a generic implementation (also for testng execution)
    @After
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }
}