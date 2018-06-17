package com.trello.testing.rest.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequestWithBody;

import java.io.IOException;
import java.util.Map;

/**
 * This class allows to make http calls.
 */
public class RequestClient {

    public static <T> HttpResponse<T> postForObject(String url, Map<String, Object> parameters, Class<T> className) throws UnirestException {

        HttpResponse<T> response = Unirest.post(url)
            .queryString(parameters)
            .asObject(className);

        return response;
    }

    public static HttpRequestWithBody deleteObject(String url, Map<String, Object> parameters) throws UnirestException {

        HttpRequestWithBody requestWithBody = Unirest.delete(url)
                .queryString(parameters);

        return requestWithBody;
    }

    static  {

        Unirest.setObjectMapper(new ObjectMapper() {

            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}