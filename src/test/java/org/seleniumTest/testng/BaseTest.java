package org.seleniumTest.testng;

import org.testng.ITestListener;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;

import org.seleniumTest.DriverManager;
public class BaseTest implements ITestListener {

    @BeforeSuite
    public static void setupAll() {
        DriverManager.setupAll();
    }

    @BeforeMethod
    public void setup() {
        DriverManager.getDriverManager().setup();
    }

    @AfterMethod
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }
}
