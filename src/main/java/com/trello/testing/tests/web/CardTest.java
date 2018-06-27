package com.trello.testing.tests.web;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trello.testing.core.dto.BoardDto;
import com.trello.testing.core.dto.ListDto;
import com.trello.testing.exceptions.rest.BadAuthServiceException;
import com.trello.testing.exceptions.rest.ServiceException;
import com.trello.testing.exceptions.web.BrowserException;
import com.trello.testing.web.check.TextCheck;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import com.trello.testing.rest.service.BoardService;
import com.trello.testing.rest.service.ListService;
import com.trello.testing.web.BoardsDrawer;
import com.trello.testing.web.Card;
import com.trello.testing.web.ListCards;
import com.trello.testing.web.LoginPage;
import com.trello.testing.web.browser.Chrome;

import java.util.HashMap;
import java.util.UUID;

public class CardTest {

    private String BOARD_NAME = "trello_board" + UUID.randomUUID();
    private String LIST_NAME = "trello_list";
    private String CARD_NAME = "trello_card";
    private String SIMPLE_TEXT = "text text text";
    private String IMAGE_LINK = "https://www.gstatic.com/webp/gallery3/2.png";

    @BeforeTest
    public void setUp() throws UnirestException, BadAuthServiceException, ServiceException {

        // Create a board by com.trello.testing.rest api
        HashMap<String, Object> boardParameters = new HashMap<String, Object>();
        boardParameters.put("name", BOARD_NAME);
        HttpResponse<BoardDto> boardResponse = BoardService.createBoard(boardParameters);

        // Create a list on the board by com.trello.testing.rest api
        HashMap<String, Object> listParameters = new HashMap<String, Object>();
        listParameters.put("idBoard", boardResponse.getBody().getId());
        listParameters.put("name", LIST_NAME);
        HttpResponse<ListDto> listResponse = ListService.createList(listParameters);
    }

    @Test
    public void createCard() throws ServiceException, BrowserException {

        // Log in to Trello
        LoginPage.loginWithCheck();

        // Find the board
        BoardsDrawer.findBoard(BOARD_NAME);

        // Add a new card on the list
        ListCards.addCard(LIST_NAME, CARD_NAME);

        // Open the card on the list
        WebElement list = ListCards.getList(LIST_NAME);
        Card.openCard(list, CARD_NAME);

        // Edit the card

        // Add description and verify
        Card.addDescription(SIMPLE_TEXT);
        TextCheck.checkText(Card.getDescription(), SIMPLE_TEXT, "Description is not added");

        // Add checklist and verify
        Card.addChecklist(SIMPLE_TEXT);
        TextCheck.checkAnyMatch(Card.getChecklistsTitles(), SIMPLE_TEXT, "Checklist is not added");

        // Upload image and verify
        Card.uploadImage(IMAGE_LINK);
        Assert.assertEquals(Card.getImageAttachments().size(), 1, "Image is not added");

        // Add comment and verify
        Card.addComment(SIMPLE_TEXT);
        Assert.assertEquals(Card.getComments(SIMPLE_TEXT).size(), 1, "Comment is not added");
    }

    @AfterTest
    public void cleanUp() {

        Chrome.quitDriver();
    }
}
