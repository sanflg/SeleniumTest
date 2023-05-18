package org.seleniumTest.glue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;
//TODO 1 Add new layer for cucumber steps
//TODO 1 - Call @Step method with another @Step method and see what happens in logings
//TODO 1 Learn Gherkin
public class GoogleHomePageGlue {
    @When("User does a {} search with {}")
    public GoogleResultPage searchBy(String search, String term) {

    }


}
