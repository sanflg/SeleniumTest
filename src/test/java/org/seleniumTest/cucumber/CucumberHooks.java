package org.seleniumTest.cucumber;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.seleniumTest.AllureManager;
import org.seleniumTest.DriverManager;

public class CucumberHooks {

    @BeforeAll
    public static void setupAll() {
        DriverManager.setupAll(xmlValue("driver"));
    }

    @Before
    public void setup() {
        DriverManager.getDriverManager().setup(xmlValue("driver"), xmlValue("maximize"));
    }

    @After
    public void afterMethod(Scenario scenario) {
        AllureManager.attachScreenshotOnFailure(scenario.isFailed());
        DriverManager.getDriverManager().teardown();
    }

    private static String xmlValue(String key) {
        return System.getProperty(key);
    }
}
