package org.seleniumtest.cucumber.web;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import org.seleniumtest.DriverManager;
import org.seleniumtest.allure.AllureManager;
import org.seleniumtest.allure.screenshot.ScreenshotTaker;

public class CucumberHooks {

    @BeforeAll
    public static void cucumberBeforeAll() {
        DriverManager.getInstance().driverManagerSetup();
    }

    @Before
    public void cucumberBefore(Scenario scenario) {
        Thread.currentThread().setName(scenario.getName().replace(" ", "_"));
    }

    @After
    public void cucumberAfterTest(Scenario scenario) {
        ScreenshotTaker.evaluateScreenshot(DriverManager.getInstance().getDriver(), System.getProperty("screenshot"), scenario.isFailed());
        if (System.getProperty("appendLog").equals("true")){
            AllureManager.fetchLogFile(scenario.getName().replace(" ", "_"));
        }
        DriverManager.getInstance().quitAll();
    }
}
