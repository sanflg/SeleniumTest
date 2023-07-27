package org.seleniumtest.web.pageobjects.google;

import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage<T extends BasePage<T>> {
    protected static final String DOMAIN = "https://www.google.com.ar/";
    protected static final Logger LOGGER = LogManager.getLogger(BasePage.class);
    protected final WebDriver driver;
    protected URL url = null;

    protected BasePage(WebDriver driver, String url) {
        this.driver = driver;
        try {
            this.url = new URI(url).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            LOGGER.error(String.format("Invalid URL <%s>", url), e);
        }
        PageFactory.initElements(driver, this);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage() {
        assertEquals(driver.getCurrentUrl(), DOMAIN);
    }

    @Step("Go to the desired url by page.")
    @SuppressWarnings({"unchecked"})
    public T goTo() {
        driver.get(url.toString());
        return (T) this;
    }
}