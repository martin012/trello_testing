package com.trello.testing.rest.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.trello.testing.exceptions.rest.BadAuthServiceException;
import com.trello.testing.exceptions.rest.ServiceException;
import org.apache.http.HttpStatus;

import java.io.IOException;
import java.util.Map;

/**
 * This class allows to make http calls.
 */
public class RequestClient {

    public static <T> HttpResponse<T> postForObject(String url, Map<String, Object> parameters, Class<T> className) throws BadAuthServiceException, ServiceException {

        HttpResponse<T> response = null;

        try {

            response = Unirest.post(url)
                .queryString(parameters)
                .asObject(className);

        } catch (UnirestException e) {

            String errorMessage = e.getMessage();

            if (errorMessage.contains(ErrorMessages.INVALID_KEY) || errorMessage.contains(ErrorMessages.INVALID_TOKEN)) {

                throw new BadAuthServiceException("Unauthorized: Access is denied! Bad key or token.");
            }

            throw new ServiceException("Request was not successful. Reason: " + errorMessage);
        }

        return response;
    }

    public static HttpResponse<String> deleteObject(String url, Map<String, Object> parameters) throws BadAuthServiceException, ServiceException {

        HttpResponse<String> response = null;

        try {

            response = Unirest.delete(url).queryString(parameters).asString();

            switch (response.getStatus()) {

                case HttpStatus.SC_OK:
                    return response;

                case HttpStatus.SC_UNAUTHORIZED:
                    throw new BadAuthServiceException("Unauthorized: Access is denied! Bad key or token.");

                default:
                    throw new ServiceException("Request was not successful. Reason: " + response.getBody());
            }

        } catch (UnirestException e) {

            throw new ServiceException("Invalid Request. Reason: " + e.getMessage());
        }
    }

    static {

        Unirest.setObjectMapper(new ObjectMapper() {

            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

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