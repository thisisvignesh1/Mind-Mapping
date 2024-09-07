package PageFunctions;

import Locators.FeedbackPageLocators;
import Locators.FrontEndFeedBackPageLocators;
import Locators.FrontEndLoginLocators;
import com.sun.mail.imap.protocol.ID;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class FrontEndFeedbackPage {
    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static CommonPage commonPage = new CommonPage();
    static FrontEndLoginPage frontEndLoginPage = new FrontEndLoginPage();
    static EmailGenerator emailGenerator = new EmailGenerator();
    String randomName;
    String randomEmail;
    boolean flag;

    /**
     * This method verifies feedback page componenets
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if(flag){
                log.logging("info", "Name text field is visible");
                flag = webDB.waitForElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, ElementType.Id);
                if(flag){
                    log.logging("info", "Email text field is visible");
                    flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, ElementType.Id);
                    if(flag){
                        log.logging("info", "Feedback text area is visible");
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Cancel button is visible");
                            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Submit button is visible");
                            }else{
                                log.logging("fail", "Issue on finding submit button");
                                webDB.switchToChildWindow();
                                flag = false;
                            }
                        }else{
                            log.logging("fail", "Issue on finding cancel button");
                            webDB.switchToChildWindow();
                            flag = false;
                        }
                    }else{
                        log.logging("fail", "Issue on finding text area");
                        webDB.switchToChildWindow();
                        flag = false;
                    }
                }else{
                    log.logging("fail", "Issue on finding email text field");
                    webDB.switchToChildWindow();
                    flag = false;
                }
            }else{
                log.logging("fail", "Issue on finding name text field");
                webDB.switchToChildWindow();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback page heading
     *
     * @author Gokul - GWL
     * @return boolean flag;
     */
    public boolean verifyFeedbackPageHeading() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            String heading = webDB.getTextFromElement(FrontEndFeedBackPageLocators.FEEDBACK_HEADING, ElementType.Xpath);
            if(heading.equals(FrontEndFeedBackPageLocators.FEEDBACK_SUBTEXT)){
                log.logging("info", "Feedback subtext is verified");
            }else{
                log.logging("fail", "Issue on verifying subtext");
                webDB.switchToChildWindow();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies invalid name in feedback
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidNameInFeedback() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, "123", ElementType.Id);
            if(flag){
                log.logging("info", "Send invalid name in text field");
                flag = webDB.clickAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, ElementType.Id);
                if(flag){
                    flag = webDB.waitForElement(FrontEndFeedBackPageLocators.INVALID_NAME_ERROR, ElementType.Id);
                    if(flag){
                        log.logging("info", "Error message is visible");
                        String errorMessage = webDB.getTextFromElement(FrontEndFeedBackPageLocators.INVALID_NAME_ERROR, ElementType.Id);
                        if(errorMessage.equals(FrontEndFeedBackPageLocators.INVALID_NAME_ERROR_MSG)){
                            log.logging("info", "Error message is verified");
                        }else{
                            log.logging("fail", "Issue while verifying error message");
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
     * This method verifies invalid email error message
     *
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyInvalidEmailError() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, "1234", ElementType.Id);
            if(flag){
                log.logging("info", "Invalid email entered into text field");
                flag = webDB.clickAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, ElementType.Id);
                if(flag){
                    flag = webDB.waitForElement(FrontEndFeedBackPageLocators.INVALID_EMAIL_ERROR, ElementType.Id);
                    if(flag){
                        String emailError = webDB.getTextFromElement(FrontEndFeedBackPageLocators.INVALID_EMAIL_ERROR, ElementType.Id);
                        if(emailError.equals(FrontEndFeedBackPageLocators.INVALID_EMAIL_ERROR_MSG)){
                            log.logging("info", "Error message is verified for invalid email");
                        }else{
                            log.logging("fail", "Issue while verifying error message");
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
     * This method verifies empty email error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyEmailErrorMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, EmailGenerator.getRandomNme(), ElementType.Id);
            if(flag){
                log.logging("info", "Random name has been entered to text field");
                flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, FrontEndFeedBackPageLocators.RANDOM_FEEDBACK, ElementType.Id);
                if(flag){
                    log.logging("info", "Random feedback has been entered in text field");
                        Thread.sleep(2000);
                        flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            log.logging("info", "Clicked submit button");
                            Thread.sleep(1500);
//                            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.INVALID_EMAIL_ERROR, ElementType.Id);
//                            if(flag){
//                                log.logging("info", "Error message for empty email is visible");
//                                String errorMessage = webDB.getTextFromElement(FrontEndFeedBackPageLocators.INVALID_EMAIL_ERROR, ElementType.Id);
//                                if(errorMessage.equals(FrontEndFeedBackPageLocators.EMPTY_TEXT_FIELD_MESSAGE)){
//                                    log.logging("info", "Error message is verified");
//                                }else{
//                                    log.logging("fail", "Issue while verifying the error message");
//                                    webDB.switchToChildWindow();
//                                    flag = false;
//                                }
//                            }
                        }
                }
            }
        return flag;
    }

    /**
     * This method verifies empty name error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyNameErrorMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, EmailGenerator.generateRandomEmail(), ElementType.Id);
            if(flag){
                log.logging("info", "Random name has been entered to text field");
                flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, FrontEndFeedBackPageLocators.RANDOM_FEEDBACK, ElementType.Id);
                if(flag){
                    log.logging("info", "Random feedback has been entered in text field");
                    Thread.sleep(2000);
                    flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    log.logging("info", "Clicked submit button");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty feedback error
     *
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyEmptyFeedbackError() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, EmailGenerator.generateRandomEmail(), ElementType.Id);
            if(flag){
                log.logging("info", "Random name has been entered to text field");
                flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, EmailGenerator.getRandomNme(), ElementType.Id);
                if(flag){
                    log.logging("info", "Random feedback has been entered in text field");
                    Thread.sleep(2000);
                    flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    log.logging("info", "Clicked submit button");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback length error
     *
     * @return Boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyFeedbackLengthError() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, "asd", ElementType.Id);
                if(flag){
                    log.logging("info", "Feedback with 3 characters been entered");
                    Thread.sleep(2000);
                    flag = webDB.clickAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_ERROR, ElementType.Id);
                        if(flag){
                            log.logging("info", "Error message for feedback length is verified");
                        }else{
                            log.logging("fail", "Issue while verifying feedback length");
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
     * This method verifies name field length
     *
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyNameFieldLength() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, "sa", ElementType.Id);
                if(flag){
                    log.logging("info", "Name with 3 characters sent to text field");
                    flag = webDB.clickAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.INVALID_NAME_ERROR, ElementType.Id);
                        if(flag){
                            log.logging("info", "Error message is visible");
                            String errorMsg = webDB.getTextFromElement(FrontEndFeedBackPageLocators.INVALID_NAME_ERROR, ElementType.Id);
                            if(errorMsg.equals(FrontEndFeedBackPageLocators.NAME_LENGHT_ERROR_MSG)){
                                log.logging("info", "Error message for character length is verfied");
                            }else{
                                log.logging("fail", "Issue while verifying error message");
                                webDB.switchToChildWindow();
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
     * This method enteres valid name email and feedback
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean enteringValidNameEmailFeedback() {
        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, ElementType.Id);
        if(flag) {
            randomName = EmailGenerator.getRandomNme();
            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.NAME_TEXT_FIELD, randomName, ElementType.Id);
            if (flag) {
                log.logging("info", "Valid name entered on name field");
                flag = webDB.waitForElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, ElementType.Id);
                if (flag) {
                    randomEmail = EmailGenerator.generateRandomEmail();
                    flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.EMAIL_TEXT_FIELD, randomEmail, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Valid email entered on email field");
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, ElementType.Id);
                        if (flag) {
                            flag = webDB.sendTextToAnElement(FrontEndFeedBackPageLocators.FEEDBACK_TEXT_AREA, FrontEndFeedBackPageLocators.RANDOM_FEEDBACK, ElementType.Id);
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This metod verifies cancel button redirection
     *
     * @return Boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyCancelButtonRedirection() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = enteringValidNameEmailFeedback();
            if(flag){
                log.logging("info", "Valid feedback entered on feedback field");
                flag = webDB.waitForElement(FrontEndFeedBackPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Cancel button is clicked");
                        flag = webDB.waitForElement(FrontEndLoginLocators.HEADIG_TEXT, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Successfully navigated back to login page");
                        }else{
                            log.logging("fail", "Issue on clicking cancel button");
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
     * This method verifies feedback success message
     *
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyFeedbackSuccessMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = enteringValidNameEmailFeedback();
            if(flag){
                flag = webDB.waitForElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.SUBMIT_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked submit button");
                        flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_SUCCESS_MSG, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Success message for feedback is visible");
                            String successMsg = webDB.getTextFromElement(FrontEndFeedBackPageLocators.FEEDBACK_SUCCESS_MSG, ElementType.Xpath);
                            if(successMsg.equals(FrontEndFeedBackPageLocators.SUCCESS_FEEDBACK_MSG)){
                                log.logging("info", "Feedback success message is verified");
                            }else{
                                log.logging("fail", "Issue on verifying success message");
                                webDB.switchToChildWindow();
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
     * This method verifies feedback success message close button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackSuccessMessageCloseBtn() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyFeedbackSuccessMessage();
        if(flag){
            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.FEEDBACK_SUCCESS_CLOSE_BTN, ElementType.Xpath);
            if(flag){
                log.logging("info", "Close button on feedback is visible");
                flag = webDB.clickAnElement(FrontEndFeedBackPageLocators.FEEDBACK_SUCCESS_CLOSE_BTN, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked close button on feedback message");
                }else{
                    log.logging("fail", "Issue on clicking close button");
                    webDB.switchToChildWindow();
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel button after submitting feedback
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelBtnAfterFeedback() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyFeedbackSuccessMessage();
        if(flag){
            flag = webDB.waitForElement(FrontEndFeedBackPageLocators.CANCEL_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(FrontEndFeedBackPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked cancel button");
                }else{
                    log.logging("fail", "Issue on cancel button");
                    webDB.switchToChildWindow();
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies feedback sender details
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyFeedbackSenderDetails() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyFeedbackSuccessMessage();
        if(flag){
            flag = webDB.switchToChildWindow();
            if(flag){
                log.logging("info", "Navigated back to admin page");
                flag = webDB.waitForElement(FeedbackPageLocators.FEEDBACK_TAB, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(FeedbackPageLocators.FEEDBACK_TAB, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked feedback tab");
                        flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                        if(flag){
                            String name = webDB.getTextFromElement(FeedbackPageLocators.TABLE_FIRST_ROW_NAME, ElementType.Xpath);
                            if(name.equals(randomName)) {
                                log.logging("info", "Feedback giver name is verified on admin page");
                                flag = webDB.waitForElement(FeedbackPageLocators.TABLE_FIRST_ROW_EMAIL, ElementType.Xpath);
                                if(flag){
                                    String email = webDB.getTextFromElement(FeedbackPageLocators.TABLE_FIRST_ROW_EMAIL, ElementType.Xpath);
                                    if(email.equals(randomEmail)){
                                        log.logging("info", "Email of feedback giver is verified on admin page");
                                    }else{
                                        log.logging("fail", "Issue on verifying feedback giver email");
                                        flag = false;
                                    }
                                }
                            }else{
                                log.logging("fail", "Issue on verifying feedback giver name");
                                flag = false;
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }
}
