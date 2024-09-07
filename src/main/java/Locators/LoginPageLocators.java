package Locators;

public class LoginPageLocators {

    /** xpath */ public static final String EMAIL_TEXT_FIELD = "//input[@itemid='username']";
    /** xpath */ public static final String PASSWORD_TEXT_FIELD = "//input[@itemid='password']";
    /** xpath */ public static final String SIGN_IN_BUTTON = "//button[normalize-space()='Sign In']";
    /** xpath */ public static final String INVALID_CREDENTIALS_ERROR_MSG = "//p[@class='text-center' and contains(text(),'Invalid')]";
    public static final String USERNAME = "admin@demo.com";
    public static final String PASSWORD = "Galaxy@123";
}
