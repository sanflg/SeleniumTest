package org.seleniumtest.pageobjects;

import static org.seleniumtest.utils.PageUtils.queryParamsPresent;

import java.net.URL;
import java.net.MalformedURLException;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ResultPage extends BasePage<ResultPage> {
    public static final String PARTIAL_URL = DOMAIN + "search?q=";

    public ResultPage(WebDriver driver, String term) {
        super(driver, PARTIAL_URL + term);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    public void assertIsCurrentPage(String term) {
        URL actualUrl;
        URL targetUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URL(driver.getCurrentUrl());
            targetUrl = new URL(PARTIAL_URL + term);

            softAssert.assertEquals(actualUrl.getProtocol(), targetUrl.getProtocol());
            softAssert.assertEquals(actualUrl.getHost(), targetUrl.getHost());
            softAssert.assertEquals(actualUrl.getPort(), targetUrl.getPort());
            softAssert.assertEquals(actualUrl.getPath(), targetUrl.getPath());
            softAssert.assertTrue(queryParamsPresent(targetUrl, actualUrl));

            softAssert.assertAll();
        } catch (MalformedURLException e) {
            LOGGER.error("MalformedURLException building URL from driver.");
            Assert.fail();
        }
    }
}
