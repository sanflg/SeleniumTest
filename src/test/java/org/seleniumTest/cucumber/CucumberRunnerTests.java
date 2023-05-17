package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

import org.seleniumTest.DriverManager;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"org.seleniumTest"}
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

    @After
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }
}