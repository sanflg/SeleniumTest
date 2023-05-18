package org.seleniumTest.testng.google.search;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.Description;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.Test;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.seleniumTest.testng.dataProviders.SearchDataProvider;

import org.seleniumTest.testng.BaseTest;
import org.seleniumTest.pageObjects.SearchPage;
import org.seleniumTest.pageObjects.ResultPage;

@Epic("SearchEpic")
@Feature("Search")
public class SearchTest extends BaseTest {
    public static final Logger logger = LogManager.getLogger(SearchTest.class);
    public SearchPage searchPage;
    public ResultPage resultPage;

    @Test(dataProvider = "data-provider",dataProviderClass=SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchBy(String search, String term) {
        searchPage = new SearchPage().goTo();
        resultPage = searchPage.searchBy(search, term);
        resultPage.assertIsCurrentPage(term);
        logger.info("Search with enter worked correctly.");
    }
}
