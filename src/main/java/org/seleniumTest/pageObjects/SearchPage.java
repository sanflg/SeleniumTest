package org.seleniumTest.pageObjects;

import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.seleniumTest.DriverManager;
import org.seleniumTest.ExtendedWebElement;
import org.seleniumTest.utils.MoreExpectedConditions;

import java.time.Duration;
import java.util.List;

public class SearchPage extends BasePage<SearchPage> {
    //TODO 4 Parameters for final classes assign on runtime give warning of no assignment
    private static final ExtendedWebElement SEARCH_BOX = new ExtendedWebElement(By.name("q"));
    private static final ExtendedWebElement SEARCH_BUTTON = new ExtendedWebElement(By.name("btnk"));

    public SearchPage() {
        super(domain);
    }

    @Step ("Search by {0} with '{1}' term")
    public static void searchBy(String search, String term) {

        SEARCH_BOX.getElement().sendKeys(term);
        SEARCH_BOX.getElement().sendKeys(Keys.ESCAPE);

        WebElement button = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(2)).until(
                MoreExpectedConditions.anyElementToBeClickable(SEARCH_BUTTON.getElements()));
        switch (search){
            case "button": button.click(); break;
            case "submit": SEARCH_BOX.getElement().submit(); break;
            case "enter": SEARCH_BOX.getElement().sendKeys(Keys.ENTER); break;
        }
    }
}
