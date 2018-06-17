package com.trello.testing.common;

import java.util.HashMap;

/**
 * Global variables for whole project
 */
public class GlobalVariables {

    // Profile information for account
    public static final String MAIL = "";
    public static final String PASSWORD = "";
    public static final String KEY = "";
    public static final String TOKEN = "";

    public static final HashMap<String, Object> authParameters =  new HashMap<String, Object>() {{
        put("key", GlobalVariables.KEY);
        put("token", GlobalVariables.TOKEN);
    }};

    // URL's
    public static final String BASE_URL = "https://api.trello.com/";
    public static final String BASE_API_URL = BASE_URL + "1/";
}
