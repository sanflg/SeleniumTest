package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import org.seleniumTest.DriverManager;
import org.seleniumTest.utils.AllureManager;

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
    public void teardown() {
        DriverManager.getDriverManager().teardown();
    }

    private static String xmlValue(String key) {
        return System.getProperty(key);
    }
}
