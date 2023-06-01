package org.seleniumtest.testng.test.search;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.seleniumtest.DriverManager;
import org.testng.annotations.Test;
import org.seleniumtest.testng.dataProviders.search.SearchDataProvider;

import org.seleniumtest.pageobjects.SearchPage;
import org.seleniumtest.pageobjects.ResultPage;

@Epic("SearchEpic")
@Feature("Search")
@Link("https://www.google.com.ar/")
@Owner("Santiago Lataza")
public class SearchTest {
//    protected static final AllureLogger LOGGER = new AllureLogger(SearchTest.class);

    @Test(dataProvider = "search", dataProviderClass = SearchDataProvider.class)
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify google search by pressing enter after declaring the search term in the text box.")
    @Story("TT-1")
    public void googleHomePage_search(String search, String term) {
        SearchPage searchPage = new SearchPage(DriverManager.getInstance().getDriver()).goTo();
        ResultPage resultPage = searchPage.searchBy(search, term);
        resultPage.assertIsCurrentPage(term);
    }
}
