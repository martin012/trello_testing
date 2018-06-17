package com.trello.testing.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.trello.testing.web.browser.Chrome;
import java.util.List;

/**
 * Manipulate with cards of list in browser.
 */
public class ListCards {

    // Web elements on page
    private static final String LIST_CSS = "[class='list js-list-content']";
    private static final String LIST_NAME_CSS = "[class='list-header-name mod-list-name js-list-name-input']";

    private static final String ADD_CARD_CSS = "[class='open-card-composer js-open-card-composer']";
    private static final String ADD_CARD_TITLE_CSS = "[class='list-card-composer-textarea js-card-title']";
    private static final String ADD_CARD_BUTTON_CSS = "[class='primary confirm mod-compact js-add-card']";

    /**
     * Find the list on a board
     *
     * @param  listName name of the list
     * @return list     if there is expected list
     *         null     otherwise
     */
    public static WebElement getList(String listName) {

        List<WebElement> allLists = Chrome.getDriver().findElements(By.cssSelector(LIST_CSS));

        for(WebElement list : allLists) {

            String name = list.findElement(By.cssSelector(LIST_NAME_CSS)).getText();

            if(name.equals(listName)) {

                return list;
            }
        }

        return null;
    }

    /**
     * Add new card to the list
     *
     * @param  listName name of the list
     * @param  cardName name for new card
     */
    public static void addCard(String listName, String cardName) {

        // Find expected list
        WebElement list = getList(listName);
        list.findElement(By.cssSelector(ADD_CARD_CSS)).click();

        // Add title and click on submit button
        Chrome.getDriver().findElement(By.cssSelector(ADD_CARD_TITLE_CSS)).sendKeys(cardName);
        Chrome.getDriver().findElement(By.cssSelector(ADD_CARD_BUTTON_CSS)).click();
    }

}
