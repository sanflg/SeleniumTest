package org.seleniumtest.allure.screenshot;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

public class ScreenshotTaker {

    private ScreenshotTaker(){}

    public static void evaluateScreenshot(WebDriver driver, String param, boolean isFailed) {
        if ((param.equalsIgnoreCase("onTestEnd")) ||
                (param.equalsIgnoreCase("onTestFailure")) &&
                        isFailed) {
            takeAndAttachScreenshot(driver);
        }
    }

    public static void takeAndAttachScreenshot(WebDriver driver) {
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        ByteArrayInputStream src = new ByteArrayInputStream(screenshotTaker.getScreenshotAs(OutputType.BYTES));
        Allure.getLifecycle().addAttachment(LocalDateTime.now().toString(), "image/png", "png", src);
    }
}
