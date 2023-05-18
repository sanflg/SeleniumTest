package org.seleniumTest.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.seleniumTest.pageObjects.SearchPage;

//TODO 1 - Call @Step method with another @Step method and see what happens in logings
//TODO 1 Learn Gherkin
public class HomePageGlue {

    @When("User does a {} search with {}")
    public void searchBy(String search, String term) {
        SearchPage.searchBy(search, term);
    }

    //TODO 2 Investigate for abstract implementation for cucumber
    @Given("User is in search page")
    public void goTo() {
        SearchPage.goTo();}
}
