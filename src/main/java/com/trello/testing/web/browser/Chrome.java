package com.trello.testing.web.browser;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.trello.testing.exceptions.web.WebElementException;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Chrome browser is  used for testing by default.
 * This class is used for configuration and helpers methods.
 *
 */
public class Chrome {

    private final static WebDriver driver;

    static {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

    private static WebElement findElement(By mechanism) throws WebElementException {

        WebElement element = null;

        try {

            element = getDriver().findElement(mechanism);

        } catch (NoSuchElementException e) {

            throw new WebElementException("Element not found: " + mechanism);
        }

        return element;

    }

    private static List<WebElement> findElements(By mechanism) throws WebElementException {

        List<WebElement> elements = null;

        try {

            elements = getDriver().findElements(mechanism);

        } catch (NoSuchElementException e) {

            throw new WebElementException("Element not found: " + mechanism);
        }

        return elements;

    }

    public static WebElement findElementByCss(String value) throws WebElementException {

        return findElement(By.cssSelector(value));
    }

    public static List<WebElement> findElementsByCss(String value) throws WebElementException {

        return findElements(By.cssSelector(value));
    }

    public static WebElement findElementById(String value) throws WebElementException {

        return findElement(By.id(value));
    }

}
