package com.trello.testing.tests.rest;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trello.testing.core.dto.BoardDto;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import com.trello.testing.rest.service.BoardService;
import org.testng.Assert;

import java.util.HashMap;
import java.util.UUID;

public class BoardRestTest {

    private String BOARD_NAME = "trello_board" + UUID.randomUUID();
    BoardDto board;

    @Test
    public void createBoard() throws UnirestException {

        // Create a board by com.trello.testing.rest api
        HashMap<String, Object> boardParameters = new HashMap<String, Object>();
        boardParameters.put("name", BOARD_NAME);
        HttpResponse<BoardDto> boardResponse = BoardService.createBoard(boardParameters);
        board = boardResponse.getBody();

        // Check status of response
        Assert.assertEquals(boardResponse.getStatus(), HttpStatus.SC_OK);

        // Check values of created board
        Assert.assertFalse(board.getId().isEmpty());
        Assert.assertEquals(board.getName(), BOARD_NAME);
    }

    @AfterTest
    public void cleanUp() throws UnirestException {
        BoardService.deleteBoard(board.getId());
    }

}

