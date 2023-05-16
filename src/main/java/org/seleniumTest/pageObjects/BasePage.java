package org.seleniumTest.pageObjects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BasePage <T extends BasePage<T>> {
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
        PageFactory.initElements(driver, this);
    }

    public boolean isCurrentPage(){
        return driver.getCurrentUrl().equals(url.toString());
    }

    /**
     * Move to the desired url declared by page instantiation, resolved as a generic of BasePage<T extends BasePage<T>>.
     * Removed driver.get() from constructor and moved it here, returning the instance in order to decouple
     * the instantiation from the driver action, because some other methods (such as a link click) should not be
     * forced to invoke the target url by driver.get() but with said action.
     *
     * @return <T> generic instance of BasePage <T extends BasePage<T>>.
     */
    @SuppressWarnings({"unchecked"})
    public T goTo(){
        driver.get(url.toString());
        return (T) this;
    }
}