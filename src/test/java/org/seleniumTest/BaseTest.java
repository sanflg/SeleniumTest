package org.seleniumTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest{
    protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;

    @BeforeSuite
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
        logger.info("Started WebDriverManager");
    }

    @BeforeTest
    void setup() {
        driver = new ChromeDriver();
        logger.info("Started new Chrome Driver");
    }

    @AfterTest
    void teardown() {
        driver.quit();
        logger.info("Quited Chrome Driver");
    }
}
