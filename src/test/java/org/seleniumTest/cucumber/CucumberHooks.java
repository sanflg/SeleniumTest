package org.seleniumTest.cucumber;

import io.cucumber.java.*;

import io.qameta.allure.Allure;
import org.seleniumTest.DriverManager;
import org.seleniumTest.allure.AllureManager;
import org.seleniumTest.allure.screenshot.ScreenshotTaker;

import java.nio.charset.StandardCharsets;

public class CucumberHooks {

    @BeforeAll
    public static void cucumberBeforeAll(){
        DriverManager.getInstance().driverManagerSetup();
    }

    @Before
    public void cucumberBefore(Scenario scenario){
        Thread.currentThread().setName(scenario.getName().replace(" ", "_"));
    }

    @After
    public void cucumberAfterTest(Scenario scenario) {
        ScreenshotTaker.evaluateScreenshot(DriverManager.getInstance().getDriver(), System.getProperty("screenshot"), scenario.isFailed());
        AllureManager.fetchLogFile(scenario.getName().replace(" ", "_"));
        DriverManager.getInstance().quitAll();
    }
}
