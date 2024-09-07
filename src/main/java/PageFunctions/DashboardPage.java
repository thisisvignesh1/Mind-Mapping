package PageFunctions;

import Locators.DashboardPageLocators;
import Locators.FrontEndLoginLocators;
import Locators.GroupsPageLocators;
import Locators.MindMappingLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class DashboardPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static FrontEndLoginPage frontEndLoginPage = new FrontEndLoginPage();
    static GroupsPage groupsPage = new GroupsPage();
    static MindMappingPage mindMappingPage = new MindMappingPage();
    boolean flag;
    String username;
    String email;

    /**
     * This method verifies dashboard heading text
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDashboadHeadingText() {
        flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
        if(flag){
            String text = webDB.getTextFromElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
            log.logging("info", "Text has been extracted");
            if(text.contains(DashboardPageLocators.DASHBOARD_WELCOME_TEXT)) {
                log.logging("info", "Welcome text has been verified");
            }else{
                log.logging("fail", "Issue on verifying welcome text");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies dashboard page contents
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDashboardPageContents() {
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_TAB, ElementType.Xpath);
        if(flag){
            log.logging("info", "Participants tab is visible");
            flag = webDB.waitForElement(DashboardPageLocators.GROUPS_TAB, ElementType.Xpath);
            if(flag){
                log.logging("info", "Groups tab is visible");
                flag = webDB.waitForElement(DashboardPageLocators.RECENT_PARTICIPANTS_TABLE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "All dashboard element is visible as expected");
                }else{
                    log.logging("fail", "Issue on verifying dashboard contents");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies recent participants contents
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyRecentParticipantsContents() {
        flag = webDB.waitForElement(DashboardPageLocators.NAME_COLUMN, ElementType.Xpath);
        if(flag){
            log.logging("info", "Name column is visible");
            flag = webDB.waitForElement(DashboardPageLocators.EMAIL_COLUMN, ElementType.Xpath);
            if(flag){
                log.logging("info", "Email column is visible");
                flag = webDB.waitForElement(DashboardPageLocators.CREATED_AT_COLUMN, ElementType.Xpath);
                if(flag){
                    log.logging("info", "All recent participants contents are visible");
                }else{
                    log.logging("fail", "Issue on verifying the contents");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies participant count for new user
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantCountForNewUser() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
        if(flag) {
            String initialCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
            log.logging("info", "Initial count of participants is: " + initialCount);
            int initialNumber = Integer.parseInt(initialCount);
            flag = frontEndLoginPage.verifyCopyClipboardLink();
            if (flag) {
                flag = webDB.openNewTabWithUrl(frontEndLoginPage.url);
                if (flag) {
                    log.logging("info", "Navigated to front end page");
                    flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, EmailGenerator.getRandomNme(), ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Valid name entered on name field");
                            flag = webDB.waitForElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, EmailGenerator.generateRandomEmail(), ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Valid email entered on email field");
                                    flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Clicked next button");
                                            Thread.sleep(3000);
                                            flag = webDB.switchToChildWindow();
                                            if (flag) {
                                                flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                if (flag) {
                                                    flag = webDB.clickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                    if (flag) {
                                                        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                        if (flag) {
                                                            String aftermathCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                            log.logging("info", "Aftermath count of participants is: " + aftermathCount);
                                                            int afterCount = Integer.parseInt(aftermathCount);
                                                            if (afterCount == initialNumber+1) {
                                                                log.logging("info", "Participants count has been increased by 1 as expected");
                                                            } else {
                                                                log.logging("fail", "Issue on verifying participants count");
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
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies participant count for existing user
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantCountForExistingUser() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        username = returnExistingName();
        email = returnExistingEmail();
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
        if(flag) {
            String initialCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
            log.logging("info", "Initial count of participants is: " + initialCount);
            int initialNumber = Integer.parseInt(initialCount);
            flag = frontEndLoginPage.verifyCopyClipboardLink();
            if (flag) {
                flag = webDB.openNewTabWithUrl(frontEndLoginPage.url);
                if (flag) {
                    log.logging("info", "Navigated to front end page");
                    flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, username, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Valid name entered on name field");
                            flag = webDB.waitForElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, email, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Valid email entered on email field");
                                    flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Clicked next button");
                                            Thread.sleep(3000);
                                            flag = webDB.switchToChildWindow();
                                            if (flag) {
                                                flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                if (flag) {
                                                    flag = webDB.clickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                    if (flag) {
                                                        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                        if (flag) {
                                                            String aftermathCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                            log.logging("info", "Aftermath count of participants is: " + aftermathCount);
                                                            int afterCount = Integer.parseInt(aftermathCount);
                                                            if (afterCount == initialNumber) {
                                                                log.logging("info", "Participants count has not been increased by 1 as expected");
                                                            } else {
                                                                log.logging("fail", "Issue on verifying participants count");
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
                }
            }
        }
        return flag;
    }

    /**
     * This method returns existing name
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public String returnExistingName() {
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_NAME, ElementType.Xpath);
        if(flag){
            username = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_NAME, ElementType.Xpath);
            if(username.equals("")){
                log.logging("fail", "Issue on fetching name");
            }else{
                log.logging("info", "Name got extracted");
            }
        }
        return username;
    }

    /**
     * This method returns existing email
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public String returnExistingEmail() {
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_EMAIL, ElementType.Xpath);
        if(flag){
            email = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_EMAIL, ElementType.Xpath);
            if(email.equals("")){
                log.logging("fail", "Issue on fetching email");
            }else{
                log.logging("info", "email got extracted");
            }
        }
        return email;
    }

    /**
     * This method verifies group count increase
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyGroupCountIncrease() throws InterruptedException {
        flag = webDB.waitForElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
        if(flag) {
            String beforeCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
            log.logging("info", "Initial count on group is " + beforeCount);
            flag = groupsPage.verifyAddToCreateNewGrp();
            if (flag) {
                log.logging("info", "Group created successfully");
                flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                if (flag) {
                    flag = webDB.javaScriptClickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
                        if (flag) {
                            String afterCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
                            log.logging("info", "After count on group is " + afterCount);
                            if (Integer.parseInt(afterCount) == Integer.parseInt(beforeCount) + 1) {
                                log.logging("info", "Groups count has been increased by 1 as expected");
                                flag = webDB.waitForElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.javaScriptClickAnElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked groups tab");
                                        flag = webDB.waitForElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                                        if(flag){
                                            flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                                        }
                                    }
                                }
                            } else {
                                log.logging("fail", "Issue on verifying groups count");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies existing group count
     *
     * @author Gokul - GWL
     * @return boolan flag
     */
    public boolean verifyExistingGroupCount() {
        flag = webDB.waitForElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
        if(flag) {
            String beforeCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
            log.logging("info", "Initial count on group is " + beforeCount);
            flag = webDB.waitForElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_TAB, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_LIST_TAB, ElementType.Xpath);
                        if(flag){
                            flag = webDB.waitForElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                            if(flag){
                                String grpName = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                                log.logging("info", "Extracted group name is; "+grpName);
                                flag = webDB.clickAnElement(GroupsPageLocators.GROUPS_ADD_TAB, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked add tab on groups");
                                    flag = webDB.waitForElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, ElementType.Xpath);
                                    if(flag) {
                                        flag = webDB.sendTextToAnElement(GroupsPageLocators.GROUP_NAME_TEXT_FIELD, grpName, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Group name has beeen entered in text field");
                                            flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                                            if (flag) {
                                                log.logging("info", "Clicked submit button");
                                                flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                if (flag) {
                                                    flag = webDB.javaScriptClickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                    if (flag) {
                                                        log.logging("info", "Clicked dashboard tab");
                                                        String afterCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
                                                        log.logging("info", "after count on group is "+afterCount);
                                                        if (beforeCount.equals(afterCount)) {
                                                            log.logging("info", "Count has not increased as expected");
                                                        } else {
                                                            log.logging("fail", "Issue on verifying group count");
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
            }
        }
        return flag;
    }

    /**
     * This method verifies deleted group count
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletedGroupCount() throws InterruptedException {
        flag = webDB.waitForElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
        if(flag) {
            String beforeCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
            log.logging("info", "Initial count on group is " + beforeCount);
            flag = groupsPage.verifyAddToCreateNewGrp();
            if(flag){
                flag = mindMappingPage.deleteFirstRowInList();
                if(flag){
                    flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.javaScriptClickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.waitForElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
                            if (flag) {
                                String afterCount = webDB.getTextFromElement(DashboardPageLocators.GROUPS_COUNT, ElementType.Xpath);
                                log.logging("info", "After count on group is " + afterCount);
                                if (beforeCount.equals(afterCount)) {
                                    log.logging("info", "Groups count has not increased as expected");
                                }else{
                                    log.logging("fail", "Issue on verifying group count");
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
     * This method verifies participants count for different name
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantCountForDifferentName() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_EMAIL, ElementType.Xpath);
        if(flag) {
            String email = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_FIRST_ROW_EMAIL, ElementType.Xpath);
            flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
            if (flag) {
                String initialCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                log.logging("info", "Initial count of participants is: " + initialCount);
                int initialNumber = Integer.parseInt(initialCount);
                flag = frontEndLoginPage.verifyCopyClipboardLink();
                if (flag) {
                    flag = webDB.openNewTabWithUrl(frontEndLoginPage.url);
                    if (flag) {
                        log.logging("info", "Navigated to front end page");
                        flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, EmailGenerator.getRandomNme(), ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Valid name entered on name field");
                                flag = webDB.waitForElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                                if (flag) {
                                    flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, email, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Valid email entered on email field");
                                        flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                            if (flag) {
                                                log.logging("info", "Clicked next button");
                                                Thread.sleep(3000);
                                                flag = webDB.switchToChildWindow();
                                                if (flag) {
                                                    flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                    if (flag) {
                                                        flag = webDB.clickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                                                        if (flag) {
                                                            flag = webDB.waitForElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                            if (flag) {
                                                                String aftermathCount = webDB.getTextFromElement(DashboardPageLocators.PARTICIPANTS_COUNT, ElementType.Xpath);
                                                                log.logging("info", "Aftermath count of participants is: " + aftermathCount);
                                                                int afterCount = Integer.parseInt(aftermathCount);
                                                                if (afterCount == initialNumber) {
                                                                    log.logging("info", "Participants count has not been increased as expected");
                                                                } else {
                                                                    log.logging("fail", "Issue on verifying participants count");
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
                    }
                }
            }
        }
        return flag;
    }
}
