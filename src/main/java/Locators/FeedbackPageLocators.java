package Locators;

public class FeedbackPageLocators {

    /** xpath */ public static final String FEEDBACK_TAB = "//span[normalize-space()='Feedback']";
    /** xpath */ public static final String TABLE_FIRST_ROW_NAME = "(//td[@class='sorting_1'])[1]";
    /** xpath */ public static final String TABLE_FIRST_ROW_EMAIL = "//tbody//tr[1]//td[2]";
    /** xpath */ public static final String TABLE_FIRST_ROW_VIEW = "//tbody//tr[1]//td[3]//a";
    /** xpath */ public static final String FEEDBACK_POPUP_TITLE = "//div[@id='messageModal']//h5[contains(text(),'View Feedback')]";
    /** xpath */ public static final String FEEDBACK_POPUP_CLOSE = "//div[@id='messageModal']//button[@aria-label='Close']";
    /** xpath */ public static final String FEEDBACK_POPUP_OK = "//button[normalize-space()='Ok']";
    /** xpath */ public static final String FEEDBACK_IN_POPUP = "//div[@id='feedback_msg']";
    /** xpath */ public static final String TABLE_FIRST_ROW_CREATED_DATE = "//tbody//tr[1]//td[4]";
    /** xpath */ public static final String TABLE_FIRST_ROW_DELETE_ICON = "//tbody//tr[1]//td[5]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_DELETE_POPUP = "//div[@id='deleteModal']//div[@class='modal-header']";
    /** xpath */ public static final String DELETE_POPUP_CONFIRM = "//button[normalize-space()='Delete']";
    /** xpath */ public static final String DELETE_POPUP_CANCEL = "//button[normalize-space()='Cancel']";
    /** xpath */ public static final String DELETE_POPUP_MESSAGE = "//div[normalize-space()='Are you sure you want to delete this feedback?']";
    /** xpath */ public static final String DELETE_POPUP_CLOSE = "//div[@id='deleteModal']//span[@aria-hidden='true'][normalize-space()='×']";
    /** xpath */ public static final String SHOW_BUTTON_SELECTBOX = "//select[@name='feedback_list_table_length']";
    /** xpath */ public static final String TABLE_TOTAL_ROWS = "//tbody//tr";
    /** xpath */ public static final String SEARCH_BOX = "//input[@type='search']";
    /** xpath */ public static final String DASHBOARD_LINK = "//a[contains(text(),'Dashboard')]";
    /** xpath */ public static final String DASHBOARD_PAGE_HEADING = "//h5[normalize-space()='Recent Participants']";
    /** xpath */ public static final String NUMBER_OF_ITEMS = "//div[@id='feedback_list_table_info']";
    /** xpath */ public static final String NEXT_BUTTON = "//a[normalize-space()='Next']";
    /** xpath */ public static final String PREVIOUS_BUTTON = "//a[normalize-space()='Previous']";


    public static final String DELETE_POPUP_MESSAGE_TXT = "Are you sure you want to delete this feedback?";
}
