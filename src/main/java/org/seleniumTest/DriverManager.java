package org.seleniumTest;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.seleniumTest.allure.AllureManager;
import org.seleniumTest.utils.PageUtils;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(PageUtils.class);
    protected static final String DEFAULT_DRIVER_NAME = "DEFAULT";
    private static final DriverManager instance = new DriverManager();
    private final ThreadLocal<Map<String,WebDriver>> webDrivers = ThreadLocal.withInitial(HashMap::new);
    private final String driverType = System.getProperty("driver");
    private WebDriverManager webDriverManager;

    private DriverManager() {
    }

    public void driverManagerSetup() {
        webDriverManager = WebDriverManager.getInstance(driverType.toUpperCase());
        webDriverManager.setup();
        AllureManager.setAllureInfo("jenkins");
        logger.info("Started WebDriverManager");
    }

    public WebDriver getDriver(String driverName){
        Map<String,WebDriver> drivers = webDrivers.get();
        WebDriver driver = drivers.get(driverName);
        if (driver == null){
            driver = webDriverManager.create();
            drivers.put(driverName, driver);
        }
        return driver;
    }

    public WebDriver getDriver(){
        return getDriver(DEFAULT_DRIVER_NAME);
    }

    public void quitAll(){
        for (WebDriver driver : webDrivers.get().values()){
            driver.quit();
        }
        webDrivers.get().clear();
    }

    public static DriverManager getInstance(){
        return instance;
    }
}
