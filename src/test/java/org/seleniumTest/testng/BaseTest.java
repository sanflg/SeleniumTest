package org.seleniumTest.testng;

import io.cucumber.java.Scenario;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.seleniumTest.AllureManager;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.seleniumTest.DriverManager;
import org.testng.annotations.Parameters;

public class BaseTest {

    @BeforeSuite
    @Parameters({"driver"})
    public static void setupAll(String driver) {
        DriverManager.setupAll(driver);
    }

    @BeforeMethod
    @Parameters({"driver", "maximize"})
    public void setup(String driver, String maximize) {
        DriverManager.getDriverManager().setup(driver, maximize);
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        AllureManager.attachScreenshotOnFailure(result.getStatus()!=1);
        DriverManager.getDriverManager().teardown();
    }
}
