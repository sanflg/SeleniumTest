package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.seleniumTest.DriverManager;
import org.seleniumTest.pageObjects.SearchPage;

public final class SearchPageSD{
    private final SearchPage searchPage;

    public SearchPageSD(){
        this.searchPage = new SearchPage(DriverManager.getInstance().getDriver());
    }

    @Given("User is in search page")
    public void userIsInSearchPage() {
        searchPage.goTo();
    }

    @When("User does a {} search with {}")
    public void userDoesASearchWith(String search, String term) {
        searchPage.searchBy(search, term);
    }
}
