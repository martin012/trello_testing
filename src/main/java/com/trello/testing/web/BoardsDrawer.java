package com.trello.testing.web;

import org.openqa.selenium.By;
import com.trello.testing.web.browser.Chrome;

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
     */
    public static void findBoard(String boardName) {
        Chrome.getDriver().findElement(By.cssSelector(BOARDS_MENU_CSS)).click();
        Chrome.getDriver().findElement(By.cssSelector(SEARCH_BOARDS_CSS)).sendKeys(boardName);
        Chrome.getDriver().findElement(By.cssSelector(BOARD_TITLE_CSS)).click();
    }

}
