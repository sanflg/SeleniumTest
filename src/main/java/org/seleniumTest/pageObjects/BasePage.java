package org.seleniumTest.pageObjects;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.support.PageFactory;

import org.seleniumTest.DriverManager;

public abstract class BasePage<T extends BasePage<T>> {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected final DriverManager driverManager = DriverManager.getDriverManager();
    protected final URL url;

    public BasePage(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException URL generation for {}", url, e);
            throw new RuntimeException(e);
        }
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public void isCurrentPageBase(){
        assertEquals(driverManager.getDriver().getCurrentUrl(), url.toString());
    }

    @SuppressWarnings({"unchecked"})
    public T goTo(String url){
        driverManager.getDriver().get(url);
        return (T) this;
    }
}