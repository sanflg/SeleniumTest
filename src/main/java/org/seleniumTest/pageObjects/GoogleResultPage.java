package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.comparePartialQueryParams;
import org.openqa.selenium.WebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class GoogleResultPage extends BasePage{
    protected final String term;

    public GoogleResultPage(WebDriver driver, String term) {
        super(driver, "https://www.google.com.ar/search?q=" + term);
        this.term = term;
    }

    @Override
    public boolean isCurrentPage(){
        URL actualUrl;

        try { actualUrl = new URL(driver.getCurrentUrl());}
        catch (MalformedURLException e) {
            logger.error("MalformedURLException building URL from driver.");
            throw new RuntimeException(e);
        }

        return (actualUrl.getProtocol().equals(url.getProtocol()) &&
                actualUrl.getHost().equals(url.getHost()) &&
                actualUrl.getPort() == (url.getPort()) &&
                actualUrl.getPath().equals(url.getPath()) &&
                comparePartialQueryParams(url, actualUrl));
    }
}
