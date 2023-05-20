package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import org.seleniumTest.pageObjects.ResultPage;

public final class ResultPageSD {
    private final ResultPage resultPage = new ResultPage();

    @Then("User is in correct search tab with term {}")
    public void userIsInCorrectSearchTabWithTerm(String term) {
        resultPage.assertIsCurrentPage(term);
    }
}
