package org.seleniumTest.pageObjects;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import org.seleniumTest.DriverManager;

public abstract class BasePage implements BaseBehaviors<BasePage> {
    //TODO Check if there is allure logger to avoid duplicate login calls (log4j and allure)
    protected final static Logger logger = LogManager.getLogger(BasePage.class);
    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected final URL url;

    //TODO There is a way to generify parameters declarations in testng.xml files?
    //TODO Generate profiles to run testng.xml, cucumber.xml and testng.xml + cucumber.xml
    //TODO Fix pom failing to resolve dependencies
    public BasePage(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException URL generation for {}", url, e);
            throw new RuntimeException(e);
        }
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public void assertIsCurrentPageBase(){
        assertEquals(driverManager.getDriver().getCurrentUrl(), url.toString());
    }

    public void goTo(String url){
        driverManager.getDriver().get(url);
    }
}