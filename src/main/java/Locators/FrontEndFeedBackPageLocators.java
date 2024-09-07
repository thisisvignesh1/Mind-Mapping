package Locators;

public class FrontEndFeedBackPageLocators {

    /** xpath */ public static final String FEEDBACK_HEADING = "//p[@class='slider-subtext']";
    /** id */ public static final String NAME_TEXT_FIELD = "name";
    /** id */ public static final String EMAIL_TEXT_FIELD = "email";
    /** id */ public static final String FEEDBACK_TEXT_AREA = "message";
    /** xpath */ public static final String CANCEL_BUTTON = "//a[normalize-space()='Cancel']";
    /** xpath */ public static final String SUBMIT_BUTTON = "//button[@id='feedbackform']";
    /** id */ public static final String INVALID_NAME_ERROR = "name-error";
    /** id */ public static final String INVALID_EMAIL_ERROR = "email-error";
    /** id */ public static final String FEEDBACK_ERROR = "message-error";
    /** xpath */ public static final String FEEDBACK_SUCCESS_MSG = "//div[@role='alert']";
    /** xpath */ public static final String FEEDBACK_SUCCESS_CLOSE_BTN = "//button[@class='btn-close']";

    public static final String FEEDBACK_SUBTEXT = "Please share your feedback";
    public static final String INVALID_NAME_ERROR_MSG = "Please enter only alphabets.";
    public static final String INVALID_EMAIL_ERROR_MSG = "Please enter a valid email address.";
    public static final String RANDOM_FEEDBACK = "This is the test feedback";
    public static final String EMPTY_TEXT_FIELD_MESSAGE = "This field is required";
    public static final String NAME_LENGHT_ERROR_MSG = "Name must be at least 3 characters long.";
    public static final String SUCCESS_FEEDBACK_MSG = "Your feedback sent successfully!";
}
