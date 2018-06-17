package com.trello.testing.rest.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trello.testing.common.GlobalVariables;
import com.trello.testing.rest.http.RequestClient;
import com.trello.testing.core.dto.ListDto;

import java.util.Map;

/**
 * Manipulate with resource 'Lists' by com.trello.testing.rest api
 */
public class ListService {

    private static final String LISTS_URL = GlobalVariables.BASE_API_URL + "lists/";

    /**
     * Create a new list
     *
     * @param queryParams       query parameters in url, e.g. name of board
     * @return
     * @throws UnirestException
     */
    public static HttpResponse<ListDto> createList(Map<String, Object> queryParams) throws UnirestException {

        queryParams.putAll(GlobalVariables.authParameters);

        return RequestClient.postForObject(LISTS_URL, queryParams, ListDto.class);
    }
}