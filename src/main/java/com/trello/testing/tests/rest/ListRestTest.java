package com.trello.testing.tests.rest;

import com.mashape.unirest.http.HttpResponse;
import com.trello.testing.core.dto.BoardDto;
import com.trello.testing.core.dto.ListDto;
import com.trello.testing.exceptions.rest.BadAuthServiceException;
import com.trello.testing.exceptions.rest.ServiceException;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.trello.testing.rest.service.BoardService;
import org.testng.Assert;
import com.trello.testing.rest.service.ListService;

import java.util.HashMap;
import java.util.UUID;

public class ListRestTest {

    private String BOARD_NAME = "trello_board" + UUID.randomUUID();
    private String LIST_NAME = "trello_list";
    BoardDto board;

    @BeforeTest
    public void setUp() throws BadAuthServiceException, ServiceException {

        // Create a board by com.trello.testing.rest api
        HashMap<String, Object> boardParameters = new HashMap<String, Object>();
        boardParameters.put("name", BOARD_NAME);
        HttpResponse<BoardDto> boardResponse = BoardService.createBoard(boardParameters);
        board = boardResponse.getBody();
    }

    @Test
    public void createList() throws BadAuthServiceException, ServiceException {

        // Create a list on the board by com.trello.testing.rest api
        HashMap<String, Object> listParameters = new HashMap<String, Object>();
        listParameters.put("idBoard", board.getId());
        listParameters.put("name", LIST_NAME);
        HttpResponse<ListDto> listResponse = ListService.createList(listParameters);
        ListDto list = listResponse.getBody();

        // Check status of response
        Assert.assertEquals(listResponse.getStatus(), HttpStatus.SC_OK);

        // Check values of created list
        Assert.assertFalse(list.getId().isEmpty());
        Assert.assertEquals(list.getIdBoard(), board.getId());
        Assert.assertEquals(list.getName(), LIST_NAME);
    }

    @AfterTest
    public void cleanUp() throws BadAuthServiceException, ServiceException {
        BoardService.deleteBoard(board.getId());
    }

}

