package Locators;

import org.apache.commons.lang3.RandomStringUtils;

public class MindMappingLocators {

    /** xpath */ public static final String MIND_MAPPING_SIDEBAR = "//span[@class='sidebar-text' and contains(text(),'Mind Mapping')]";
    /** xpath */ public static final String MIND_MAPPING_ADD = "//span[contains(text(),'Mind Mapping')]/../following-sibling::ul//a[1]";
    /** xpath */ public static final String START_TEXT_FIELD = "//input[@id='start_word']";
    /** xpath */ public static final String END_TEXT_FIELD = "//input[@id='end_word']";
    /** xpath */ public static final String SUBMIT_BUTTON = "//button[@type='submit']";
    /** xpath */ public static final String CANCEL_BUTTON = "//button[contains(text(),'Cancel')]";
    /** xpath */ public static final String ALPHABETS_ONLY_ERROR_MSG = "//div[@class='invalid-feedback' and contains(text(),'Please enter')]";
    /** id */ public static final String ERROR_MSG_FOR_START_FIELD = "start_word-error";
    /** id */ public static final String ERROR_MSG_FOR_END_FIELD = "end_word-error";
    /** xpath */ public static final String MIND_MAPPING_LIST_TABLE = "//table[@id='mapping_list_table']";
    /** xpath */ public static final String FIRST_TABLE_DELETE_ICON = "(//i[@class='bi bi-trash-fill'])[1]";
    /** xpath */ public static final String POPUP_DELETE_CONFRM = "//button[normalize-space()='Delete']";
    /** xpath */ public static final String MIND_MAPPING_LIST_TAB = "//a[@href='/siteadmin/mapping_list/']";
    /** xpath */ public static final String ADD_BUTTON = "//button[normalize-space()='Add']";
    /** xpath */ public static final String SEARCH_BOX = "//input[@type='search']";
    /** xpath */ public static final String TABLE_ROWS = "//tbody//tr";
    /** xpath */ public static final String TABLE_FIRST_ROW = "//tbody//tr[1]";
    /** xpath */ public static final String TABLE_FIRST_ROW_STATUS = "//tbody//tr[1]//span";
    /** xpath */ public static final String TABLE_FIRST_ROW_START_NAME = "(//td[@class='sorting_1'])[1]";
    /** xpath */ public static final String STATUS_POPUP = "//div[@id='update_status_Modal']//div[@class='modal-content']";
    /** xpath */ public static final String STATUS_POPUP_CONFIRM = "//button[normalize-space()='Update']";
    /** xpath */ public static final String STATUS_POPUP_CANCEL = "//div[@id='update_status_Modal']//button[@type='button'][normalize-space()='Cancel']";
    /** xpath */ public static final String TABLE_FIRST_ROW_EDIT = "//tbody//tr[1]//td[5]//a";
    /** xpath */ public static final String TABLE_FIRST_ROW_DELETE = "//tbody//tr[1]//td[5]//a[2]";
    /** xpath */ public static final String MIND_MAPPING_ADD_HEADING = "//h5[normalize-space()='Add']";
    /** xpath */ public static final String DELETE_POPUP = "//div[@id='deleteModal']//div[@class='modal-content']";
    /** xpath */ public static final String DELETE_POPUP_CANCEL = "//div[@id='deleteModal']//button[@type='button'][normalize-space()='Cancel']";

    public static final String ALPHABET_ONLY_ERROR_MESSAGE = "Please enter only alphabets.";
    public static final String EMPTY_FIELD_ERROR_MESSAGE = "This field is required";
    public static String randomAlphabets =  RandomStringUtils.randomAlphabetic(5);
}
