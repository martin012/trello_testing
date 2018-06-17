package com.trello.testing.web.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Created by mmalik on 16/06/2018.
 */
public class Chrome {

    private final static WebDriver driver;

    static {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void quitDriver() {
        driver.quit();
    }

}
