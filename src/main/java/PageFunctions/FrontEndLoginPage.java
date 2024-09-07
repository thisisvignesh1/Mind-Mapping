package PageFunctions;

import Locators.*;
import org.openqa.selenium.JavascriptExecutor;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class FrontEndLoginPage {
    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static GroupsPage groupsPage = new GroupsPage();
    static MindMappingPage mindMappingPage = new MindMappingPage();
    static CommonPage commonPage = new CommonPage();
    String groupName = MindMappingLocators.randomAlphabets;
    boolean flag;
    static String name;
    static String email;
    String url;

    /**
     * This method verifies galaxy logo in front end page
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyGalaxyLogoInFrontEnd() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = groupsPage.navigateToMindMappingFrontEnd();
        return flag;
    }

    /**
     * This method verifies copy clipboard link
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCopyClipboardLink() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = groupsPage.verifyNavigatesToGroupsListPage();
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
     * This method verifies front end login components
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyFrontEndLoginComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Navigated to front end login page as expected");
                    if (flag) {
                        flag = webDB.waitForElement(FrontEndLoginLocators.FEEDBACK_BTN, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Feed back button is visible");
                            flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Name text field is visible");
                                flag = webDB.waitForElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Email text field is visible");
                                    flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        log.logging("info", "Submit button is visible");
                                        flag = webDB.waitForElement(FrontEndLoginLocators.CANCEL_BUTTON, ElementType.Xpath);
                                        if (flag) {
                                            log.logging("info", "Cancel button is visible");
                                        } else {
                                            log.logging("fail", "Cancel button is not visible");
                                        }
                                    } else {
                                        log.logging("fail", "Submit button is not visible");
                                    }
                                } else {
                                    log.logging("fail", "Email text field is not visible");
                                }
                            } else {
                                log.logging("fail", "Name text field is not visible");
                            }
                        } else {
                            log.logging("fail", "Feedback button is not visible");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies error message for name field
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyErrorMessageForNameFld() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "1234", ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Numberical values are sent to the name field");
                            flag = webDB.moveToElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Email field is visible");
                                flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD,"asdf", ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked email text field");
                                    flag = webDB.waitForElement(FrontEndLoginLocators.ERROR_MSG_FOR_NAME_FIELD, ElementType.Id);
                                    if (flag) {
                                        log.logging("info", "Error message for numerical value is visible");
                                        String text = webDB.getTextFromElement(FrontEndLoginLocators.ERROR_MSG_FOR_NAME_FIELD, ElementType.Id);
                                        if (text.equals(FrontEndLoginLocators.ALPHABETS_ONLY_ERROR_MESSAGE)) {
                                            log.logging("info", "Error message is verified");
                                        } else {
                                            log.logging("fail", "Issue on verifying error message");
                                            webDB.switchToChildWindow();
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
     * This method verifies error message for email field
     *
     * @return bolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyErrorMessageForEmailFld() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.CANCEL_BUTTON, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, "1234", ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Send numberical values to email text field");
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "asdf", ElementType.Xpath);
                            if (flag) {
                                flag = webDB.waitForElement(FrontEndLoginLocators.ERROR_MSG_FOR_EMAIL_FIELD, ElementType.Id);
                                if (flag) {
                                    log.logging("info", "Error message for numerical value is visible");
                                    String text = webDB.getTextFromElement(FrontEndLoginLocators.ERROR_MSG_FOR_EMAIL_FIELD, ElementType.Id);
                                    if (text.equals(FrontEndLoginLocators.ERROR_MESSAGE_FOR_EMAIL)) {
                                        log.logging("info", "Error message is verified");
                                    } else {
                                        log.logging("fail", "Issue on verifying error message");
                                        webDB.switchToChildWindow();
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
     * This method verifies empty email and name field
     * @return boolean flag
     */
    public boolean verifyEmptyEmailNameField() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked next button without entering name and email");
                                flag = webDB.waitForElement(FrontEndLoginLocators.EMPTY_NAME_ERROR_MSG, ElementType.Xpath);
                                if(flag){
                                    String errorMsg = webDB.getTextFromElement(FrontEndLoginLocators.EMPTY_NAME_ERROR_MSG, ElementType.Xpath);
                                    log.logging("info", "Error message for empty name is: "+errorMsg);
                                    if(errorMsg.equals(FrontEndLoginLocators.ERROR_MESSAGE_FOR_EMPTY_NAME_FIELD)){
                                        log.logging("info", "Error message verified for empty name field");
                                        flag = webDB.waitForElement(FrontEndLoginLocators.EMPTY_EMAIL_ERROR_MSG, ElementType.Xpath);
                                        if(flag) {
                                            String errorMsg1 = webDB.getTextFromElement(FrontEndLoginLocators.EMPTY_EMAIL_ERROR_MSG, ElementType.Xpath);
                                            log.logging("info", "Error message for empty email is: "+errorMsg1);
                                            if (errorMsg1.equals(FrontEndLoginLocators.ERROR_MESSAGE_FOR_EMPTY_EMAIL_FIELD)) {
                                                log.logging("info", "Error message verified for empty email field");
                                            } else {
                                                log.logging("fail", "Issue on verifying empty email field");
                                                webDB.switchToChildWindow();
                                                flag = false;
                                            }
                                        }
                                    }else{
                                        log.logging("fail", "Issue on verifying empty name field");
                                        webDB.switchToChildWindow();
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
     * This method verifies cancel button in login page
     *
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyCancelButtonInLogin() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "asdf", ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Name was entered in name text field");
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, "asdf@gmail.com", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Email was entered in email text field");
                                flag = webDB.waitForElement(FrontEndLoginLocators.CANCEL_BUTTON, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.clickAnElement(FrontEndLoginLocators.CANCEL_BUTTON, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked cancel button");
                                        Thread.sleep(3000);
                                        flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                                        if(flag){
                                            log.logging("info", "User not navigated into next page");
                                        }else{
                                            log.logging("fail", "Issue on login page");
                                            webDB.switchToChildWindow();
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
        return flag;
    }

    /**
     * This method verifies valid name with invalid email
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyValidNameInvalidEmail() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "asdf", ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Valid name was entered in name text field");
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, "asdf.com", ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Invalid email sent to the email text field");
                                flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.clickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked next button");
                                        flag = webDB.waitForElement(FrontEndLoginLocators.ERROR_MSG_FOR_EMAIL_FIELD, ElementType.Id);
                                        if(flag){
                                            log.logging("info", "Error message shown for invalid email and not navigated to next page");
                                        }else{
                                            log.logging("fail", "Issue while clicking next button");
                                            webDB.switchToChildWindow();
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
        return flag;
    }

    /**
     * This method verifies invalid name and valid email login
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidNamevalidEmail() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "123", ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Valid name was entered in name text field");
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, "asdf@gmail.com", ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Invalid email sent to the email text field");
                                flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.clickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked next button");
                                        flag = webDB.waitForElement(FrontEndLoginLocators.ERROR_MSG_FOR_NAME_FIELD, ElementType.Id);
                                        if(flag){
                                            log.logging("info", "Error message shown for invalid name and not navigated to next page");
                                        }else{
                                            log.logging("fail", "Issue while clicking next button");
                                            webDB.switchToChildWindow();
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
        return flag;
    }

    /**
     * This method verifies name field letters count error
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNameFieldLettersCount() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, "ab", ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Sent 2 characters in name text field");
                            flag = webDB.clickAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
                            if(flag){
                                flag = webDB.waitForElement(FrontEndLoginLocators.ERROR_MSG_FOR_NAME_FIELD, ElementType.Id);
                                if(flag){
                                    log.logging("info", "Error message for less than 3 character name is visible");
                                }else{
                                    log.logging("fail","Issue on verifying error message");
                                    webDB.switchToChildWindow();
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
     * This method verifies feed back page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedBackPageNavigation() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(5000);
                flag = webDB.waitForElement(FrontEndLoginLocators.FEEDBACK_BTN, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Feedback button is visible");
                    Thread.sleep(1000);
                    flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.FEEDBACK_BTN, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked feedback button");
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Successfully navigated to feedback page");
                        }else{
                            log.logging("fail", "Issue while navigating to feedback page");
                            webDB.switchToChildWindow();
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies words entering page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyWordsEnteringPageNavigation() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyCopyClipboardLink();
        if(flag) {
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(3000);
                flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
                if (flag) {
                    flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if (flag) {
                        name = EmailGenerator.getRandomNme();
                        flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD,name, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Valid name was entered in name text field");
                            email = EmailGenerator.generateRandomEmail();
                            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, email, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Valid email sent to the email text field");
                                flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                if(flag){
                                    flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                                    if(flag){
                                        log.logging("info", "Clicked next button");
                                        flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER, ElementType.Id);
                                        if(flag){
                                            log.logging("info", "Navigated to connection words page");
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
     * This method navigates from login to words entering page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyLoginToWordsEnteringPageNav(){
        flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
        if (flag) {
            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD, EmailGenerator.getRandomNme(), ElementType.Xpath);
            if (flag) {
                log.logging("info", "Valid name was entered in name text field");
                flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, EmailGenerator.generateRandomEmail(), ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Valid email sent to the email text field");
                    flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked next button");
                            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER, ElementType.Id);
                            if(flag){
                                log.logging("info", "Navigated to connection words page");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters valid name email specific to reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean enterValidNameEmailSpecificToReportsPage() {
        flag = webDB.moveToElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
        if (flag) {
            name = EmailGenerator.getRandomNme();
            flag = webDB.sendTextToAnElement(FrontEndLoginLocators.NAME_TEXT_FIELD,name, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Valid name was entered in name text field");
                email = EmailGenerator.generateRandomEmail();
                flag = webDB.sendTextToAnElement(FrontEndLoginLocators.EMAIL_TEXT_FIELD, email, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Valid email sent to the email text field");
                    flag = webDB.waitForElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(FrontEndLoginLocators.NEXT_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked next button");
                            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER, ElementType.Id);
                            if(flag){
                                log.logging("info", "Navigated to connection words page");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }
}
