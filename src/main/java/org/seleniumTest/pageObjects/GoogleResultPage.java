package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.comparePartialQueryParams;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import java.net.MalformedURLException;
import java.net.URL;

public class GoogleResultPage extends BasePage<GoogleResultPage>{
    protected final String term;

    public GoogleResultPage(WebDriver driver, String term) {
        super(driver, "https://www.google.com.ar/search?q=" + term);
        this.term = term;
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void isCurrentPage(){
        URL actualUrl;
        SoftAssert softAssert = new SoftAssert();

        try { actualUrl = new URL(driver.getCurrentUrl());}
        catch (MalformedURLException e) {
            logger.error("MalformedURLException building URL from driver.");
            throw new RuntimeException(e);
        }

        softAssert.assertEquals(actualUrl.getProtocol(), url.getProtocol());
        softAssert.assertEquals(actualUrl.getHost(), url.getHost());
        softAssert.assertEquals(actualUrl.getPort(), url.getPort());
        softAssert.assertEquals(actualUrl.getPath(), url.getPath());
        softAssert.assertEquals(actualUrl.getProtocol(), url.getProtocol());
        softAssert.assertTrue(comparePartialQueryParams(url, actualUrl));

        softAssert.assertAll();
    }
}
