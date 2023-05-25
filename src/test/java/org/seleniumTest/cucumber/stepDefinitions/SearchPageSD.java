package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.seleniumTest.pageObjects.SearchPage;

public final class SearchPageSD{
    private final SearchPage searchPage;
    private final ScenarioContext scenarioContext;

    public SearchPageSD(ScenarioContext scenarioContext){
        this.scenarioContext = scenarioContext;
        this.searchPage = new SearchPage();
    }

    @Given("User is in search page")
    public void userIsInSearchPage() {
        searchPage.goTo();
    }

    @When("User does a {} search with {}")
    public void userDoesASearchWith(String search, String term) {
        searchPage.searchBy(search, term);
        scenarioContext.setTerm("TERM SET AS: "+ term);
    }
}
