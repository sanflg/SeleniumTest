package org.seleniumtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.seleniumtest.allure.AllureManager;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DriverManager {
    private static final String DEFAULT_DRIVER_NAME = "DEFAULT";
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class);
    private static final DriverManager instance = new DriverManager();

    private final ThreadLocal<Map<String, WebDriver>> webDrivers = ThreadLocal.withInitial(HashMap::new);

    private final String driverType = System.getProperty("driver", "chrome");
    private final String executor = System.getProperty("executor", "manual");
    private final boolean maximize = Boolean.getBoolean(System.getProperty("maximize", "false"));
    private final long timeout = Long.parseLong(System.getProperty("timeout", "10"));

    private WebDriverManager webDriverManager;

    private DriverManager() {
    }

    public void driverManagerSetup() {
        webDriverManager = WebDriverManager.getInstance(driverType.toUpperCase());
        webDriverManager.setup();
        AllureManager.setAllureInfo(executor);
        LOGGER.info("Started WebDriverManager");
    }

    public WebDriver getDriver(String driverName) {
        Map<String, WebDriver> drivers = webDrivers.get();
        WebDriver driver = drivers.get(driverName);
        if (driver == null) {
            LOGGER.error(">>>>>>>>>> GET DRIVER <<<<<<<<<<");
            driver = webDriverManager.create();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(timeout));
            if (maximize) {
                driver.manage().window().maximize();
            }
            drivers.put(driverName, driver);
        }
        return driver;
    }

    public WebDriver getDriver() {
        return getDriver(DEFAULT_DRIVER_NAME);
    }

    public void quitAll() {
        for (WebDriver driver : webDrivers.get().values()) {
            driver.quit();
        }
        webDrivers.get().clear();
        webDrivers.remove();
    }

    public static DriverManager getInstance() {
        return instance;
    }
}
