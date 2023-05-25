package org.seleniumTest;

import java.time.Duration;
import java.util.Objects;

import org.apache.logging.log4j.Level;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.seleniumTest.pageObjects.BasePage;
import org.seleniumTest.utils.AllureLogger;

public class DriverManager {
    //TODO  - Add screenshot by step with var
    private static final AllureLogger LOGGER = new AllureLogger(BasePage.class);
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final DriverManager driverManager = new DriverManager();

    private DriverManager() {
    }

    public static void setupAll(String driver) {
        WebDriverManager.getInstance(driver.toUpperCase()).setup();
        AllureManager.setAllureInfo(System.getProperty("executor"));
        LOGGER.log(Level.INFO, "Started WebDriverManager");
    }

    public void setup(String driver, String maximize) {
        WebDriver driverInstance = WebDriverManager.getInstance(driver).create();
        //Set timeout time for test cases
        driverInstance.manage().timeouts()
                .scriptTimeout(Duration.ofSeconds(Long.parseLong(System.getProperty("timeout"))));

        //Check for driver to maximize
        if (Objects.equals(maximize, "yes")) driverInstance.manage().window().maximize();

        driverPool.set(driverInstance);
        LOGGER.log(Level.INFO,"Started new Chrome Driver");
    }

    public void teardown() {
        driverPool.get().quit();
        driverPool.remove();
        LOGGER.log(Level.INFO,"Quited Chrome Driver");
    }

    public static DriverManager getDriverManager() {
        return driverManager;
    }

    public static WebDriver getDriver() {
        return driverPool.get();
    }
}
