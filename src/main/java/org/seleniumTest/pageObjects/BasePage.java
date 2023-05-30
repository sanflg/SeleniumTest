package org.seleniumTest.pageObjects;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.Level;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.seleniumTest.DriverManager;
import org.seleniumTest.allure.AllureLogger;

public abstract class BasePage<T extends BasePage<T>> {
    //TODO 3 Check if there is allure logger to avoid duplicate login calls (log4j and allure)
//    protected static final AllureLogger LOGGER = new AllureLogger(BasePage.class);
    protected final static String domain = "https://www.google.com.ar/";
    protected final WebDriver driver;
    protected final URL url;

    //TODO 4 There is a way to generify parameters declarations in testng.xml files?
    //TODO 3 Fix pom failing to resolve dependencies
    //TODO Add driver on constructor
    public BasePage(WebDriver driver, String url) {
        try {
            this.driver = driver;
            this.url = new URL(url);
        } catch (MalformedURLException e) {
//            LOGGER.log(Level.ERROR, String.format("MalformedURLException URL generation for <%s>", url));
            throw new RuntimeException(e);
        }
        PageFactory.initElements(driver, this);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage() {
        assertEquals(driver.getCurrentUrl(), domain);
    }

    @Step("Go to the desired url by page.")
    @SuppressWarnings({"unchecked"})
    public T goTo() {
        driver.get(url.toString());
        return (T) this;
    }
}