package Locators;

public class ReportsPageLocators {

    /** xpath */ public static final String REPORTS_TAB = "//span[contains(text(),'Reports')]";
    /** xpath */ public static final String REPORTS_HEADING = "//li[@class='breadcrumb-item active']";
    /** xpath */ public static final String FIRST_ROW_DELETE_ICON = "//tbody//tr[1]//td[4]//a";
    /** xpath */ public static final String FIRST_ROW_GROUP_NAME = "//tbody//tr[1]//td[1]";
    /** xpath */ public static final String DELETE_POPUP_CLOSE = "//button[@class='close']";
    /** link text*/ public static final String DASHBOARD_LINK = "Dashboard";
    /** xpath */ public static final String DEMO_TESTING_GROUP = "//td[contains(text(),'Demo Testing Group')]";
    /** xpath */ public static final String DEMO_TESTING_GRP_DELETE = "//td[contains(text(),'Demo Testing Group')]/following-sibling::td[3]//a";
    /** xpath */ public static final String DEMO_GROUP_DELETE = "//td[contains(text(),'Demo Testing Group')]/following-sibling::td[5]//a[2]";
    /** xpath */ public static final String DEMO_START_END_WORD = "//td[contains(text(),'Demo start word')]/following-sibling::td[4]//a[2]";
    /** xpath */ public static final String DEMO_START_END_WORD_IN_REPORT = "//td[contains(text(),'Demo Testing Group')]/following-sibling::td[1]";
    /** xpath */ public static final String DEMO_NO_OF_PARTICIPANTS = "//td[contains(text(),'Demo Testing Group')]/following-sibling::td[2]//a";
    /** id */ public static final String NUMBER_OF_REPORT = "report_datatable_info";
    /** xpath */ public static final String TOTAL_ROWS = "//tbody//tr";
    /** id */ public static final String PAGINATION_PREVIOUS = "report_datatable_previous";
    /** xpath */ public static final String CURRENT_PAGE = "//ul[@class='pagination']//li[2]";
    /** id */ public static final String PAGINATION_NEXT = "report_datatable_next";
    /** xpath */ public static final String FIRST_ROW_NUMBER_OF_PARTICIPANTS = "//tbody//tr[1]//td[3]//a";
    /** xpath */ public static final String DEMO_WORD_ON_PARTICIPANTS_PAGE = "//td[normalize-space()='Demo word']";
    /** link text */ public static final String REPORTS_LINK = "Reports";
    /** xpath */ public static final String FIRSTROW_PARTICIPANTS_NAME = "//tbody//tr[1]//td[1]";
    /** xpath */ public static final String SEARCH_BOX = "//input[@type='search']";
    /** xpath */ public static final String TABLE_ROWS = "//tbody//tr";
    /** id */ public static final String PARTICIPANT_PAGINATION_PREVIOUS = "participant_reports_previous";
    /** id */ public static final String PARTICIPANT_PAGINATION_NEXT = "participant_reports_next";
    /** id */ public static final String PARTICIPANT_FIRST_PAGE_INFO = "participant_reports_info";
    /** name */ public static final String PARTICIPANT_REPORT_COUNT = "participant_reports_length";

}
