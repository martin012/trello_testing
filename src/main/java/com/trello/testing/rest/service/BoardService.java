package com.trello.testing.rest.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trello.testing.common.GlobalVariables;
import com.trello.testing.exceptions.rest.BadAuthServiceException;
import com.trello.testing.exceptions.rest.ServiceException;
import com.trello.testing.rest.http.RequestClient;
import com.trello.testing.core.dto.BoardDto;

import java.util.HashMap;
import java.util.Map;


/**
 * Manipulate with resource 'Boards' by com.trello.testing.rest api
 */
public class BoardService {

    private static final String BOARDS_URL = GlobalVariables.BASE_API_URL + "boards/";

    /**
     * Create a new board
     *
     * @param queryParams       query parameters in url, e.g. name of board
     * @return                  http response
     * @throws UnirestException
     */
    public static HttpResponse<BoardDto> createBoard(Map<String, Object> queryParams) throws BadAuthServiceException, ServiceException {

        queryParams.putAll(GlobalVariables.authParameters);

        return RequestClient.postForObject(BOARDS_URL, queryParams, BoardDto.class);
    }

    /**
     * Delete the board by id
     *
     * @param id            id of the board
     * @param queryParams   query parameters of url
     * @return              http response
     * @throws UnirestException
     */
    public static HttpResponse<String> deleteBoard(String id, Map<String, Object> queryParams) throws BadAuthServiceException, ServiceException {

        queryParams.putAll(GlobalVariables.authParameters);

        return RequestClient.deleteObject(BOARDS_URL + id, queryParams);
    }

    public static HttpResponse<String> deleteBoard(String id) throws BadAuthServiceException, ServiceException {

        return deleteBoard(id, new HashMap<String, Object>());
    }

}
