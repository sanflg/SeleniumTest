package org.seleniumTest.pageObjects;

import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Step;
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

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void isCurrentPage() {
        assertEquals(driverManager.getDriver().getCurrentUrl(), url.toString());
    }

    @SuppressWarnings({"unchecked"})
    @Step("Go to the desired url by page instantiation.")
    public T goTo() {
        driverManager.getDriver().get(url.toString());
        return (T) this;
    }
}