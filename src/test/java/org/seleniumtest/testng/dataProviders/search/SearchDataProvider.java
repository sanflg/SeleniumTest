package org.seleniumtest.testng.dataProviders.search;

import org.testng.annotations.DataProvider;

public class SearchDataProvider {
    @DataProvider(name = "search")
    public static Object[][] search() {
        return new Object[][]{
                {"button", "testng"},
                {"button", "staircase"},
                {"submit", "testng"},
                {"submit", "staircase 1"},
                {"enter", "testng"},
                {"enter", "staircase"}
        };
    }
}

