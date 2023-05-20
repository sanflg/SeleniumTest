package org.seleniumTest.testng;

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
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }
}
