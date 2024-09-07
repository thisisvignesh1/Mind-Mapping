package PageFunctions;

import Locators.FeedbackPageLocators;
import Locators.FrontEndFeedBackPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeedbackPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static FrontEndFeedbackPage frontEndFeedbackPage = new FrontEndFeedbackPage();
    boolean flag;

    /**
     * This method verifies feedback popup view
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackPopupView() {
        flag = navigateToFeedbackTab();
        if(flag){
            flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_VIEW, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked view link");
                flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_POPUP_TITLE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Popup is visible for feedback view link");
                }else{
                    log.logging("fail", "Issue on showing feedback popup");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method navigates to feedback tab
     *
     * @author Gokul
     * @return Boolean flag
     */
    public boolean navigateToFeedbackTab() {
        flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_TAB, ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(FeedbackPageLocators.FEEDBACK_TAB, ElementType.Xpath);
            //flag = webDB.clickAnElement(FeedbackPageLocators.FEEDBACK_TAB, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked feedback tab");
                flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_VIEW, ElementType.Xpath);
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback close popup
     *
     * @author Gokul - GWL
     * @return Boolean flag
     */
    public boolean closeFeedbackPopup() {
        flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_POPUP_CLOSE, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(FeedbackPageLocators.FEEDBACK_POPUP_CLOSE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked feedback close button");
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback popup components
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackPopupComponents() {
        flag = verifyFeedbackPopupView();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_POPUP_OK, ElementType.Xpath);
            if(flag){
                log.logging("info", "Feedback popup ok is visible");
            }else{
                log.logging("fail", "Issue on feedback popup");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback popup ok button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackPopupOkBtn() throws InterruptedException {
        flag = verifyFeedbackPopupComponents();
        if(flag){
            flag = webDB.clickAnElement(FeedbackPageLocators.FEEDBACK_POPUP_OK, ElementType.Xpath);
            if(flag) {
                log.logging("info", "Clicked ok button from feedback popup");
                Thread.sleep(3000);
                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
                WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FeedbackPageLocators.FEEDBACK_POPUP_TITLE)));
                if(element.isDisplayed()){
                    log.logging("fail", "Popup is still available which is not a case");
                    flag = false;
                }else{
                    log.logging("info", "Popup closed as expected");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies close button on popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCloseBtnOnPopup() throws InterruptedException {
        flag = verifyFeedbackPopupComponents();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_POPUP_CLOSE, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(FeedbackPageLocators.FEEDBACK_POPUP_CLOSE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked feedback close button");
                    Thread.sleep(3000);
                    WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
                    WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FeedbackPageLocators.FEEDBACK_POPUP_TITLE)));
                    if(element.isDisplayed()){
                        log.logging("fail", "Popup is still available which is not a case");
                        flag = false;
                    }else{
                        log.logging("info", "Popup closed as expected");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies actual feedback message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyActualFeedbackMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        if(flag){
            flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_VIEW, ElementType.Xpath);
            if(flag) {
                log.logging("info", "Clicked view link");
                flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_POPUP_TITLE, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Popup is visible for feedback view link");
                    flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_IN_POPUP, ElementType.Xpath);
                    if(flag){
                        String feedback = webDB.getTextFromElement(FeedbackPageLocators.FEEDBACK_IN_POPUP, ElementType.Xpath);
                        if(feedback.equals(FrontEndFeedBackPageLocators.RANDOM_FEEDBACK)){
                            log.logging("info", "Feedback message is verified");
                            flag = closeFeedbackPopup();
                        }else{
                            log.logging("fail", "Issue while verifying feedback message");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback created date
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackCreatedDate() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_CREATED_DATE, ElementType.Xpath);
            if(flag){
                LocalDate today = LocalDate.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String formattedDate = today.format(formatter);
                log.logging("info", "Today's date is "+formattedDate);
                String createdDate = webDB.getTextFromElement(FeedbackPageLocators.TABLE_FIRST_ROW_CREATED_DATE, ElementType.Xpath);
                if(createdDate.equals(formattedDate)){
                    log.logging("info", "Feedback created date is verified");
                }else{
                    log.logging("false", "Issue on verifying created date");
                    flag = false;
                }

            }
        }
        return flag;
    }

    /**
     * This method deletes feedback details
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean deleteFeedbackContent() throws InterruptedException {
        flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Delete icon is clicked");
                flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_POPUP, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Delete popup is shown");
                    flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(FeedbackPageLocators.DELETE_POPUP_CONFIRM, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked delete confirm");
                            Thread.sleep(2000);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopup() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Delete icon is clicked");
                    flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delete popup is shown");
                        flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            Thread.sleep(2000);
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
     * This method verifies delete popup message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Delete icon is clicked");
                    flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_MESSAGE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Delete popup message is visible");
                        String deleteMessage = webDB.getTextFromElement(FeedbackPageLocators.DELETE_POPUP_MESSAGE, ElementType.Xpath);
                        if(deleteMessage.equals(FeedbackPageLocators.DELETE_POPUP_MESSAGE_TXT)){
                            log.logging("info", "Delete popup message is verified");
                            flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            if(flag){
                                flag = webDB.clickAnElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                                Thread.sleep(2000);
                            }
                        }else{
                            log.logging("fail", "Issue on verifying delete popup message");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete popup close icon
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupClose() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag){
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Delete icon is clicked");
                    flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_CLOSE, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(FeedbackPageLocators.DELETE_POPUP_CLOSE, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked delete popup close");
                            Thread.sleep(2000);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
                            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_POPUP)));
                            if(element.isDisplayed()){
                                log.logging("fail", "Popup is still available which is not a case");
                                flag = false;
                            }else{
                                log.logging("info", "Popup closed as successfully");
                            }
                        }else{
                            log.logging("fail", "Issue on closing delete popup");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel button in delete popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelButtonInDeletePopup() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_ICON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Delete icon is clicked");
                    flag = webDB.waitForElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(FeedbackPageLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked popup cancel");
                            Thread.sleep(2000);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
                            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(FeedbackPageLocators.TABLE_FIRST_ROW_DELETE_POPUP)));
                            if(element.isDisplayed()){
                                log.logging("fail", "Popup is still available which is not a case");
                                flag = false;
                            }else{
                                log.logging("info", "Popup closed as successfully");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies confirm delete popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyConfirmDeletePopup() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        if(flag){
            flag = deleteFeedbackContent();
        }
        return flag;
    }

    /**
     * This method verifies show button number
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyShowButtonNumber() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.SHOW_BUTTON_SELECTBOX, ElementType.Xpath);
            if(flag){
                flag = webDB.selectDropDownByValue(FeedbackPageLocators.SHOW_BUTTON_SELECTBOX, "10", ElementType.Xpath);
                if(flag){
                    log.logging("info", "Dropdown is selected to value 10");
                    List<WebElement> elements = webDB.getDriver().findElements(By.xpath(FeedbackPageLocators.TABLE_TOTAL_ROWS));
                    if(elements.size() == 10){
                        log.logging("info", "Feedback items count is equal to the show button value");
                    }else{
                        log.logging("fail", "Issue on verifying count of show button");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies search box functionality
     *
     * @return boolean flag
     * @throws InterruptedException
     */
    public boolean verifySearchBoxFunctionality() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_NAME,ElementType.Xpath);
            if(flag){
                String name = webDB.getTextFromElement(FeedbackPageLocators.TABLE_FIRST_ROW_NAME,ElementType.Xpath);
                flag = webDB.waitForElement(FeedbackPageLocators.SEARCH_BOX, ElementType.Xpath);
                if(flag){
                    flag = webDB.sendTextToAnElement(FeedbackPageLocators.SEARCH_BOX, name, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "First row User name is entered on search box");
                        Thread.sleep(3000);
                        List<WebElement> elements = webDB.getDriver().findElements(By.xpath(FeedbackPageLocators.TABLE_FIRST_ROW_NAME));
                        boolean isNameFound = false;
                        for (WebElement element : elements) {
                            String text = element.getText();
                            System.out.println("Element text: " + text);
                            if (!name.equals(text)) {
                                isNameFound = false;
                            }
                        }
                        if (isNameFound = true) {
                            log.logging("info", "Table shows only searched contents");
                        } else {
                            log.logging("fail", "Issue one verifying search box");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies clear text in search box
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyClearTextInSearchBox() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.SEARCH_BOX, ElementType.Xpath);
            if(flag) {
                flag = webDB.sendTextToAnElement(FeedbackPageLocators.SEARCH_BOX, EmailGenerator.getRandomNme(), ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Name has been enetered into search box");
                    Thread.sleep(2000);
                    flag = webDB.clearTextField(FeedbackPageLocators.SEARCH_BOX, ElementType.Xpath);
                    if(flag){
                        Thread.sleep(2000);
                        String name  = webDB.getTextFromElement(FeedbackPageLocators.SEARCH_BOX, ElementType.Xpath);
                        System.out.println("Name is: "+name);
                        if(name.isEmpty()){
                            log.logging("info", "Cleared text as expected");
                        }else{
                            log.logging("info", "Issue while clearing text");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback to dashboard page navigation
     *
     * @author Gokul -GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackToDashboardPageNav() {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.DASHBOARD_LINK, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(FeedbackPageLocators.DASHBOARD_LINK, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked dashboard link");
                    flag = webDB.waitForElement(FeedbackPageLocators.DASHBOARD_PAGE_HEADING, ElementType.Xpath);
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
     * This method verifies Number of table of entries
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNumberOfTableEntries() {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.SHOW_BUTTON_SELECTBOX, ElementType.Xpath);
            if (flag) {
                flag = webDB.selectDropDownByValue(FeedbackPageLocators.SHOW_BUTTON_SELECTBOX, "10", ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Dropdown is selected to value 10");
                    flag = webDB.waitForElement(FeedbackPageLocators.NUMBER_OF_ITEMS, ElementType.Xpath);
                    if (flag) {
                        String count = webDB.getTextFromElement(FeedbackPageLocators.NUMBER_OF_ITEMS, ElementType.Xpath);
                        Pattern pattern = Pattern.compile("\\d+ to (\\d+) of \\d+ entries");
                        Matcher matcher = pattern.matcher(count);
                        if (matcher.find()) {
                            int number = Integer.parseInt(matcher.group(1));
                            if(number == 10){
                                log.logging("info", "Drop down value mated with the actual data count");
                            }else{
                                log.logging("fail", "Issue on extracting count");
                                flag = false;
                            }
                        } else {
                            System.out.println("Could not extract the number");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies pagination next button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPaginationNextButton() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            String firstCount = webDB.getTextFromElement(FeedbackPageLocators.NUMBER_OF_ITEMS, ElementType.Xpath);
            log.logging("info", "Extracted first page number");
            flag = webDB.waitForElement(FeedbackPageLocators.NEXT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(FeedbackPageLocators.NEXT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked next button");
                    Thread.sleep(2000);
                    String secondCount = webDB.getTextFromElement(FeedbackPageLocators.NUMBER_OF_ITEMS, ElementType.Xpath);
                    if(firstCount.equals(secondCount)){
                        log.logging("info", "Issue on loading next page");
                        flag = false;
                    }else{
                        log.logging("info", "Navigated to next page successfully");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies previous button in pagination
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyPreviousButtonInPagination() throws InterruptedException {
        flag = navigateToFeedbackTab();
        if(flag) {
            flag = webDB.waitForElement(FeedbackPageLocators.NEXT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(FeedbackPageLocators.NEXT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked Next button");
                    Thread.sleep(2000);
                    flag = webDB.waitForElement(FeedbackPageLocators.PREVIOUS_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(FeedbackPageLocators.PREVIOUS_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked previous button which is enabled");
                        }
                    }
                }
            }
        }
        return flag;
    }
}
