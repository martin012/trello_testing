package com.trello.testing.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.trello.testing.web.browser.Chrome;

import java.util.List;

/**
 * Manipulate with card in browser
 */
public class Card {

    // Web elements on page
    private static final String CARD_TITLE_CSS = "[class='list-card-title js-card-name']";

    private static final String DESCRIPTION_EDIT_HIDE_CSS = "[class='description-content js-desc-content']";
    private static final String DESCRIPTION_TEXT_CSS = "[class='field js-description-draft']";
    private static final String DESCRIPTION_CONFIRM_CSS= "[class='primary confirm mod-submit-edit js-save-edit']";

    private static final String CHECKLIST_MENU_CSS = "[class='button-link js-add-checklist-menu']";
    private static final String CHECKLIST_TITLE_CSS = "[class='js-checklist-title js-autofocus']";
    private static final String CHECKLIST_CONFIRM_CSS = "[class='primary wide confirm js-add-checklist']";

    private static final String COMMENT_INPUT_CSS = "[class='comment-box-input js-new-comment-input']";
    private static final String COMMENT_CONFIRM_CSS = "[class='primary confirm mod-no-top-bottom-margin js-add-comment']";

    private static final String ADD_ATTACHMENT_BUTTON_CSS = "[class='js-attach']";
    private static final String ADD_LINK_ID = "addLink";
    private static final String ATTACHMENT_CONFIRM_CSS = "[class='js-add-attachment-url']";


//    private static final String LIST_CSS = "[class='']";
//    private static final String LIST_CSS = "[class='']";

    public static WebElement getCard(WebElement list, String cardName) {

        List<WebElement> allCards = list.findElements(By.cssSelector(CARD_TITLE_CSS));

        for(WebElement card : allCards) {

            String name = card.getText();

            if(name.equals(cardName)) {

                return card;
            }
        }

        return null;

    }

    public static void openCard(WebElement list, String cardName) {

        WebElement card = getCard(list, cardName);
        card.click();
    }

    public static void addDescription(String text) {

        Chrome.getDriver().findElement(By.cssSelector(DESCRIPTION_EDIT_HIDE_CSS)).click();
        Chrome.getDriver().findElement(By.cssSelector(DESCRIPTION_TEXT_CSS)).sendKeys(text);
        Chrome.getDriver().findElement(By.cssSelector(DESCRIPTION_CONFIRM_CSS)).click();
    }

    public static void addChecklist(String text) {

        Chrome.getDriver().findElement(By.cssSelector(CHECKLIST_MENU_CSS)).click();
        Chrome.getDriver().findElement(By.cssSelector(CHECKLIST_TITLE_CSS)).sendKeys(text);
        Chrome.getDriver().findElement(By.cssSelector(CHECKLIST_CONFIRM_CSS)).click();
    }

    public static void addComment(String text) {

        Chrome.getDriver().findElement(By.cssSelector(COMMENT_INPUT_CSS)).sendKeys(text);
        Chrome.getDriver().findElement(By.cssSelector(COMMENT_CONFIRM_CSS)).click();
    }

    public static void uploadImage(String link) {

        Chrome.getDriver().findElement(By.cssSelector(ADD_ATTACHMENT_BUTTON_CSS)).click();
        Chrome.getDriver().findElement(By.id(ADD_LINK_ID)).sendKeys(link);
        Chrome.getDriver().findElement(By.cssSelector(ATTACHMENT_CONFIRM_CSS)).click();
    }

}
