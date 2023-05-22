package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.seleniumTest.AllureManager;
import org.seleniumTest.DriverManager;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class CucumberSupport {
    @BeforeAll
    public static void setupAll() {
        DriverManager.setupAll(xmlValue("driver"));
    }

    @Before
    public void setup() {
        DriverManager.getDriverManager().setup(xmlValue("driver"), xmlValue("maximize"));
    }

    // TODO 2 Add allure screenshot on failure with a generic implementation (also for testng execution)
    @After
    public void afterMethod(Scenario scenario) {
        AllureManager.attachScreenshotOnFailure(scenario.isFailed());
        DriverManager.getDriverManager().teardown();
    }

    private static String xmlValue(String key) {
        return System.getProperty(key);
    }
}
