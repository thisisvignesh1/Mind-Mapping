package Locators;

public class GroupsPageLocators {

    /** xpath */ public static final String GROUPS_TAB = "//span[normalize-space()='Groups']";
    /** xpath */ public static final String GROUPS_ADD_TAB = "//span[contains(text(),'Groups')]/../following-sibling::ul//a[1]";
    /** xpath */ public static final String GROUPS_LIST_TAB = "//a[@href='/siteadmin/group_list/']";
    /** xpath */ public static final String GROUP_NAME_TEXT_FIELD = "//input[@id='group_name']";
    /** xpath */ public static final String TABLE_FIRST_ROW_NAME = "(//td[@class='sorting_1'])[1]";
    /** xpath */ public static final String TABLE_FIRST_ROW_WORD_CONNECTION = "//tbody//tr[1]//td[2]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_URL = "//tbody//tr[1]//td[3]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_STATUS= "//tbody//tr[1]//td[5]//a";
    /** xpath */ public static final String GROUP_LIST_HEADING = "//li[@class='breadcrumb-item active']";
    /** xpath */ public static final String WORD_CONECTION_POPUP = "//div[@class='modal-dialog modal-lg']//div[@class='modal-content']";
    /** xpath */ public static final String WORD_CONNECTION_POPUP_CLOSE = "//div[@class='modal-dialog modal-lg']//button[@aria-label='Close']";
    /** xpath */ public static final String WORD_CONNECTION_POPUP_FIRST_ASSIGN = "//div[@id='word_Model_wrapper']//div[2]//tbody//tr[1]//td[3]//a//span";
    /** xpath */ public static final String ASSIGN_POPUP_CONFIRM_OK = "//button[normalize-space()='Ok']";
    /** xpath */ public static final String GROUP_CREATED_DATE = "//tbody//tr[1]//td[4]";
    /** xpath */ public static final String FIRST_ROW_EDIT_BTN = "//tbody//tr[1]//td[6]//a";
    /** xpath */ public static final String ASSIGNED_START_WORD_IN_POPUP = "//span[contains(text(),'Unassign')]/../../..//td[1]";
    /** xpath */ public static final String ASSIGNED_END_WORD_IN_POPUP = "//span[contains(text(),'Unassign')]/../../..//td[2]";
    /** xpath */ public static final String FIRST_ROW_URL_LINK = "//tr[1]//td[3]//p";
}
