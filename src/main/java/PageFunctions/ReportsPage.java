package PageFunctions;

import Locators.*;
import org.openqa.selenium.support.ui.Select;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReportsPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static GroupsPage groupsPage = new GroupsPage();
    static FrontEndConnectionWordsPage frontEndConnectionWordsPage = new FrontEndConnectionWordsPage();
    static FrontEndLoginPage frontEndLoginPage = new FrontEndLoginPage();
    static MindMappingPage mindMappingPage = new MindMappingPage();
    static LoginPage loginPage = new LoginPage();
    boolean flag;
    String url;

    /**
     * This method navigates to reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean navigateToReportsPage() {
        flag = webDB.waitForElement(ReportsPageLocators.REPORTS_TAB, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(ReportsPageLocators.REPORTS_TAB, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked reports tab");
                flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Navigated to reports page");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies group details delete confirmation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyGroupNameConfirmOnReport() throws InterruptedException {
        flag = mindMappingPage.crateWordsSpecificToReport();
        if(flag) {
            flag = groupsPage.verifyNavigationToGroupTab();
            if (flag) {
                flag = groupsPage.createGroupSpecificToReportsPage();
                if (flag) {
                    flag = groupsPage.assignWordsSpecificForReportsPage();
                    if (flag) {
                        flag = groupsPage.copyUrlSpecificToReportsPage();
                        if (flag) {
                            //
                            flag = webDB.openNewTabWithUrl(groupsPage.reportSpecificUrl);
                            if (flag) {
                                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Navigated to front end login page as expected");
                                    Thread.sleep(2000);
                                    flag = frontEndLoginPage.enterValidNameEmailSpecificToReportsPage();
                                    if (flag) {
                                        flag = frontEndConnectionWordsPage.passingWordsSpecificToReportsPage();
                                        if (flag) {
                                            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                                            if (flag) {
                                                flag = webDB.javaScriptClickAnElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                                                if (flag) {
                                                    Thread.sleep(2000);
                                                    log.logging("info", "Clicked submit button");
                                                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                                                    if (flag) {
                                                        flag = verifyCreatedGroupInReport();
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
     * This method verifies created group in report and delete
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCreatedGroupInReport() throws InterruptedException {
        flag = webDB.switchToChildWindow();
        if(flag){
            flag = navigateToReportsPage();
            if(flag){
                flag = webDB.waitForElement(ReportsPageLocators.DEMO_TESTING_GROUP, ElementType.Xpath);
                if(flag){
                    String text = webDB.getTextFromElement(ReportsPageLocators.DEMO_TESTING_GROUP, ElementType.Xpath);
                    log.logging("info", "Extracted group name is: "+text);
                    if(text.equals("Demo Testing Group")){
                        log.logging("info", "Created group successfully updated");
                    }else{
                        log.logging("info", "Issue on verifying");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies created group delete
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyCreatedGroupDelete() throws InterruptedException {
        flag = webDB.waitForElement(ReportsPageLocators.DEMO_TESTING_GRP_DELETE, ElementType.Xpath);
        if(flag){
            Thread.sleep(1000);
            flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_TESTING_GRP_DELETE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked first row delete icon");
                flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Delete popup is visible");
                    flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked delete confirm");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete created group
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean deleteCreatedGroup() throws InterruptedException {
        flag = groupsPage.verifyNavigatesToGroupsListPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.DEMO_GROUP_DELETE, ElementType.Xpath);
            if(flag){
                Thread.sleep(2000);
                flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_GROUP_DELETE,ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if(flag){
                        flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete created start and end word
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean deleteCreatedStartEndWord() throws InterruptedException {
        flag = mindMappingPage.verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.DEMO_START_END_WORD, ElementType.Xpath);
            if(flag){
                Thread.sleep(2000);
                flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_START_END_WORD, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if(flag){
                        flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies group name delete cancel on report
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyGroupNameDeleteCancelOnReport() throws InterruptedException {
        flag = navigateToReportsPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
            if(flag) {
                String name = webDB.getTextFromElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
                log.logging("info", "Extracted first row name is: "+name);
                flag = webDB.waitForElement(ReportsPageLocators.FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.javaScriptClickAnElement(ReportsPageLocators.FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked first row delete icon");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Delete popup is visible");
                            flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked delete cancel button");
                                    Thread.sleep(2000);
                                    String afterName = webDB.getTextFromElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
                                    log.logging("info", "Extracted first row name after clicking cancel is: "+afterName);
                                    if(name.equals(afterName)){
                                        log.logging("info", "Group info not deleted as expected");
                                    }else{
                                        log.logging("fail", "Issue on delete cancel button");
                                        flag = false;
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
     * This method verifies group name delete close popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifyGroupNameDeleteClosePopup() throws InterruptedException {
        flag = navigateToReportsPage();
        if(flag) {
            flag = webDB.waitForElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
            if (flag) {
                String name = webDB.getTextFromElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
                log.logging("info", "Extracted first row name is: " + name);
                flag = webDB.waitForElement(ReportsPageLocators.FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    flag = webDB.javaScriptClickAnElement(ReportsPageLocators.FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Clicked first row delete icon");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Delete popup is visible");
                            flag = webDB.waitForElement(ReportsPageLocators.DELETE_POPUP_CLOSE, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(ReportsPageLocators.DELETE_POPUP_CLOSE, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked close popup icon");
                                    Thread.sleep(2000);
                                    String afterName = webDB.getTextFromElement(ReportsPageLocators.FIRST_ROW_GROUP_NAME, ElementType.Xpath);
                                    log.logging("info", "Extracted first row name after clicking cancel is: "+afterName);
                                    if(name.equals(afterName)){
                                        log.logging("info", "Group info not deleted as expected");
                                    }else{
                                        log.logging("fail", "Issue on delete cancel button");
                                        flag = false;
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
     * This method verifies reports to dashboard page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyReportsToDashboardNavigation() {
        flag = navigateToReportsPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.DASHBOARD_LINK, ElementType.LinkText);
            if(flag){
                flag = webDB.clickAnElement(ReportsPageLocators.DASHBOARD_LINK, ElementType.LinkText);
                if(flag){
                    log.logging("info", "Clicked dashboard link");
                    flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Successfully navigated to dashboard page");
                    }else{
                        log.logging("fail", "Issue on navigation");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search box on reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySearchBoxOnReportsPage() throws InterruptedException {
        flag = navigateToReportsPage();
            if(flag) {
                Thread.sleep(2000);
                String name = webDB.getTextFromElement(GroupsPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                log.logging("info", "First row group name is: " + name);
                flag = webDB.waitForElement(MindMappingLocators.SEARCH_BOX, ElementType.Xpath);
                if (flag) {
                    flag = webDB.sendTextToAnElement(MindMappingLocators.SEARCH_BOX, name, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Group name is sent to search box");
                        Thread.sleep(3000);
                        flag = webDB.waitForElement(MindMappingLocators.TABLE_ROWS, ElementType.Xpath);
                        if (flag) {
                            List<WebElement> elements = webDB.getDriver().findElements(By.xpath(MindMappingLocators.TABLE_ROWS));
                            int numberOfElements = elements.size();
                            if (numberOfElements == 1) {
                                log.logging("info", "Shows only one row as expected");
                            } else {
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
     * This method verifies total rows displayed
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyTotalRowsDisplayed() {
        flag = navigateToReportsPage();
        if(flag) {
            flag = webDB.waitForElement(ReportsPageLocators.NUMBER_OF_REPORT, ElementType.Id);
            if(flag){
                String text = webDB.getTextFromElement(ReportsPageLocators.NUMBER_OF_REPORT, ElementType.Id);
                String pattern = "(\\d+)\\s+entries$";
                Pattern compiledPattern = Pattern.compile(pattern);
                Matcher matcher = compiledPattern.matcher(text);
                if (matcher.find()) {
                    String lastNumber = matcher.group(1);
                    log.logging("info", "Extracted number: "+lastNumber);
                    flag = webDB.waitForElement(ReportsPageLocators.TOTAL_ROWS, ElementType.Xpath);
                    if(flag){
                        List<WebElement> totalList = webDB.getDriver().findElements(By.xpath(ReportsPageLocators.TOTAL_ROWS));
                        log.logging("info", "Total number of rows: "+totalList.size());
                        if(Integer.parseInt(lastNumber) == totalList.size()){
                            log.logging("info", "Rows count matches with displayed total");
                        }else{
                            log.logging("fail", "Issue on counting");
                            flag = false;
                        }
                    }
                } else {
                    log.logging("fail", "No match found");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies pagination in report
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPaginationInReport() {
        flag = navigateToReportsPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.PAGINATION_PREVIOUS, ElementType.Id);
            if(flag){
                log.logging("info", "Pagination previous is visible");
                flag = webDB.waitForElement(ReportsPageLocators.CURRENT_PAGE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Current page pagination is visible");
                    flag = webDB.waitForElement(ReportsPageLocators.PAGINATION_NEXT, ElementType.Id);
                    if(flag){
                        log.logging("info", "All pagination contents are visible");
                    }else{
                        log.logging("fail", "Issue on pagination contents");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies group start and end word on report
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyGroupStartEndWordOnReport() {
        flag = webDB.waitForElement(ReportsPageLocators.DEMO_START_END_WORD_IN_REPORT, ElementType.Xpath);
        if(flag){
            String text = webDB.getTextFromElement(ReportsPageLocators.DEMO_START_END_WORD_IN_REPORT, ElementType.Xpath);
            log.logging("info", "Extracted text is: "+text);
            String[] parts = text.split("Demo", 3);
            String part1 = "Demo " + parts[1].trim();
            String part2 = "Demo " + parts[2].trim();
            if(part1.equals("Demo start word")){
                log.logging("info", "Start word is updated as mentioned in front end");
                if(part2.equals("Demo end word")){
                    log.logging("info", "End word is updated as mentioned in front end");
                }else{
                    log.logging("fail", "Issue on end word update");
                    flag = false;
                }
            }else{
                log.logging("fail", "Issue on start word update");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies participants count on report
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantsCountOnReport() throws InterruptedException {
        flag = webDB.openNewTabWithUrl(groupsPage.reportSpecificUrl);
        if (flag) {
            flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Navigated to front end login page as expected");
                Thread.sleep(2000);
                flag = frontEndLoginPage.enterValidNameEmailSpecificToReportsPage();
                if (flag) {
                    flag = frontEndConnectionWordsPage.passingWordsSpecificToReportsPage();
                    if (flag) {
                        flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.javaScriptClickAnElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                            if (flag) {
                                Thread.sleep(2000);
                                log.logging("info", "Clicked submit button");
                                flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                                if (flag) {
                                    flag = verifyCreatedGroupInReport();
                                    if(flag){
                                        flag = webDB.waitForElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
                                        if(flag){
                                            String text = webDB.getTextFromElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
                                            log.logging("info", "Extracted count is: "+text);
                                            if(text.equals("2")){
                                                log.logging("info", "Count has been increased as expected");
                                            }else{
                                                log.logging("fail", "Issue on verifying count");
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
     * This method verifies navigation to participants report page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNavigationToParticipantReportPage() {
        flag = navigateToReportsPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.FIRST_ROW_NUMBER_OF_PARTICIPANTS, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(ReportsPageLocators.FIRST_ROW_NUMBER_OF_PARTICIPANTS, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row number of participants");
                    flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                        log.logging("info", "Extracted text is: "+text);
                        if(text.equals("Participants Reports")){
                            log.logging("info", "Navigation to participants report page is verified");
                        }else{
                            log.logging("fail", "Issue on navigation");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies name and email in participants page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNameAndEmailInParticipantsPage() throws InterruptedException {
        flag = verifyGroupNameConfirmOnReport();
        if (flag) {
            flag = webDB.waitForElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
            if (flag) {
                Thread.sleep(1000);
                flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row number of participants");
                    flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                        log.logging("info", "Extracted text is: "+text);
                        if(text.equals("Participants Reports")){
                            log.logging("info", "Navigation to participants report page is verified");
                            flag = verifyCreatedNameAndEmailInParticipantsPage();
                        }else{
                            log.logging("fail", "Issue on navigation");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies creted name and email in participants page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCreatedNameAndEmailInParticipantsPage() {
        flag = webDB.waitForElement("//td[normalize-space()='"+frontEndLoginPage.name+"']", ElementType.Xpath);
        if(flag){
            log.logging("info", "Name from front end page is updated as expected");
            flag = webDB.waitForElement("//td[normalize-space()='"+frontEndLoginPage.email+"']", ElementType.Xpath);
            if(flag){
                log.logging("info", "Email from front end page is updated as expected");
            }else{
                log.logging("fail", "Issue on verifying email");
                flag = false;
            }
        }else{
            log.logging("fail", "Issue on verifying name");
            flag = false;
        }
        return flag;
    }

    /**
     * This method verifies participant entered word
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantEnteredWord() throws InterruptedException {
        flag = verifyGroupNameConfirmOnReport();
        if (flag) {
            flag = webDB.waitForElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
            if (flag) {
                Thread.sleep(1000);
                flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row number of participants");
                    flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                        log.logging("info", "Extracted text is: "+text);
                        if(text.equals("Participants Reports")){
                            log.logging("info", "Navigation to participants report page is verified");
                            flag = verifyCreatedWordsInParticipantPage();
                        }else{
                            log.logging("fail", "Issue on navigation");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies created words in participant page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCreatedWordsInParticipantPage() {
        flag = webDB.waitForElement(ReportsPageLocators.DEMO_WORD_ON_PARTICIPANTS_PAGE, ElementType.Xpath);
        if(flag){
            String text = webDB.getTextFromElement(ReportsPageLocators.DEMO_WORD_ON_PARTICIPANTS_PAGE, ElementType.Xpath);
            log.logging("info", "Extracted word is: "+text);
            if(text.equals("Demo word")){
                log.logging("info", "Entered word on front end page is verified");
            }else{
                log.logging("fail", "Issue on verifying entered page");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies created date on participants page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCreatedDateOnParticipantsPage() throws InterruptedException {
        flag = verifyGroupNameConfirmOnReport();
        if (flag) {
            flag = webDB.waitForElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
            if (flag) {
                Thread.sleep(1000);
                flag = webDB.javaScriptClickAnElement(ReportsPageLocators.DEMO_NO_OF_PARTICIPANTS, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row number of participants");
                    flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                        log.logging("info", "Extracted text is: "+text);
                        if(text.equals("Participants Reports")){
                            log.logging("info", "Navigation to participants report page is verified");
                            flag = verifyCreatedDate();
                        }else{
                            log.logging("fail", "Issue on navigation");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies created date
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCreatedDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = today.format(formatter);
        log.logging("info", "Today's date is: "+formattedDate);
        flag = webDB.waitForElement("//td[normalize-space()='"+formattedDate+"']", ElementType.Xpath);
        if(flag){
            log.logging("info", "Today's date is correctly displayed");
        }else{
            log.logging("fail", "Issue on verifying today's date");
            flag = false;
        }
        return flag;
    }

    /**
     * This method verifies participants to report page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantsToReportPageNav() {
        flag = verifyNavigationToParticipantReportPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.REPORTS_LINK, ElementType.LinkText);
            if(flag){
                flag = webDB.clickAnElement(ReportsPageLocators.REPORTS_LINK, ElementType.LinkText);
                if(flag){
                    log.logging("info", "Clicked reports link");
                    flag = webDB.waitForElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(ReportsPageLocators.REPORTS_HEADING, ElementType.Xpath);
                        log.logging("info", "Extracted text is: "+text);
                        if(text.equals("Reports")){
                            log.logging("info", "Successfully navigated to reports page");
                        }else{
                            log.logging("fail", "Issue on navigation");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }


    /**
     * This method verifies participants search box
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyParticipantsSearchBox() throws InterruptedException {
        flag = verifyNavigationToParticipantReportPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.FIRSTROW_PARTICIPANTS_NAME, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(ReportsPageLocators.FIRSTROW_PARTICIPANTS_NAME, ElementType.Xpath);
                log.logging("info", "Extracted participant name is: "+text);
                flag = webDB.waitForElement(ReportsPageLocators.SEARCH_BOX, ElementType.Xpath);
                if(flag){
                    flag = webDB.sendTextToAnElement(ReportsPageLocators.SEARCH_BOX, text, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Text has been passed on search field");
                        Thread.sleep(2000);
                        List<WebElement>totalRows = webDB.getDriver().findElements(By.xpath(ReportsPageLocators.TABLE_ROWS));
                        if(totalRows.size() == 1){
                            log.logging("info", "Search functionality is working as expected");
                        }else{
                            log.logging("fail", "Issue on search feature");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies pagination buttons
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPaginationPreviousNextBtn() {
        flag = verifyNavigationToParticipantReportPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.PARTICIPANT_PAGINATION_PREVIOUS, ElementType.Id);
            if(flag){
                String attr = webDB.getAttributeFromElement(ReportsPageLocators.PARTICIPANT_PAGINATION_PREVIOUS, ElementType.Id, "class");
                if(attr.contains("disabled")){
                    log.logging("info", "By default previous button is in disable state");
                    List<WebElement>totalRows = webDB.getDriver().findElements(By.xpath(ReportsPageLocators.TABLE_ROWS));
                    if(totalRows.size() > 1) {
                        flag = webDB.waitForElement(ReportsPageLocators.PARTICIPANT_PAGINATION_NEXT, ElementType.Id);
                        if(flag){
                            String attr1 = webDB.getAttributeFromElement(ReportsPageLocators.PARTICIPANT_PAGINATION_NEXT, ElementType.Id, "class");
                            if(attr1.contains("disabled")){
                                log.logging("fail", "Issue on next button state");
                                flag = false;
                            }else{
                                log.logging("info", "Next button state is verified");
                            }
                        }
                    }
                }else{
                    log.logging("fail", "Issue on previous button state");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies number of listing items shown
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNumberOfListingItemsShown() {
        flag = verifyNavigationToParticipantReportPage();
        if(flag){
            List<WebElement> totalRows = webDB.getDriver().findElements(By.xpath(ReportsPageLocators.TABLE_ROWS));
            log.logging("info", "Total number of rows in tha table is: "+totalRows.size());
            int totalCount = totalRows.size();
            flag = webDB.waitForElement(ReportsPageLocators.PARTICIPANT_FIRST_PAGE_INFO, ElementType.Id);
            if(flag){
                String text = webDB.getTextFromElement(ReportsPageLocators.PARTICIPANT_FIRST_PAGE_INFO, ElementType.Id);
                String regex = "to (\\d+)";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(text);
                if (matcher.find()) {
                    String number = matcher.group(1);
                    log.logging("info", "Extracted number from an info is: "+number);
                    if(Integer.parseInt(number) == totalCount){
                        log.logging("info", "Number of count on listing is verified");
                    }else{
                        log.logging("fail", "Issue on verifying number of count");
                        flag = false;
                    }
                } else {
                    log.logging("fail", "No match is found");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies table drop down values
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyTableDropDownValues() {
        flag = verifyNavigationToParticipantReportPage();
        if(flag){
            flag = webDB.waitForElement(ReportsPageLocators.PARTICIPANT_REPORT_COUNT, ElementType.Name);
            if(flag){
                WebElement selectElement = webDB.getDriver().findElement(By.name(ReportsPageLocators.PARTICIPANT_REPORT_COUNT));
                Select select = new Select(selectElement);
                List<WebElement> options = select.getOptions();
                List<String> optionValues = new ArrayList<>();
                for (WebElement option : options) {
                    optionValues.add(option.getText());
                }
                List<String> expectedValues = Arrays.asList("10", "25", "50", "100");
                if (optionValues.containsAll(expectedValues)) {
                    log.logging("info", "All values are present as expected");
                } else {
                    log.logging("fail", "Issue on select box");
                    flag = false;
                }
            }
        }
        return flag;
    }
}
