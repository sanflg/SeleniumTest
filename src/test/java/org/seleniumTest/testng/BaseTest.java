package org.seleniumTest.testng;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class BaseTest{
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;

    @BeforeSuite
    @Parameters({ "driver" })
    static void setupAll(String driverName) {
        System.out.println(driverName);
        System.out.println(WebDriverManager.getInstance(driverName).getDriverManagerType().toString());
        WebDriverManager.getInstance(driverName).setup();
        logger.info("Started WebDriverManager");
    }

    @BeforeTest
    @Parameters({ "driver" })
    public void setup(String driverName) {
        driver = WebDriverManager.getInstance(driverName).create();
        logger.info("Started new Chrome Driver");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
        logger.info("Quited Chrome Driver");
    }
}
