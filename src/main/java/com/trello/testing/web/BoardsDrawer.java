package com.trello.testing.web;

import com.trello.testing.web.browser.Chrome;
import com.trello.testing.exceptions.web.BrowserException;
import com.trello.testing.exceptions.web.WebElementException;

/**
 * Manipulate with boards drawer in browser.
 */
public class BoardsDrawer {

    // Web elements on page
    private static final String BOARDS_MENU_CSS = "[class='header-btn header-boards js-boards-menu']";
    private static final String SEARCH_BOARDS_CSS = "[class='js-search-boards']";
    private static final String BOARD_TITLE_CSS = "[class='compact-board-tile-link-text details']";

    /**
     * Find the board and click on first result
     *
     * @param  boardName name of expected board
     * @throws BrowserException
     */
    public static void findBoard(String boardName) throws BrowserException {

        try {

            Chrome.findElementByCss(BOARDS_MENU_CSS).click();
            Chrome.findElementByCss(SEARCH_BOARDS_CSS).sendKeys(boardName);
            Chrome.findElementByCss(BOARD_TITLE_CSS).click();

        } catch (WebElementException e) {

            throw new BrowserException("Unable to find boards drawer", e);
        }
    }

}
