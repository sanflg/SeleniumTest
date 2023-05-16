package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;
import org.slf4j.bridge.SLF4JBridgeHandler;
import static org.seleniumTest.cucumber.runner.CucumberRunnerTests.retrieveXmlValue;

@Epic("SearchEpic")
@Feature("Search")
public class GoogleSearchDefinition {
    public GoogleHomePage googleHomePage;
    public GoogleResultPage googleResultPage;
    public WebDriver driver = WebDriverManager.getInstance(retrieveXmlValue("driver")).create();

    public GoogleSearchDefinition() {
        SLF4JBridgeHandler.removeHandlersForRootLogger();
        SLF4JBridgeHandler.install();
    }

    @Given("User is in search page")
    public void userIsInSearchPage() {
        googleHomePage = new GoogleHomePage(driver).goTo();
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
