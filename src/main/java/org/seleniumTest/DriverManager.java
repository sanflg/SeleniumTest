package org.seleniumTest;

import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.seleniumTest.utils.AllureManager;
import org.testng.annotations.Parameters;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final DriverManager driverManager = new DriverManager();

    private DriverManager() {
    }

    @Parameters({"driver"})
    public static void setupAll(String driver) {
        WebDriverManager.getInstance(driver.toUpperCase()).setup();
        AllureManager.addEnvironment();
        logger.info("Started WebDriverManager");
    }

    @Parameters({"driver", "maximize"})
    public void setup(String driver, String maximize) {
        WebDriver driverInstance = WebDriverManager.getInstance(driver).create();
        if (Objects.equals(maximize, "yes")) driverInstance.manage().window().maximize();
        driverPool.set(driverInstance);
        logger.info("Started new Chrome Driver");
    }

    public void teardown() {
        driverPool.get().quit();
        driverPool.remove();
        logger.info("Quited Chrome Driver");
    }

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static WebDriver getDriver() {
        return driverPool.get();
    }
}
