package org.seleniumTest.pageObjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleHomePage extends BasePage<GoogleHomePage> {
    @FindBy (name="q")
    protected WebElement searchBox;
    @FindBy (name="btnK")
    protected WebElement searchButton;

    public GoogleHomePage(WebDriver driver) {
        super(driver, "https://www.google.com.ar/");
    }

    public GoogleResultPage searchByButton(String term){
        searchBox.sendKeys(term);
        searchButton.submit();
        return new GoogleResultPage(driver, term);
    }

    public GoogleResultPage searchBySubmit(String term){
        searchBox.sendKeys(term);
        searchBox.submit();
        return new GoogleResultPage(driver, term);
    }

    public GoogleResultPage searchByEnter(String term){
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.ENTER);
        return new GoogleResultPage(driver, term);
    }
}
