package Locators;

public class ProfilePageLocators {

    /** xpath */ public static final String PROFILE_HEADING = "//h5[normalize-space()='Profile']";
    /** id */ public static final String NAME_TEXT_FIELD = "name";
    /** xpath */ public static final String UPDATE_BUTTON = "//button[normalize-space()='Update']";
    /** xpath */ public static final String CANCEL_BUTTON = "//button[normalize-space()='Cancel']";
    /** css */ public static final String ADMIN_NAME = "h5[class='mb-1']";
    /** link text */ public static final String DASHBOARD_LINK = "Dashboard";
    /** xpath */ public static final String UPDATED_ADMIN_NAME_HEADER = "//h3[contains(text(),'Welcome back, Demo Admin Name!')]";
    /** xpath */ public static final String UPDATE_NAME_ALERT = "//div[@role='alert']";
    /** xpath */ public static final String NAME_ALERT_CLOSE = "//div[@role='alert']//button";

    public static final String ALERT_MSG = "Admin name updated successfully!";
}
