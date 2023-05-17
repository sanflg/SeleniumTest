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
import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;

@Epic("SearchEpic")
@Feature("Search")
public class GoogleSearchTest extends BaseTest {
    public static final Logger logger = LogManager.getLogger(GoogleSearchTest.class);
    public GoogleHomePage googleHomePage;
    public GoogleResultPage googleResultPage;

    @Test(dataProvider = "data-provider",dataProviderClass=SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_searchBy(String search, String term) {
        googleHomePage = new GoogleHomePage().goTo();
        googleResultPage = googleHomePage.searchBy(search,term);
        googleResultPage.isCurrentPage();
        logger.info("Search with enter worked correctly.");
    }
}
