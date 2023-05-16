package org.seleniumTest.cucumber.runner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.seleniumTest.BaseTest;
import org.testng.Reporter;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"classpath:org.seleniumTest"}
)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
    public BaseTest baseTest = new BaseTest();

    @BeforeAll
    static void setupAll() {
        BaseTest.setupAll(retrieveXmlValue("driver"));
    }

    @Before
    public void setup() {
        baseTest.setup(retrieveXmlValue("driver"), retrieveXmlValue("maximize"));
    }

    @After
    public void teardown() {
        baseTest.teardown();
    }

    public static String retrieveXmlValue(String key){
        return Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter(key);
    }
}