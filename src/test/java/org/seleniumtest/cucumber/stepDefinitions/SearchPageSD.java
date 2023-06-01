package org.seleniumtest.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.seleniumtest.DriverManager;
import org.seleniumtest.pageobjects.SearchPage;

public final class SearchPageSD {
    private final SearchPage searchPage;
    private final SearchContext searchContext;

    public SearchPageSD(SearchContext searchContext) {
        this.searchPage = new SearchPage(DriverManager.getInstance().getDriver());
        this.searchContext = searchContext;
    }

    @Given("user is in search page")
    public void userIsInSearchPage() {
        searchPage.goTo();
    }

    @When("user does a {} search with {}")
    public void userDoesASearchWith(String search, String term) {
        searchPage.searchBy(search, term);
        searchContext.setTerm(term);
    }
}
