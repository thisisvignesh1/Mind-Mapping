package Locators;

public class DashboardPageLocators {

    /** xpath */ public static final String WELCOME_PAGE_TEXT = "//h3[contains(text(),'Welcome back')]";
    /** xpath */ public static final String DASHBOARD_PAGE_TAB = "//span[normalize-space()='Dashboard']";
    /** xpath */ public static final String PARTICIPANTS_TAB = "//div[@class='card overflow-hidden']//p[contains(text(), 'Participants')]";
    /** xpath */ public static final String GROUPS_TAB = "//div[@class='card overflow-hidden']//p[contains(text(), 'Groups')]";
    /** xpath */ public static final String RECENT_PARTICIPANTS_TABLE = "//h5[normalize-space()='Recent Participants']";
    /** xpath */ public static final String NAME_COLUMN = "//th[contains(text(),'Name')]";
    /** xpath */ public static final String EMAIL_COLUMN = "//th[contains(text(),'Email')]";
    /** xpath */ public static final String CREATED_AT_COLUMN = "//th[contains(text(),'Created at')]";
    /** xpath */ public static final String PARTICIPANTS_COUNT = "//div[@class='card overflow-hidden']//p[contains(text(),'Participant')]/../h5";
    /** xpath */ public static final String PARTICIPANTS_FIRST_ROW_NAME = "//tbody/tr[1]/td[1]/div[1]/p[1]//a";
    /** xpath */ public static final String PARTICIPANTS_FIRST_ROW_EMAIL = "//tbody/tr[1]/td[2]//span";
    /** xpath */ public static final String GROUPS_COUNT = "//div[@class='card overflow-hidden']//p[contains(text(),'Groups')]/..//h5";


    public static final String DASHBOARD_WELCOME_TEXT = "Welcome back, Super Admin!";
}
