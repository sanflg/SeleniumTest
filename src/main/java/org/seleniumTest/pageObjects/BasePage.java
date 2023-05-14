package org.seleniumTest.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasePage {
    protected final Logger logger = LogManager.getLogger(this.getClass());
    protected final WebDriver driver;
    protected final URL url;

    public BasePage(WebDriver driver, String url){
        this.driver = driver;

        try { this.url = new URL(url); }
        catch (MalformedURLException e) {
            logger.error("MalformedURLException URL generation for {}", url, e);
            throw new RuntimeException(e);
        }

        driver.get(url);
        PageFactory.initElements(driver, this);
    }

    public boolean isCurrentPage(){
        return driver.getCurrentUrl().equals(url.toString());
    }
}