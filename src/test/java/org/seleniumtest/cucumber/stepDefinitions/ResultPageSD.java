package org.seleniumtest.cucumber.stepDefinitions;

import io.cucumber.java.en.Then;
import org.seleniumtest.DriverManager;
import org.seleniumtest.pageobjects.ResultPage;

public final class ResultPageSD {
    private final ResultPage resultPage;
    private final SearchContext searchContext;

    public ResultPageSD(SearchContext searchContext) {
        this.resultPage = new ResultPage(DriverManager.getInstance().getDriver(), searchContext.getTerm());
        this.searchContext = searchContext;
    }

    @Then("user is in correct search tab with term {}")
    public void userIsInCorrectSearchTabWithTerm(String term) {
        resultPage.assertIsCurrentPage(term);
    }
}
