package org.seleniumtest.web.pageobjects.google;

import static org.seleniumtest.utils.web.PageUtils.queryParamsPresent;

import java.net.*;
import java.nio.charset.StandardCharsets;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class ResultPage extends BasePage<ResultPage> {
    protected static final Logger LOGGER = LogManager.getLogger(ResultPage.class);
    public static final String PARTIAL_URL = DOMAIN + "search?q=";

    public ResultPage(WebDriver driver, String term) {
        super(driver, PARTIAL_URL + URLEncoder.encode(term, StandardCharsets.UTF_8));
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    // TODO nested steps missing.
    public void assertIsCurrentPage(String term) {
        URL actualUrl;
        URL targetUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URI(driver.getCurrentUrl()).toURL();
            targetUrl = new URI(PARTIAL_URL + URLEncoder.encode(term, StandardCharsets.UTF_8)).toURL();

            softAssert.assertEquals(actualUrl.getProtocol(), targetUrl.getProtocol());
            softAssert.assertEquals(actualUrl.getHost(), targetUrl.getHost());
            softAssert.assertEquals(actualUrl.getPort(), targetUrl.getPort());
            softAssert.assertEquals(actualUrl.getPath(), targetUrl.getPath());
            softAssert.assertTrue(queryParamsPresent(targetUrl, actualUrl));

            softAssert.assertAll();
        } catch (MalformedURLException | URISyntaxException e) {
            LOGGER.error("Error building URL from driver.");
            Assert.fail();
        }
    }
}
