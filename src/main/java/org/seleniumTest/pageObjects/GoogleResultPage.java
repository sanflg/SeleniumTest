package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.queryParamsPresent;

import java.net.URL;
import java.net.MalformedURLException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.seleniumTest.DriverManager;
import org.testng.asserts.SoftAssert;

public final class GoogleResultPage extends BasePage<GoogleResultPage> {
    public static final ThreadLocal<String> termPool = new ThreadLocal<>();

    public GoogleResultPage() {
        super("https://www.google.com.ar/search?q=" + termPool.get());
    }

    public static GoogleResultPage getResultPage(String term){
        termPool.set(term);
        return new GoogleResultPage();
    }

    //TODO Avoid double annotation.
//    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    @Then("User is in correct search tab")
    public void assertIsCurrentPage() {
        URL actualUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URL(DriverManager.getDriverManager().getDriver().getCurrentUrl());
        } catch (MalformedURLException e) {
            logger.error("MalformedURLException building URL from driver.");
            throw new RuntimeException(e);
        }

        softAssert.assertEquals(actualUrl.getProtocol(), url.getProtocol());
        softAssert.assertEquals(actualUrl.getHost(), url.getHost());
        softAssert.assertEquals(actualUrl.getPort(), url.getPort());
        softAssert.assertEquals(actualUrl.getPath(), url.getPath());
        softAssert.assertTrue(queryParamsPresent(url, actualUrl));

        softAssert.assertAll();
    }
}
