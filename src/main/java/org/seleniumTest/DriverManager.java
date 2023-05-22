package org.seleniumTest;

import java.time.Duration;
import java.util.Objects;

import org.openqa.selenium.WebDriver;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverManager {
    //TODO 	- Try Jenkins with allure plugins
    //TODO	- Implement issue administrator system
    private static final Logger logger = LogManager.getLogger(DriverManager.class);
    private static final ThreadLocal<WebDriver> driverPool = new ThreadLocal<>();
    private static final DriverManager driverManager = new DriverManager();

    private DriverManager() {
    }

    public static void setupAll(String driver) {
        WebDriverManager.getInstance(driver.toUpperCase()).setup();
        AllureManager.setAllureInfo(System.getProperty("executor"));
        logger.info("Started WebDriverManager");
    }

    public void setup(String driver, String maximize) {
        WebDriver driverInstance = WebDriverManager.getInstance(driver).create();
        //Set timeout time for test cases
        driverInstance.manage().timeouts()
                .scriptTimeout(Duration.ofSeconds(Long.parseLong(System.getProperty("timeout"))));

        //Check for driver to maximize
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
