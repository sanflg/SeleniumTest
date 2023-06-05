package org.seleniumtest.cucumber.web.search.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.seleniumtest.DriverManager;
import org.seleniumtest.web.pageobjects.google.ResultPage;

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
