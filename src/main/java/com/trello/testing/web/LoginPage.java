package com.trello.testing.web;

import com.trello.testing.common.GlobalVariables;
import com.trello.testing.exceptions.rest.ServiceException;
import com.trello.testing.exceptions.web.BrowserException;
import com.trello.testing.exceptions.web.WebElementException;
import org.openqa.selenium.By;
import com.trello.testing.web.browser.Chrome;
import org.testng.Assert;

/**
 * Manipulate with login page in browser.
 */
public class LoginPage {

    private static final String LOGIN_URL = GlobalVariables.BASE_URL + "login";

    // Web elements on page
    private static final String USER_ID = "user";
    private static final String PASSWORD_ID = "password";
    private static final String LOGIN_ID = "login";
    private static final String LOGO_CSS = "[class='header-logo-default']";

    /**
     *  Navigate to Login page
     */
    public static void getPage() {

        Chrome.getDriver().get(LOGIN_URL);
    }

    /**
     * Log in to Trello
     *
     * @param mail          mail for account
     * @param password     password for account
     * @throws BrowserException
     */
    public static void logIn(String mail, String password) throws BrowserException {

        try {

            Chrome.findElementById(USER_ID).sendKeys(mail);
            Chrome.findElementById(PASSWORD_ID).sendKeys(password);
            Chrome.findElementById(LOGIN_ID).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to log in ", e);
        }
    }

    /**
     * Log in to Trello with default mail and password
     *
     * @throws BrowserException
     */
    public static void logIn() throws BrowserException {

        logIn(GlobalVariables.MAIL, GlobalVariables.PASSWORD);
    }

    /**
     * Get page, log in and check whether it was successful
     *
      * @throws BrowserException
     */
    public static void loginWithCheck() throws BrowserException {

        LoginPage.getPage();
        LoginPage.logIn();

        try {

            Chrome.findElementByCss(LOGO_CSS);

        } catch (WebElementException e) {

            throw new BrowserException("Failed to log in", e);
        }
    }

}
