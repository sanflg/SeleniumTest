package org.seleniumTest;

import org.openqa.selenium.*;
import java.util.List;

public class ExtendedWebElement {
    private final By by;

    public ExtendedWebElement(By by){
        this.by = by;
    }

    public WebElement getElement(){
        return DriverManager.getDriver().findElement(by);
    }

    public List<WebElement> getElements(){
        return DriverManager.getDriver().findElements(by);
    }

}
