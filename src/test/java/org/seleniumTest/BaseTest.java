package org.seleniumTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.util.Objects;

public class BaseTest implements ITestListener {
    public static final Logger logger = LogManager.getLogger(BaseTest.class);
    public WebDriver driver;

    @BeforeSuite
    @Parameters({ "driver"})
    public static void setupAll(String driverName) {
        WebDriverManager.getInstance(driverName.toUpperCase()).setup();
        logger.info("Started WebDriverManager");
    }

    @BeforeTest
    @Parameters({ "driver", "maximize" })
    public void setup(String driverName, @Optional String maximize) {
        driver = WebDriverManager.getInstance(driverName).create();

        if (Objects.equals(maximize, "yes")) driver.manage().window().maximize();

        logger.info("Started new Chrome Driver");
    }

    @AfterTest
    public void teardown() {
        driver.quit();
        logger.info("Quited Chrome Driver");
    }
}
