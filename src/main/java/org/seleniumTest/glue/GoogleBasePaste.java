package org.seleniumTest.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.seleniumTest.pageObjects.BasePage;
import org.seleniumTest.pageObjects.GoogleHomePage;

public class GoogleBasePaste <T extends BasePage> {
    @Then("User is in correct GoogleHome tab")
    public void assertIsCurrentPage() {assertIsCurrentPageBase();}

    //    @Step("Go to GoogleHomePage page.")
    @Given("User is in page {}")
    public GoogleHomePage goTo(String page) {
        goTo(url.toString());
        return this;
    }
}
