package org.seleniumTest.pageObjects;

import static org.seleniumTest.utils.PageUtils.queryParamsPresent;
import static org.testng.Assert.assertEquals;

import java.net.URL;
import java.net.MalformedURLException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Step;
import org.testng.asserts.SoftAssert;

public class GoogleResultPage extends BasePage<GoogleResultPage> {
    protected final String term;

    public GoogleResultPage(String term) {
        super("https://www.google.com.ar/search?q=" + term);
        this.term = term;
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    @Then("User is in correct GoogleResultPage tab")
    public void isCurrentPage() {
        URL actualUrl;
        SoftAssert softAssert = new SoftAssert();

        try {
            actualUrl = new URL(driverManager.getDriver().getCurrentUrl());
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

    @Step("Go to the GoogleResultPage page.")
    @Given("User is in GoogleResultPage page")
    public GoogleResultPage goTo() {return goTo(url.toString());}
}
