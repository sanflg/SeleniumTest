package org.seleniumTest.cucumber.steps;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.slf4j.bridge.SLF4JBridgeHandler;

import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;

@Epic("SearchEpic")
@Feature("Search")
public class GoogleSearchDefinition {
    public GoogleHomePage googleHomePage;
    public GoogleResultPage googleResultPage;

    public GoogleSearchDefinition() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Given("User is in correct starting page")
    public void userIsInSearchPage() {
        googleHomePage = new GoogleHomePage().goTo();
    }

    @When("User does a button search with term {}")
    public void userDoesAButtonSearchWithTerm(String term) {
        googleResultPage = googleHomePage.searchByEnter(term);
    }

    @When("User does a submit search with term {}")
    public void userDoesASubmitSearchWithTerm(String term) {
        googleResultPage = googleHomePage.searchBySubmit(term);
    }

    @When("User does an enter search with term {}")
    public void userDoesAnEnterSearchWithTerm(String term) {
        googleResultPage = googleHomePage.searchByEnter(term);
    }

    @Then("User is in correct search tab")
    public void userIsInCorrectSearchTab() {
        googleResultPage.isCurrentPage();
    }
}
