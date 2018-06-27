package com.trello.testing.web.check;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

/**
 * Verification of text for web element/s
 */
public class TextCheck {

    /**
     * Check whether any element contains expected text
     *
     * @param elements  web elements
     * @param expectedText expected text of web element
     */

    public static void checkAnyMatch(List<WebElement> elements, String expectedText, String message) {

        Assert.assertTrue(elements.stream().anyMatch(element -> element.getText().equals(expectedText)), message);
    }

    /**
     * Check whether element contains expecte text
     *
     * @param element   web element
     * @param expectedText expected text of web element
     */
    public static void checkText(WebElement element, String expectedText, String message) {

        String actualText = element.getText();

        Assert.assertEquals(actualText, expectedText, message);
    }

}
