package org.seleniumTest;

import org.seleniumTest.pageObjects.GoogleHomePage;
import org.seleniumTest.pageObjects.GoogleResultPage;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class GoogleSearchTest extends BaseTest {

    @Test (description="Verify google search by pressing enter after declaring the search term in the text box.")
    void googleHomePage_searchByEnter() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchByEnter("panda");
        assertTrue(googleResultPage.isCurrentPage());
        logger.info("Search with enter worked correctly.");
    }

    @Test (description="Verify google search by submitting the search text box after declaring the search term.")
    void googleHomePage_searchBySubmit() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchBySubmit("panda");
        assertTrue(googleResultPage.isCurrentPage());
        logger.info("Search with submit worked correctly.");
    }

    @Test (description="Verify google search by submitting the search button after declaring the term in the text box.")
    void googleHomePage_searchByButton() {
        GoogleHomePage googleHomePage = new GoogleHomePage(driver).goTo();
        GoogleResultPage googleResultPage = googleHomePage.searchByButton("panda");
        assertTrue(googleResultPage.isCurrentPage());
        logger.info("Search with button worked correctly.");
    }
}
