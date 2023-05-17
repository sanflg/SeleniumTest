package org.seleniumTest;

import java.util.Objects;

import org.testng.Reporter;
import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final  ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final DriverManager driverManager = new DriverManager();
    private static final String driverName = xmlValue("driver");
    private static final String maximize = xmlValue("maximize");

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

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public WebDriver getDriver() {
        return driverPool.get();
    }

    private static String xmlValue(String key) {
        return Reporter.getCurrentTestResult()
                .getTestContext()
                .getCurrentXmlTest()
                .getParameter(key);
    }
}
