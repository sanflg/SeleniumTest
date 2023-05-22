package org.seleniumTest.testng.test.search;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.seleniumTest.testng.dataProviders.search.SearchDataProvider;

import org.seleniumTest.testng.BaseTest;
import org.seleniumTest.pageObjects.SearchPage;
import org.seleniumTest.pageObjects.ResultPage;

@Epic("SearchEpic")
@Feature("Search")
@Link("https://www.google.com.ar/")
@Owner("Santiago Lataza")
public class SearchTest extends BaseTest {
    public static final Logger logger = LogManager.getLogger(SearchTest.class);

    @Test(dataProvider = "button", dataProviderClass = SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchByButton(String search, String term) {
        SearchPage searchPage = new SearchPage().goTo();
        ResultPage resultPage = searchPage.searchBy(search, term);
        resultPage.assertIsCurrentPage(term);
        logger.info("Search with button worked correctly.");
    }

    @Test(dataProvider = "submit", dataProviderClass = SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchBySubmit(String search, String term) {
        SearchPage searchPage = new SearchPage().goTo();
        ResultPage resultPage = searchPage.searchBy(search, term);
        resultPage.assertIsCurrentPage(term);
        logger.info("Search with submit worked correctly.");
    }

    @Test(dataProvider = "enter", dataProviderClass = SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchByEnter(String search, String term) {
        SearchPage searchPage = new SearchPage().goTo();
        ResultPage resultPage = searchPage.searchBy(search, term);
        resultPage.assertIsCurrentPage(term);
        logger.info("Search with enter worked correctly.");
    }
}