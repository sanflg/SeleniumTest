package org.seleniumtest.utils.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

/**
 * @see ExpectedConditions
 */
public class MoreExpectedConditions {
    private static final Logger LOGGER = LogManager.getLogger(MoreExpectedConditions.class);

    private MoreExpectedConditions(){}

    /**
     * @param elements Web elements to check.
     * @return The first web element found to be clickable.
     * @see #anyElementToBeClickable(List)
     */
    public static ExpectedCondition<WebElement> anyElementToBeClickable(final WebElement... elements) {
        return MoreExpectedConditions.anyElementToBeClickable(Arrays.asList(elements));
    }

    /**
     * @param elements The list of elements to check.
     * @return The first element from the list found to be clickable.
     */
    public static ExpectedCondition<WebElement> anyElementToBeClickable(final List<WebElement> elements) {
        return new ExpectedCondition<>() {
            public WebElement apply(WebDriver driver) {
                for (WebElement element : elements) {
                    try {
                        WebElement visibleElement = ExpectedConditions.visibilityOf(element).apply(driver);
                        if (visibleElement != null && visibleElement.isEnabled()) {
                            return visibleElement;
                        }
                    } catch (StaleElementReferenceException e) {
                        LOGGER.error(String.format("WebElement '%s' is in stale state", element.getTagName()), e);
                    }
                }
                return null;
            }

            public String toString() {
                return "any element to be clickable: " + elements;
            }
        };
    }
}