package com.trello.testing.common;

import org.testng.util.Strings;
import java.util.HashMap;

/**
 * Global variables for whole project
 */
public class GlobalVariables {

    // Profile information for account
    public static final String MAIL = System.getProperty("mail", "");
    public static final String PASSWORD =  System.getProperty("password", "");
    public static final String KEY =  System.getProperty("key", "");
    public static final String TOKEN = System.getProperty("token", "");

    public static final HashMap<String, Object> authParameters =  new HashMap<String, Object>() {{

        put("key", KEY);
        put("token", TOKEN);
    }};

    // URL's
    public static final String BASE_URL = "https://api.trello.com/";
    public static final String BASE_API_URL = BASE_URL + "1/";
}
