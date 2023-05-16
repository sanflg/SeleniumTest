package org.seleniumTest.testng.google.search;

import io.qameta.allure.*;
import org.seleniumTest.BaseTest;
import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;
import org.testng.annotations.Test;

@Epic("SearchEpic")
@Feature("Search")
public class GoogleSearchTest extends BaseTest {

    @Test (priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchByEnter() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchByEnter("panda");
        googleResultPage.isCurrentPage();
        logger.info("Search with enter worked correctly.");
    }

    @Test (priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by submitting the search text box after declaring the search term.")
    @Story("TT-1")
    public void googleHomePage_searchBySubmit() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchBySubmit("panda");
        googleResultPage.isCurrentPage();
        logger.info("Search with submit worked correctly.");
    }

    @Test (priority = 1)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by submitting the button after declaring the term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchByButton() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchByButton("panda");
        googleResultPage.isCurrentPage();
        logger.info("Search with button worked correctly.");
    }
}
