package org.seleniumTest.pageObjects;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.seleniumTest.DriverManager;

public abstract class BasePage<T extends BasePage<T>> {
    //TODO 3 Check if there is allure logger to avoid duplicate login calls (log4j and allure)
    protected final static Logger logger = LogManager.getLogger(BasePage.class);
    protected final static String domain = "https://www.google.com.ar/";
    protected final WebDriver driver;
    protected final URL url;

    //TODO 4 There is a way to generify parameters declarations in testng.xml files?
    //TODO 4 generify profiles declaration in pom
    //TODO 3 Fix pom failing to resolve dependencies
    public BasePage(String url) {
        try {
            this.driver = DriverManager.getDriver();
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException URL generation for {}", url, e);
            throw new RuntimeException(e);
        }
        PageFactory.initElements(DriverManager.getDriver(), this);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage() {
        assertEquals(DriverManager.getDriver().getCurrentUrl(), domain);
    }

    @Step("Go to the desired url by page.")
    @SuppressWarnings({"unchecked"})
    public T goTo() {
        DriverManager.getDriver().get(domain);
        return (T) this;
    }
}