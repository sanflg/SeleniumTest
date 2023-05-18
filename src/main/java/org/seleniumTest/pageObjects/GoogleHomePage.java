package org.seleniumTest.pageObjects;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.seleniumTest.utils.MoreExpectedConditions;

import java.time.Duration;
import java.util.List;

public final class GoogleHomePage extends BasePage<GoogleHomePage> {
    //TODO 4 Parameters for final classes assign on runtime give warning of no assignment
    protected final static String url;
    @FindBy(name = "q")
    private WebElement searchBox;
    @FindBy(name = "btnK")
    private List<WebElement> searchButtons;

    public GoogleHomePage() {
        super("https://www.google.com.ar/");
    }

    @Step("Search by {0} action with term: '{1}'.")
    public GoogleResultPage searchBy(String search, String term) {
        searchBox.sendKeys(term);
        searchBox.sendKeys(Keys.ESCAPE);
        WebElement element = new WebDriverWait(driverManager.getDriver(), Duration.ofSeconds(2)).until(
                MoreExpectedConditions.anyElementToBeClickable(searchButtons));
        switch (search){
            case "button": element.click(); break;
            case "submit": searchBox.submit(); break;
            case "enter": searchBox.sendKeys(Keys.ENTER); break;
        }
        return GoogleResultPage.getResultPage(term);
    }
}
