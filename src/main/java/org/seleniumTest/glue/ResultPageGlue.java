package org.seleniumTest.glue;

import io.cucumber.java.en.Then;
import org.seleniumTest.pageObjects.ResultPage;

public class ResultPageGlue {

    @Then("User is in correct search tab with term {}")
    public void assertIsCurrentPage(String term) {
        ResultPage.assertIsCurrentPage(term);
    }
}
