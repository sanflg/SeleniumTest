package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.queryParamsPresent;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Step;
import org.seleniumTest.DriverManager;
import org.testng.asserts.SoftAssert;

public class ResultPage extends BasePage<ResultPage> {
    public static final ThreadLocal<String> termPool = new ThreadLocal<>();
    public static final String partialUrl = domain + "search?q=";

    //TODO fix term pool mess
    public ResultPage() {
        super(partialUrl + termPool.get());
    }

    public ResultPage(String term) {
        super(partialUrl + term);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage(String term) {
        URL actualUrl;
        URL targetUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URL(DriverManager.getDriver().getCurrentUrl());
            targetUrl = new URL(partialUrl + term);
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException building URL from driver.");
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
