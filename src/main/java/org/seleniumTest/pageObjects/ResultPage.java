package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.queryParamsPresent;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

public class ResultPage extends BasePage<ResultPage> {
    public static final ThreadLocal<String> termPool = new ThreadLocal<>();
    public static final String partialUrl = domain + "search?q=";

    //TODO fix term pool mess
    public ResultPage(WebDriver driver) {
        super(driver, partialUrl + termPool.get());
    }

    public ResultPage(WebDriver driver, String term) {
        super(driver, partialUrl + term);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage(String term) {
        URL actualUrl;
        URL targetUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URL(driver.getCurrentUrl());
            targetUrl = new URL(partialUrl + term);
        } catch (MalformedURLException e) {
//            LOGGER.log(Level.ERROR, "MalformedURLException building URL from driver.");
            throw new RuntimeException(e);
        }

        softAssert.assertEquals(actualUrl.getProtocol(), targetUrl.getProtocol());
        softAssert.assertEquals(actualUrl.getHost(), targetUrl.getHost());
        softAssert.assertEquals(actualUrl.getPort(), targetUrl.getPort());
        softAssert.assertEquals(actualUrl.getPath(), targetUrl.getPath());
        softAssert.assertTrue(queryParamsPresent(targetUrl, actualUrl));

        softAssert.assertAll();
    }
}
