package org.seleniumTest.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BasePage<GoogleHomePage> {
    @FindBy(name = "q")
    protected WebElement searchBox;
    @FindBy(name = "btnK")
    protected WebElement searchButton;

    public GoogleHomePage() {
        super("https://www.google.com.ar/");
    }

    @Step("Search by google search button with term: '{0}'.")
    public GoogleResultPage searchByButton(String term) {
        searchBox.sendKeys(term);
        searchButton.submit();
        return new GoogleResultPage(term);
    }

    @Step("Search by submit action with term: '{0}'.")
    public GoogleResultPage searchBySubmit(String term) {
        searchBox.sendKeys(term);
        searchBox.submit();
        return new GoogleResultPage(term);
    }

    @Step("Search by enter with term: '{0}'.")
    public GoogleResultPage searchByEnter(String term) {
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.ENTER);
        return new GoogleResultPage(term);
    }
}
