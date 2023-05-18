package org.seleniumTest.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumTest.DriverManager;
import org.seleniumTest.utils.MoreExpectedConditions;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage<SearchPage> {
    //TODO 4 Parameters for final classes assign on runtime give warning of no assignment
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(name = "btnK")
    private List<WebElement> searchButtons;

    public SearchPage() {
        super(domain);
    }

    @Step ("Search by {0} with '{1}' term")
    public static ResultPage searchBy(String search, String term) {
        SearchPage searchPageInstance = new SearchPage();

        searchPageInstance.searchBox.sendKeys(term);
        searchPageInstance.searchBox.sendKeys(Keys.ESCAPE);

        WebElement element = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(2)).until(
                MoreExpectedConditions.anyElementToBeClickable(searchButtons));
        switch (search){
            case "button": element.click(); break;
            case "submit": searchBox.submit(); break;
            case "enter": searchBox.sendKeys(Keys.ENTER); break;
        }
        return new ResultPage(term);
    }
}
