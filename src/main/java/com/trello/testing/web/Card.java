package com.trello.testing.web;

import com.trello.testing.exceptions.web.BrowserException;
import com.trello.testing.exceptions.web.WebElementException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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
    private static final String DESCRIPTION_TEXTAREA_CSS = "[class='field js-description-draft']";
    private static final String DESCRIPTION_TEXT_CSS = "[class='current markeddown hide-on-edit js-card-desc js-show-with-desc']";
    private static final String DESCRIPTION_CONFIRM_CSS= "[class='primary confirm mod-submit-edit js-save-edit']";

    private static final String CHECKLIST_MENU_CSS = "[class='button-link js-add-checklist-menu']";
    private static final String CHECKLIST_ADD_TITLE_CSS = "[class='js-checklist-title js-autofocus']";
    private static final String CHECKLIST_CONFIRM_CSS = "[class='primary wide confirm js-add-checklist']";
    private static final String CHECKLIST_TITLE_CSS =  "[class='current hide-on-edit']"; //" +
            //" "[class='editable non-empty checklist-title']/child::*";
    ///descendant::li[2]
    //"[class='editable non-empty checklist-title']//h3";

    private static final String COMMENT_INPUT_CSS = "[class='comment-box-input js-new-comment-input']";
    private static final String COMMENT_CONFIRM_CSS = "[class='primary confirm mod-no-top-bottom-margin js-add-comment']";
    private static final String CURRENT_COMMENTS_CSS = "[class='current-comment js-friendly-links js-open-card']";

    private static final String ADD_ATTACHMENT_BUTTON_CSS = "[class='js-attach']";
    private static final String ADD_LINK_ID = "addLink";
    private static final String ATTACHMENT_CONFIRM_CSS = "[class='js-add-attachment-url']";
    private static final String ATTACHMENT_THUMBNAIL_PREVIEW_CSS = "[class='attachment-thumbnail-preview js-open-viewer attachment-thumbnail-preview-is-cover']";

    /**
     * Get a card
     *
     * @param list list where the card is located
     * @param cardName name of card you want to get
     * @return card     if there is expected card
     *         null     otherwise
     * @throws BrowserException
     */
    public static WebElement getCard(WebElement list, String cardName) throws BrowserException {

        try {

            List<WebElement> allCards = list.findElements(By.cssSelector(CARD_TITLE_CSS));

            for (WebElement card : allCards) {

                String name = card.getText();

                if (name.equals(cardName)) {

                    return card;
                }
            }
        } catch (NoSuchElementException e) {

            throw new BrowserException("Unable to get card ", e);
        }

        return null;
    }

    /**
     * Get a card and open it
     *
     * @param list  list where the card is located
     * @param cardName  name of card you want to open
     * @throws BrowserException
     */
    public static void openCard(WebElement list, String cardName) throws BrowserException {

        try {

            WebElement card = getCard(list, cardName);
            card.click();
        } catch (BrowserException e) {

            throw new BrowserException("Unable to open card ", e);
        }
    }

    /**
     * Add a description with some text to the card
     *
     * @param text  text you want to add
     * @throws BrowserException
     */
    public static void addDescription(String text) throws BrowserException {

        try {

            Chrome.findElementByCss(DESCRIPTION_EDIT_HIDE_CSS).click();
            Chrome.findElementByCss(DESCRIPTION_TEXTAREA_CSS).sendKeys(text);
            Chrome.findElementByCss(DESCRIPTION_CONFIRM_CSS).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to add description ", e);
        }
    }

    /**
     * Get description of the card
     *
     * @return description if there is some
     * @throws BrowserException
     */
    public static WebElement getDescription() throws BrowserException {

        WebElement descriptionText = null;

        try {

             descriptionText = Chrome.findElementByCss(DESCRIPTION_TEXT_CSS);

        } catch (WebElementException e) {

            throw new BrowserException("Unable to get description ", e);
        }

        return descriptionText;
    }

    /**
     * Add checklist with some text to the card
     *
     * @param text text you want to add
     * @throws BrowserException
     */
    public static void addChecklist(String text) throws BrowserException {

        try {

            Chrome.findElementByCss(CHECKLIST_MENU_CSS).click();
            Chrome.findElementByCss(CHECKLIST_ADD_TITLE_CSS).sendKeys(text);
            Chrome.findElementByCss(CHECKLIST_CONFIRM_CSS).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to add checklist ", e);
        }
    }

    public static List<WebElement> getChecklistsTitles() throws BrowserException {

        List<WebElement> checklistsTitles = null;

        try {

            checklistsTitles = Chrome.findElementsByCss(CHECKLIST_TITLE_CSS);

        } catch (WebElementException e) {

            throw new BrowserException("Unable to get titles of checklists ", e);
        }

        return checklistsTitles;
    }

    /**
     * Add a comment with some text to the card
     *
     * @param text text you want to add
     * @throws BrowserException
     */
    public static void addComment(String text) throws BrowserException {

        try {

            Chrome.findElementByCss(COMMENT_INPUT_CSS).sendKeys(text);
            Chrome.findElementByCss(COMMENT_CONFIRM_CSS).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to add comment ", e);
        }
    }

    public static List<WebElement> getComments(String text) throws BrowserException {

        List<WebElement> comments = null;

        try {

            comments = Chrome.findElementsByCss(CURRENT_COMMENTS_CSS);

        } catch (WebElementException e) {

            throw new BrowserException("Unable to get comment ", e);
        }

        return comments;
    }

    /**
     * Upload an image to the card by link
     *
     * @param link url of image
     * @throws BrowserException
     */
    public static void uploadImage(String link) throws BrowserException {

        try {

            Chrome.findElementByCss(ADD_ATTACHMENT_BUTTON_CSS).click();
            Chrome.findElementById(ADD_LINK_ID).sendKeys(link);
            Chrome.findElementByCss(ATTACHMENT_CONFIRM_CSS).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to upload image ", e);
        }
    }

    public static List<WebElement> getImageAttachments() throws BrowserException {

        List<WebElement> attachments = null;

        try {

            attachments = Chrome.findElementsByCss(ATTACHMENT_THUMBNAIL_PREVIEW_CSS);

        } catch (WebElementException e) {

            throw new BrowserException("Unable to get image ", e);
        }

        return attachments;
    }



}
