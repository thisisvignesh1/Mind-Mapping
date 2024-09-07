package Locators;

public class FrontEndLoginLocators {

    /** xpath */ public static final String GALAXY_LOGO = "//a[@class='navbar-brand']";
    /** xpath */ public static final String FEEDBACK_BTN = "//a[normalize-space()='Feedback']";
    /** xpath */ public static final String NAME_TEXT_FIELD = "//input[@id='name']";
    /** xpath */ public static final String EMAIL_TEXT_FIELD = "//input[@id='email']";
    /** xpath */ public static final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";
    /** xpath */ public static final String NEXT_BUTTON = "//button[normalize-space()='Next']";
    /** id */ public static final String ERROR_MSG_FOR_NAME_FIELD = "name-error";
    /** id */ public static final String ERROR_MSG_FOR_EMAIL_FIELD = "email-error";
    /** xpath */ public static final String EMPTY_NAME_ERROR_MSG = "//input[@id='name']/following-sibling::div";
    /** xpath */ public static final String EMPTY_EMAIL_ERROR_MSG = "//input[@id='email']/following-sibling::div";
    /** xpath */ public static final String HEADIG_TEXT = "//mark[@class='custom-highlight']";

    public static final String ALPHABETS_ONLY_ERROR_MESSAGE = "Please enter only alphabets.";
    public static final String ERROR_MESSAGE_FOR_EMAIL = "Please enter a valid email address.";
    public static final String ERROR_MESSAGE_FOR_EMPTY_NAME_FIELD = "This field is required.";
    public static final String ERROR_MESSAGE_FOR_EMPTY_EMAIL_FIELD = "This field is required";
}
