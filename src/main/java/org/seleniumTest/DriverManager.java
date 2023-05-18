package org.seleniumTest;

import java.util.List;
import java.util.Objects;

import com.beust.jcommander.Parameter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.Parameters;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final  ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final DriverManager driverManager = new DriverManager();
    // TODO fix this by before or background
    private static final String driverName = "chrome";
    private static final String maximize = "yes";

    private DriverManager() {
    }

    public static void setupAll() {
        WebDriverManager.getInstance(driverName.toUpperCase()).setup();
        logger.info("Started WebDriverManager");
    }

    public void setup() {
        WebDriver driver = WebDriverManager.getInstance(driverName).create();
        if (Objects.equals(maximize, "yes")) driver.manage().window().maximize();
        driverPool.set(driver);
        logger.info("Started new Chrome Driver");
    }

    public void teardown() {
        driverPool.get().quit();
        logger.info("Quited Chrome Driver");
    }

    public static WebElement findWebElement(By by) {
        return getDriver().findElement(by);
    }

    public static List<WebElement> findWebElements(By by) {
        return getDriver().findElements(by);
    }

    private static String xmlValue(String key) {
        return Reporter.getCurrentTestResult()
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter(key);
    }

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static WebDriver getDriver() {
        return driverPool.get();
    }


}
