package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.seleniumTest.pageObjects.SearchPage;

//TODO 1 - Call @Step method with another @Step method and see what happens in logings
//TODO 1 Learn Gherkin
public final class SearchPageSD {
    private final SearchPage searchPage = new SearchPage();

    @When("User does a {} search with {}")
    public void userDoesASearchWith(String search, String term) {
        searchPage.searchBy(search, term);
    }

    //TODO 2 Investigate for abstract implementation for cucumber
    @Given("User is in search page")
    public void userIsInSearchPage() {
        searchPage.goTo();
    }
}
