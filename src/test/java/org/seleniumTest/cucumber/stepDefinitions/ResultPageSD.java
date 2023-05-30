package org.seleniumTest.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import org.seleniumTest.DriverManager;
import org.seleniumTest.pageObjects.ResultPage;

public final class ResultPageSD{
    private final ResultPage resultPage;

    public ResultPageSD(){
        this.resultPage = new ResultPage(DriverManager.getInstance().getDriver());
    }

    @Then("User is in correct search tab with term {}")
    public void userIsInCorrectSearchTabWithTerm(String term) {
        resultPage.assertIsCurrentPage(term);
    }
}
