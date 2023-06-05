package org.seleniumtest.allure.screenshot;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;

/**
 * Class dedicated to evaluate and take screenshots using the driver
 */
public class ScreenshotTaker {

    private ScreenshotTaker(){}

    /**
     * Method to evaluate if the test is a screenshot candidate at the termination process. In order to manage in step
     * screenshots, refer to StepScreenshotPlugging class.
     *
     * @param driver Driver used to take the screenshot.
     * @param screenshotConfig Declares when to take screenshots for allure
     * @param isFailed If test fails
     * @see StepScreenshotPlugin
     */
    public static void evaluateScreenshot(WebDriver driver, String screenshotConfig, boolean isFailed) {
        if ((screenshotConfig.equalsIgnoreCase("onTestEnd")) ||
                (screenshotConfig.equalsIgnoreCase("onTestFailure")) &&
                        isFailed) {
            takeAndAttachScreenshot(driver);
        }
    }

    /**
     * Cast driver to TakesScreenshot, get a driver screenshot as a byte array and attach it to allure report.
     *
     * @param driver To take te screenshot.
     */
    public static void takeAndAttachScreenshot(WebDriver driver) {
        TakesScreenshot screenshotTaker = (TakesScreenshot) driver;
        ByteArrayInputStream src = new ByteArrayInputStream(screenshotTaker.getScreenshotAs(OutputType.BYTES));
        Allure.getLifecycle().addAttachment(LocalDateTime.now().toString(), "image/png", "png", src);
    }
}
