package org.seleniumTest.pageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public final class GoogleHomePage extends BasePage {
    //TODO Parameters for final classes assign on runtime give warning of no assignment
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(name = "btnK")
    private WebElement searchButton;

    public GoogleHomePage() {
        super("https://www.google.com.ar/");
    }

    @Step("Search by {0} action with term: '{1}'.")
    //TODO Learn Gherkin
    @When("User does a {} search with term {}")
    public GoogleResultPage searchBy(String search, String term) {
        searchBox.sendKeys(term);
        switch (search){
            case "button": searchButton.submit(); break;
            case "submit": searchBox.submit(); break;
            case "enter": searchBox.sendKeys(Keys.ENTER); break;
        }
        return GoogleResultPage.getResultPage(term);
    }

    //TODO There is a way to avoid this double notation?
    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    @Then("User is in correct GoogleHome tab")
    public void isCurrentPage() {isCurrentPageBase();}

    @Step("Go to GoogleHomePage page.")
    @Given("User is in search page")
    public GoogleHomePage goTo() {
        goTo(url.toString());
        return this;
    }
}
