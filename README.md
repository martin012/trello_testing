
#### Prerequisites
* Maven
* ChromeDriver

#### How to run tests
##### Edit profile information: `com.trello.testing.common.GlobalVariables.java`
    // Profile information for account
    public static final String MAIL = "";
    public static final String PASSWORD = "";
    public static final String KEY = "";
    public static final String TOKEN = "";

Run all tests

    mvn clean install