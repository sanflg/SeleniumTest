package org.seleniumTest.cucumber;

import io.cucumber.java.*;

import io.qameta.allure.Allure;
import org.seleniumTest.DriverManager;
import org.seleniumTest.allure.screenshot.ScreenshotTaker;

import java.nio.charset.StandardCharsets;

public class CucumberHooks {

    @BeforeAll
    public static void cucumberBeforeAll(){
        DriverManager.getInstance().driverManagerSetup();
    }

    @After
    public void cucumberAfterTest(Scenario scenario) {
        ScreenshotTaker.evaluateScreenshot(DriverManager.getInstance().getDriver(), System.getProperty("screenshot"), scenario.isFailed());
        String pepe = "<h1>PEPE</h1>";
        Allure.getLifecycle().addAttachment("Log", "text/html","txt", pepe.getBytes(StandardCharsets.UTF_8));
        DriverManager.getInstance().quitAll();
    }
}
