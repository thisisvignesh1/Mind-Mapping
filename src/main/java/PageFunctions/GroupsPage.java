package PageFunctions;

/**
 * @author Gokul - GWL
 * @company Galaxy Weblinks May 23, 2024
 */

import Locators.FrontEndLoginLocators;
import Locators.GroupsPageLocators;
import Locators.MindMappingLocators;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;

public class GroupsPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static MindMappingPage mindMappingPage = new MindMappingPage();
    static CommonPage commonPage = new CommonPage();
    boolean flag;
    String errorMessage;
    String groupName = MindMappingLocators.randomAlphabets;
    String url;
    String startWord;
    String endWord;
    static String reportSpecificUrl;

    /**
     * This method verifies navigation to group tab
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNavigationToGroupTab() {
        flag = webDB.waitForElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked groups tab");
                flag = webDB.waitForElement(GroupsPageLocators.GROUPS_ADD_TAB, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_ADD_TAB, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked groups add tab");
                        flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Group name text field is visible. Navigated to Groups add page");
                        }else{
                            log.logging("info", "Issue while navigating to groups add page");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies group name error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyGroupNameErrorMsg() {
        flag = verifyNavigationToGroupTab();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, "123",  ElementType.Xpath);
                if(flag){
                    log.logging("info", "Numerical values entered on text field");
                    flag = webDB.clickAnElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        flag = webDB.waitForElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                        if(flag){
                            errorMessage = webDB.getTextFromElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                            log.logging("info", "Error message shown is: "+errorMessage);
                            if(errorMessage.equals(MindMappingLocators.ALPHABET_ONLY_ERROR_MESSAGE)){
                                log.logging("info", "Error message is verified");
                            }else{
                                log.logging("info", "Issue on verifying error message");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies valid group name updation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyValidGroupNameUpdate() throws InterruptedException {
        flag = verifyNavigationToGroupTab();
        if(flag) {
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Valid group name entered on text field");
                    flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked submit button");
                            flag = webDB.waitForElement(GroupsPageLocators.GROUP_LIST_HEADING, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Navigated to groups list page");
                                Thread.sleep(4000);
                                String firstRowGroupName = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME,ElementType.Xpath);
                                if(firstRowGroupName.equals(groupName)){
                                    log.logging("info","Created group is updated on group list");
                                    flag = mindMappingPage.deleteFirstRowInList();
                                }else{
                                    log.logging("fail","Issue on group updation");
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies group name cancel
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyGroupNameCancel() throws InterruptedException {
        flag = verifyNavigationToGroupTab();
        if(flag) {
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Valid group name entered on text field");
                    flag = webDB.waitForElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                        if(flag) {
                            log.logging("info", "Clicked cancel button");
                            flag = webDB.waitForElement(GroupsPageLocators.GROUP_LIST_HEADING, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Navigated to groups list page");
                                Thread.sleep(4000);
                                String firstRowGroupName = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME,ElementType.Xpath);
                                if(firstRowGroupName.equals(groupName)){
                                    log.logging("fail","Created group is updated on group list even clicked cancel");
                                    //flag = mindMappingPage.deleteFirstRowInList();
                                    flag = false;
                                }else{
                                    log.logging("info","Group name not updated successfully");
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method navigates to groups list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNavigatesToGroupsListPage() {
        flag = webDB.waitForElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked groups tab");
                flag = webDB.waitForElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked list tab");
                        flag = webDB.waitForElement(GroupsPageLocators.GROUP_LIST_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Navigated to group list page");
                        }else{
                            log.logging("fail", "Issue while navigating to list page");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add button in list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAddButtonInListPage() {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
                //flag = webDB.clickAnElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to Groups add button");
                    }else{
                        log.logging("fail", "Issue while navigating from list to add tab");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add to create new group page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean  verifyAddToCreateNewGrp() throws InterruptedException {
        flag = verifyAddButtonInListPage();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered valid group name");
                    flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON,ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON,ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked submit button");
                            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Navigated to groups list page");
                                Thread.sleep(2000);
                                String name = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if(name.equals(groupName)){
                                    log.logging("info", "Created group name exists on list");
                                }else{
                                    log.logging("fail", "Issue while creating group");
                                    flag =false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add button to cancel new group
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAddButtonToCancelNewGroup() {
        flag = verifyAddButtonInListPage();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered valid group name");
                    flag = webDB.waitForElement(MindMappingLocators.CANCEL_BUTTON,ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON,ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked cancel button");
                            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Navigated to groups list page");
                                String name = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if(name.equals(groupName)){
                                    log.logging("fail", "Group got created which is not the case");
                                    flag = mindMappingPage.deleteFirstRowInList();
                                    flag =false;
                                }else{
                                    log.logging("info", "Group not created as expected");
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search box inlist page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifySearchBoxInListPage() throws InterruptedException {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            Thread.sleep(2000);
            String name = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
            log.logging("info", "First row group name is: "+name);
            flag = webDB.waitForElement(MindMappingLocators.SEARCH_BOX, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(MindMappingLocators.SEARCH_BOX, name, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Group name is sent to search box");
                    Thread.sleep(3000);
                    flag = webDB.waitForElement(MindMappingLocators.TABLE_ROWS, ElementType.Xpath);
                    if(flag){
                        List<WebElement> elements = webDB.getDriver().findElements(By.xpath(MindMappingLocators.TABLE_ROWS));
                        int numberOfElements = elements.size();
                        if(numberOfElements == 1){
                            log.logging("info", "Shows only one row as expected");
                        }else{
                            log.logging("fail", "Issue while showing searched content");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies default word connection count
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDefaultWordConnectionCount() {
        flag = verifyNavigationToGroupTab();
        if(flag){
            flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
            if(flag){
                log.logging("info", "Group name has been passed to text field");
                flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked submit button");
                        flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
                        if(flag){
                            String connectionCount = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
                            if(connectionCount.equals("0")){
                                log.logging("info", "Default connection count is: "+connectionCount);
                                flag = mindMappingPage.deleteFirstRowInList();
                            }else{
                                log.logging("fail", "Issue on default connection count");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies clicking word connection and verifies popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyClickWordConnectionAndVerifyPopup() {
        flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
        if (flag) {
            flag = webDB.clickAnElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked default word connection");
                flag = webDB.waitForElement(GroupsPageLocators.WORD_CONECTION_POPUP, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Popup for word connection is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies popup on word connection
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPopupOnWordConnection() throws InterruptedException {
        flag = verifyNavigationToGroupTab();
        if (flag) {
            flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Group name has been passed to text field");
                flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked submit button");
                        flag = verifyClickWordConnectionAndVerifyPopup();
                        if(flag){
                            flag = webDB.waitForElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked assign button from popup");
                                    flag = webDB.waitForElement(GroupsPageLocators.ASSIGN_POPUP_CONFIRM_OK, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(GroupsPageLocators.ASSIGN_POPUP_CONFIRM_OK, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Clicked confirm from popup");
                                            Thread.sleep(3000);
                                            String text = webDB.getTextFromElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
                                            if (text.equals("Unassign")) {
                                                log.logging("info", "Status has been changed as expected");
                                                flag = webDB.waitForElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
                                                if(flag){
                                                    flag = webDB.clickAnElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
                                                }
                                            } else {
                                                log.logging("fail", "Issue whie changing status");
                                                flag = false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies word popup connection count
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyWordPopupConnectionCount() throws InterruptedException {
        flag = verifyPopupOnWordConnection();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
                if(text.equals("1")){
                    log.logging("info", "Connection count has been increased from 0 to 1");
                }else{
                    log.logging("fail", "Issue on connection count");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies default status of list item
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDefaultStatusOfListItem() {
        flag = verifyNavigationToGroupTab();
        if (flag) {
            flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Group name has been passed to text field");
                flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked submit button");
                        flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                        if(flag){
                            String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                            if(text.equals("Active")){
                                log.logging("info", "Default status is active as expected");
                            }else{
                                log.logging("fail", "Issue on default  status");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies active to inactive status change
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyActiveToInactiveStatusChange() throws InterruptedException {
        flag = verifyDefaultStatusOfListItem();
        if(flag){
            flag = webDB.clickAnElement(GroupsPageLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked status button");
                flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP_CONFIRM, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.STATUS_POPUP_CONFIRM, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked confirm button from popup");
                            Thread.sleep(4000);
                            webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                            String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                            if(text.equals("In active")) {
                                log.logging("info", "Status has been changed from active to inactive");
                            }else{
                                log.logging("info", "Issue while changing status");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies creation date in list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCreationDateInList() {
        flag = verifyDefaultStatusOfListItem();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.GROUP_CREATED_DATE, ElementType.Xpath);
            if(flag){
                String date = webDB.getTextFromElement(GroupsPageLocators.GROUP_CREATED_DATE, ElementType.Xpath);
                log.logging("info", "Date extracted from method is "+commonPage.getFormattedDate());
                if(date.equals(commonPage.getFormattedDate())) {
                    log.logging("info", "Creation date is verified");
                }else{
                    log.logging("fail", "Issue on verifying date");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies copy clipboard link
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCopyClipboardLink() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_URL, ElementType.Xpath);
            if(flag){
                Thread.sleep(3000);
                flag = webDB.javaScriptClickAnElement(GroupsPageLocators.TABLE_FIRST_ROW_URL, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked URL copy link");
                    Thread.sleep(3000);
                    JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
                    url = (String) js.executeScript("return document.getElementById('copy_url102').textContent;");
                    log.logging("info", "url is: "+url);
                    if(url.equals("")){
                        log.logging("fail", "There is no url");
                        flag = false;
                    }else{
                        log.logging("info", "Url got extracted as expected");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit button redirection
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEditButtonRedirection() {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement("//tbody//tr[1]//td[6]//a", ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement("//tbody//tr[1]//td[6]//a", ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked edit icon");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to Add page as expected");
                    }else{
                        log.logging("fail", "Issue while navigating to add page");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies edit confirm in list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEditConfirmInList() {
        flag = verifyDefaultStatusOfListItem();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.FIRST_ROW_EDIT_BTN, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(GroupsPageLocators.FIRST_ROW_EDIT_BTN, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked edit icon");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Navigated to Add page as expected");
                        flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clearTextField(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName + " Edited", ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Edited name is sent to text field");
                                    flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Clicked submit button");
                                            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                            if (flag) {
                                                log.logging("info", "Navigated to groups list page");
                                                String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                                if (text.equals(groupName + " Edited")) {
                                                    log.logging("info", "Name updated successfully");
                                                } else {
                                                    log.logging("false", "Issue while updating name");
                                                    flag = false;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Verifies edit cancel in list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEditCancelList() {
        flag = verifyDefaultStatusOfListItem();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.FIRST_ROW_EDIT_BTN, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(GroupsPageLocators.FIRST_ROW_EDIT_BTN, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked edit icon");
                    if(flag) {
                        flag = webDB.clearTextField(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Cleared text field");
                            flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, groupName + " Edited", ElementType.Xpath);
                            if (flag) {
                                flag = webDB.waitForElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                                if (flag) {
                                    flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Clicked cancel button");
                                        flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                        if (flag) {
                                            String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                            if (text.equals(groupName + " Edited")) {
                                                log.logging("fail", "Cancel button not worked as expected");
                                                flag = false;
                                            }else{
                                                log.logging("info", "Name not updated as expected");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Verifies delete popup in list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupInList() {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Delte popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        }
                    }else{
                        log.logging("fail", "Issue on delete popup visibility");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * Verifies delete confirm on group
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeleteConfirmOnGroup() throws InterruptedException {
        flag = verifyDefaultStatusOfListItem();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delte popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked popup delete confirm");
                                Thread.sleep(3000);
                                String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if (text.equals(groupName)){
                                    log.logging("fail", "List item did not get deleted");
                                    flag = false;
                                }else{
                                    log.logging("info", "List item deleted successfully");
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete cancel on group
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeleteCancelOnGrp() throws InterruptedException {
        flag = verifyDefaultStatusOfListItem();
        if(flag) {
            flag = webDB.waitForElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delte popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked popup delete cancel");
                                Thread.sleep(3000);
                                String text = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                if(text.equals(groupName)){
                                    log.logging("info", "Cancel button verified");
                                    flag = webDB.waitForElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
                                    if(flag) {
                                        flag = webDB.clickAnElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
                                        if (flag) {
                                            flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                                            if (flag) {
                                                flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                                                Thread.sleep(2000);
                                            }
                                        }
                                    }
                                }else{
                                    log.logging("fail", "Issueon cancel button");
                                    flag = false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies navigation to mind mapping front end page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean navigateToMindMappingFrontEnd() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag){
            flag = webDB.openNewTabWithUrl(url);
            if(flag){
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Navigated to front end login page as expected");
                }else{
                    log.logging("fail", "Issue while navigating to front end page");
                    webDB.switchToChildWindow();
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method returns start word from word connection popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public String returnStartWordFromWordConnectionPopup() {
        flag = verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(GroupsPageLocators.TABLE_FIRST_ROW_WORD_CONNECTION, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked word connection link");
                    flag = webDB.waitForElement(GroupsPageLocators.ASSIGNED_START_WORD_IN_POPUP, ElementType.Xpath);
                    if(flag){
                        startWord = webDB.getTextFromElement(GroupsPageLocators.ASSIGNED_START_WORD_IN_POPUP, ElementType.Xpath);
                        log.logging("info", "Extracted start word is: "+startWord);
                    }
                }
            }
        }
        return startWord;
    }

    /**
     * This method returns end word from word connection popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public String returnEndWordFromWordConnectionPopup() {
        flag = webDB.waitForElement(GroupsPageLocators.ASSIGNED_END_WORD_IN_POPUP, ElementType.Xpath);
        if(flag){
            endWord = webDB.getTextFromElement(GroupsPageLocators.ASSIGNED_END_WORD_IN_POPUP, ElementType.Xpath);
            log.logging("info", "Extracted end word is: "+endWord);
            flag = webDB.waitForElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Closed word connection popup");
                }
            }
        }
        return endWord;
    }

    /**
     * This method verifies group creation specific to reports page
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean createGroupSpecificToReportsPage() throws InterruptedException {
        flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, "Demo Testing Group", ElementType.Xpath);
        if(flag){
            log.logging("info", "Sent demo testing group name on text field");
            flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked submit button");
                    flag = webDB.waitForElement(GroupsPageLocators.GROUP_LIST_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to groups list page");
                        Thread.sleep(4000);
                        String firstRowGroupName = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME,ElementType.Xpath);
                        if(firstRowGroupName.equals("Demo Testing Group")){
                            log.logging("info","Created group is updated on group list");
                        }else{
                            log.logging("fail","Issue on group updation");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method assigns word specific to reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean assignWordsSpecificForReportsPage() throws InterruptedException {
        flag = verifyClickWordConnectionAndVerifyPopup();
        if(flag){
            flag = webDB.waitForElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked assign button from popup");
                    flag = webDB.waitForElement(GroupsPageLocators.ASSIGN_POPUP_CONFIRM_OK, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.javaScriptClickAnElement(GroupsPageLocators.ASSIGN_POPUP_CONFIRM_OK, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked confirm from popup");
                            Thread.sleep(3000);
                            String text = webDB.getTextFromElement(GroupsPageLocators.WORD_CONNECTION_POPUP_FIRST_ASSIGN, ElementType.Xpath);
                            if (text.equals("Unassign")) {
                                log.logging("info", "Status has been changed as expected");
                                flag = webDB.waitForElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.clickAnElement(GroupsPageLocators.WORD_CONNECTION_POPUP_CLOSE, ElementType.Xpath);
                                }
                            } else {
                                log.logging("fail", "Issue whie changing status");
                                flag = false;
                            }
                            }
                        }
                    }
                }
            }
        return flag;
    }

    /**
     * This method copies url specific to reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean copyUrlSpecificToReportsPage() throws InterruptedException {
        flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_URL, ElementType.Xpath);
        if(flag){
            Thread.sleep(3000);
            flag = webDB.javaScriptClickAnElement(GroupsPageLocators.TABLE_FIRST_ROW_URL, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked URL copy link");
                Thread.sleep(3000);
                String attribute = webDB.getAttributeFromElement(GroupsPageLocators.FIRST_ROW_URL_LINK, ElementType.Xpath, "id");
                JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
                reportSpecificUrl = (String) js.executeScript("return document.getElementById('"+attribute+"').textContent;");
                log.logging("info", "url is: "+reportSpecificUrl);
                if(reportSpecificUrl.equals("")){
                    log.logging("fail", "There is no url");
                    flag = false;
                }else{
                    log.logging("info", "Url got extracted as expected");
                }
            }
        }
        return flag;
    }

}
