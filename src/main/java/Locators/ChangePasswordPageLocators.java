package Locators;

public class ChangePasswordPageLocators {

    /** xpath */ public static final String CHANGE_PASSWORD_HEADING = "//h5[normalize-space()='Change Password']";
    /** id */ public static final String OLD_PASSWORD_TEXT_FIELD = "old_password";
    /** name */ public static final String NEW_PASSWORD_TEXT_FIELD = "new_password";
    /** id */ public static final String CONFIRM_PASSWORD_TEXT_FIELD = "confirm_password";
    /** xpath */ public static final String UPDATE_BUTTON = "//button[normalize-space()='Update']";
    /** xpath */ public static final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";
    /** xpath */ public static final String SUCCESS_POPUP = "//div[@role='alert']";
    /** xpath */ public static final String SUCCESS_POPUP_CLOSE = "//div[@role='alert']//button";
    /** id */ public static final String OLD_PASSWORD_ERROR = "old_password-error";
    /** id */ public static final String NEW_PASSWORD_ERROR = "new_password-error";
    /** id */ public static final String CONFIRM_PASSWORD_ERROR = "confirm_password-error";
    /** css */ public static final String OLD_PASSWORD_WRONG_ERROR = ".error-message";

    public static final String SUCCESS_MESSAGE = "Your password was successfully updated!";
    public static final String NEW_PASSWORD_LENGTH_ERROR = "New password must be at least 8 characters long.";
    public static final String CONFIRM_PASSWORD_LENGTH_ERROR = "Confirm password must be at least 8 characters long.";
    public static final String OLD_PASSWORD_WRONG_MSG = "Old password is incorrect";
    public static final String NEW_PASSWORD_MISMATCH_ERROR = "The new passwords do not match";
}
