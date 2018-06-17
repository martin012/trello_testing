package com.trello.testing.web;

import com.trello.testing.common.GlobalVariables;
import org.openqa.selenium.By;
import com.trello.testing.web.browser.Chrome;

/**
 * Manipulate with login page in browser.
 */
public class LoginPage {

    private static final String LOGIN_URL = GlobalVariables.BASE_URL + "login";

    // Web elements on page
    private static final String USER_ID = "user";
    private static final String PASSWORD_ID = "password";
    private static final String LOGIN_ID = "login";

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
     */
    public static void logIn(String mail, String password) {
        Chrome.getDriver().findElement(By.id(USER_ID)).sendKeys(mail);
        Chrome.getDriver().findElement(By.id(PASSWORD_ID)).sendKeys(password);
        Chrome.getDriver().findElement(By.id(LOGIN_ID)).click();
    }

    /**
     * Log in to Trello with default mail and password
     */
    public static void logIn() {
        logIn(GlobalVariables.MAIL, GlobalVariables.PASSWORD);
    }

}
