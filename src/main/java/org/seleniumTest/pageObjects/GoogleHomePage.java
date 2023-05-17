package org.seleniumTest.pageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.testng.Assert.assertEquals;

public class GoogleHomePage extends BasePage<GoogleHomePage> {
    @FindBy(name = "q")
    protected WebElement searchBox;
    @FindBy(name = "btnK")
    protected WebElement searchButton;

    public GoogleHomePage() {
        super("https://www.google.com.ar/");
    }

    @Step("Search by google search button with term: '{0}'.")
    @When("User does a button search with term {}")
    public GoogleResultPage searchByButton(String term) {
        searchBox.sendKeys(term);
        searchButton.submit();
        return new GoogleResultPage(term);
    }

    @Step("Search by submit action with term: '{0}'.")
    @When("User does a submit search with term {}")
    public GoogleResultPage searchBySubmit(String term) {
        searchBox.sendKeys(term);
        searchBox.submit();
        return new GoogleResultPage(term);
    }

    @Step("Search by enter with term: '{0}'.")
    @When("User does an enter search with term {}")
    public GoogleResultPage searchByEnter(String term) {
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.ENTER);
        return new GoogleResultPage(term);
    }

    @Step("Check that the current driver url is the same as the desired at the instantiation moment.")
    @Then("User is in correct GoogleHome tab")
    public void isCurrentPage() {isCurrentPageBase();}
    @Step("Go to GoogleHomePage page.")
    @Given("User is in GoogleHome page")
    public GoogleHomePage goTo() {return goTo(url.toString());}
}
