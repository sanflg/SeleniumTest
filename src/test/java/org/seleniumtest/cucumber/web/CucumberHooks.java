package org.seleniumtest.cucumber.web;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.seleniumtest.DriverManager;
import org.seleniumtest.allure.AllureManager;
import org.seleniumtest.allure.screenshot.ScreenshotTaker;

public class CucumberHooks {
    private static final Logger logger = LogManager.getLogger(CucumberHooks.class);

    @BeforeAll
    public static void cucumberBeforeAll() {
        logger.error(">>>>>>>>>> DRIVER MANAGER SETUP <<<<<<<<<<");
        DriverManager.getInstance().driverManagerSetup();
    }

    @Before
    public void cucumberBefore(Scenario scenario) {
        Thread.currentThread().setName(scenario.getName().replace(" ", "_"));
    }

    @After
    public void cucumberAfterTest(Scenario scenario) {
        ScreenshotTaker.evaluateScreenshot(DriverManager.getInstance().getDriver(), System.getProperty("screenshot"), scenario.isFailed());
        if ("true".equals(System.getProperty("appendLog"))) {
            AllureManager.fetchLogFile(scenario.getName().replace(" ", "_"));
        }
        DriverManager.getInstance().quitAll();
    }
}
