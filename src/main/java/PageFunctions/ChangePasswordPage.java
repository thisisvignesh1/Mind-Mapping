package PageFunctions;

import Locators.ChangePasswordPageLocators;
import Locators.CommonLocators;
import Locators.DashboardPageLocators;
import Locators.ProfilePageLocators;
import io.netty.handler.ssl.IdentityCipherSuiteFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ChangePasswordPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method verifies change password page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean changePasswordPageNavigation() {
        flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked profile image logo");
                flag = webDB.waitForElement(CommonLocators.CHANGE_PASSWORD_OPTION, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(CommonLocators.CHANGE_PASSWORD_OPTION, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked change password option");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.CHANGE_PASSWORD_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Navigated to change password page as expected");
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
     * This method verifies dashboard page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean dashboardPageNavigation() throws InterruptedException {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.DASHBOARD_LINK, ElementType.LinkText);
            if(flag){
                Thread.sleep(1000);
                //flag = webDB.clickAnElement(ProfilePageLocators.DASHBOARD_LINK, ElementType.LinkText);
                flag = webDB.javaScriptClickAnElement(ProfilePageLocators.DASHBOARD_LINK, ElementType.LinkText);
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
     * This method verifies change password page component
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePasswordComponents() {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                log.logging("info", "Started to check change password page componenets");
                log.logging("info", "Old password text field is visible");
                flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Name);
                if(flag){
                    log.logging("info", "New password text field is visible");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                    if(flag){
                        log.logging("info", "Confirm password text field is visible");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Update button is visible");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "All components of change password page is verified");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies change password success popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean changePasswordSuccessPopup() throws InterruptedException {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = enterValidOldAndNewPasswords();
            if(flag){
                flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    Thread.sleep(2000);
                    //
                    WebElement element = webDB.getDriver().findElement(By.xpath(ChangePasswordPageLocators.UPDATE_BUTTON));
                    JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
                    js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", element);
                    //
                    if(flag){
                        log.logging("info", "Clicked update button");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.SUCCESS_POPUP, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Update success popup is visible");
                            String text = webDB.getTextFromElement(ChangePasswordPageLocators.SUCCESS_POPUP, ElementType.Xpath);
                            if(text.equals(ChangePasswordPageLocators.SUCCESS_MESSAGE)){
                                log.logging("info", "Success message is verified");
                            }else{
                                log.logging("fail", "Issue on verification");
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
     * This method enteres valid old and new password
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean enterValidOldAndNewPasswords() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
        if(flag) {
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Id);
            if (flag) {
                log.logging("info", "Entered valid old password");
                flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, "IamVengeance@16", ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered new password");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                        if (flag) {
                            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, "IamVengeance@16", ElementType.Id);
                            if (flag) {
                                log.logging("info", "Entered confirm password");
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method enters default valid old and new password
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean changeToDefaultPassword() throws InterruptedException {
        flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
        if(flag) {
            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, "IamVengeance@16", ElementType.Id);
            if (flag) {
                log.logging("info", "Entered valid old password");
                flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Name);
                if (flag) {
                    flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Name);
                    if (flag) {
                        log.logging("info", "Entered new password");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                        if (flag) {
                            flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Id);
                            if (flag) {
                                log.logging("info", "Entered confirm password");
                                flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                if(flag) {
                                    flag = webDB.moveToElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                    Thread.sleep(2000);
                                    WebElement element = webDB.getDriver().findElement(By.xpath(ChangePasswordPageLocators.UPDATE_BUTTON));
                                    JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
                                    js.executeScript("arguments[0].dispatchEvent(new MouseEvent('click', {bubbles: true}));", element);
                                    if (flag) {
                                        log.logging("info", "Clicked update button");
                                        Thread.sleep(2000);
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
     * This method verifies change password success close button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePasswordSuccessCloseBtn() throws InterruptedException {
        flag = changePasswordSuccessPopup();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.SUCCESS_POPUP_CLOSE, ElementType.Xpath);
            if(flag){
                Thread.sleep(1000);
                flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.SUCCESS_POPUP_CLOSE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked success popup close icon");
                    Thread.sleep(2000);
                    flag = webDB.isElementNotDisplayed(ChangePasswordPageLocators.SUCCESS_POPUP, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Popup closed successfully");
                    }else{
                        log.logging("fail", "Issue while closing popup");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty field error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyFieldErrorMessage() {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked update button without entering any values");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_ERROR, ElementType.Id);
                    if(flag){
                        log.logging("info", "Error message for old password text field is visible");
                        flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Id);
                        if(flag){
                            log.logging("info", "Error message for new password text field is visible");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
                            if(flag){
                                log.logging("info", "Error message for confirm password text field is visible");
                            }else{
                                log.logging("fail", "Issue on visibility of error message");
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
     * This method verifies cancel button to dashboard page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelButtonDashboardNav() {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.CANCEL_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.CANCEL_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked cancel button");
                    flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to dashboard page as expected");
                    }else{
                        log.logging("fail", "Issue on navigating to dashboard page");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty error message on new update password
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyErrorMessageOnNewUpdatePwd() throws InterruptedException {
        flag = changePasswordPageNavigation();
        if(flag){
            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD,"Galaxy@123", ElementType.Id);
                if(flag){
                    log.logging("info", "Sent old password text");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked update button");
                            //
                            Thread.sleep(5000);
                            webDB.navigateToRefresh();
                            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
                            if(flag) {
                                webDB.clickAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
                                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Id);
                                if (flag) {
                                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        if(flag){
                                            flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        }
                                    }
                                }
                            }
                            //
                            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Id);
                            if(flag){
                                log.logging("info", "Error message is visible for new password text field");
                                flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
                                if(flag){
                                    log.logging("info", "Error message is visible for confirm password text field");
                                }else{
                                    log.logging("fail", "Issue on showing error message");
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
     * This method verifies empty error message on old update password
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyErrorMessageOnOldUpdatePwd() throws InterruptedException {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
            if (flag) {
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, "IamVengeance@16", ElementType.Id);
                if (flag) {
                    log.logging("info", "Sent old password text");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Clicked update button");
                            //
                            Thread.sleep(5000);
                            webDB.navigateToRefresh();
                            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
                            if(flag) {
                                webDB.clickAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
                                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Id);
                                if (flag) {
                                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        if(flag){
                                            flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        }
                                    }
                                }
                            }
                            //
                            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_ERROR, ElementType.Id);
                            if(flag){
                                log.logging("info", "Error message is visible for old password text field");
                                flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
                                if(flag){
                                    log.logging("info", "Error message is visible for confirm password text field");
                                }else{
                                    log.logging("fail", "Issue on showing error message");
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
     * This method verifies empty error message on old new password
     *
     * @author Gokul - Gwl
     * @return boolean flag
     */
    public boolean verifyEmptyErrorMessageOnOldNewPwd() {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD,"IamVengeance@16", ElementType.Id);
                if(flag){
                    log.logging("info", "Text entered on confirm password text field");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Verified error message for confirm password");
                        }else{
                            log.logging("info", "Issue on verifying error message");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies new password length error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNewPasswordLengthErrorMsg() {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD,"abc", ElementType.Id);
                if(flag){
                    log.logging("info", "New password with 3 characters entered");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                        if(flag){
                            flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Id);
                            if(flag){
                                String errorMsg = webDB.getTextFromElement(ChangePasswordPageLocators.NEW_PASSWORD_ERROR, ElementType.Id);
                                log.logging("info", "Error message extracted is: "+errorMsg);
                                if(errorMsg.equals(ChangePasswordPageLocators.NEW_PASSWORD_LENGTH_ERROR)){
                                    log.logging("info", "Error message is verified for new password field");
                                }else{
                                    log.logging("fail", "Issue while verifying error message");
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
     * This method verifies confirm password length error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyConfirmPasswordLengthError() {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD,"abc", ElementType.Id);
                if(flag){
                    log.logging("info", "New password with 3 characters entered");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Id);
                        if(flag){
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
                            if(flag){
                                String errorMsg = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
                                log.logging("info", "Error message extracted is: "+errorMsg);
                                if(errorMsg.equals(ChangePasswordPageLocators.CONFIRM_PASSWORD_LENGTH_ERROR)){
                                    log.logging("info", "Error message is verified for confirm password field");
                                }else{
                                    log.logging("fail", "Issue while verifying error message");
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
     * This method verifies old password wrong error
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyOldPasswordWrongError() {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD,"Galaxy", ElementType.Id);
                if(flag){
                    log.logging("info", "Wrong old password is sent on text field");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Name);
                    if(flag){
                        flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD,"IamVengeance", ElementType.Name);
                        if(flag){
                            log.logging("info", "Entered new password");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                            if(flag){
                                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, "IamVengeance", ElementType.Id);
                                if(flag){
                                    log.logging("info", "Entered confirm password");
                                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                    if(flag) {
                                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        if(flag) {
                                            flag = checkWrongOldPassword();
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
     * This method verifies old password wrong
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean checkWrongOldPassword() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_WRONG_ERROR, ElementType.CssSelector);
        if(flag){
            log.logging("info", "Error message is visible");
            String text = webDB.getTextFromElement(ChangePasswordPageLocators.OLD_PASSWORD_WRONG_ERROR, ElementType.CssSelector);
            if(text.equals(ChangePasswordPageLocators.OLD_PASSWORD_WRONG_MSG)){
                log.logging("info", "Old password wrong error message is verified");
            }else{
                log.logging("fail", "Issue on verifying old password error message");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies new password mismatch error
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNewPasswordMismatchError() {
        flag = changePasswordPageNavigation();
        if(flag) {
            flag = webDB.waitForElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, ElementType.Id);
            if(flag) {
                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.OLD_PASSWORD_TEXT_FIELD, "Galaxy@123", ElementType.Id);
                if (flag) {
                    log.logging("info", "Valid old password is sent on text field");
                    flag = webDB.waitForElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, ElementType.Name);
                    if (flag) {
                        flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.NEW_PASSWORD_TEXT_FIELD, "IamVengeance@16", ElementType.Name);
                        if (flag) {
                            log.logging("info", "Entered new password");
                            flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, ElementType.Id);
                            if (flag) {
                                flag = webDB.sendTextToAnElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_TEXT_FIELD, "IamVengeance", ElementType.Id);
                                if (flag) {
                                    log.logging("info", "Entered wrong confirm password");
                                    flag = webDB.waitForElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                    if (flag) {
                                        flag = webDB.javaScriptClickAnElement(ChangePasswordPageLocators.UPDATE_BUTTON, ElementType.Xpath);
                                        if(flag){
                                            flag = checkWrongConfirmPassword();
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
     * This method verifies confirm password wrong message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean checkWrongConfirmPassword() {
        flag = webDB.waitForElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
        if(flag){
            log.logging("info", "Error message is visible");
            String text = webDB.getTextFromElement(ChangePasswordPageLocators.CONFIRM_PASSWORD_ERROR, ElementType.Id);
            if(text.equals(ChangePasswordPageLocators.NEW_PASSWORD_MISMATCH_ERROR)){
                log.logging("info", "Confirm password error message is verified");
            }else{
                log.logging("fail", "Issue on verifying old password error message");
                flag = false;
            }
        }
        return flag;
    }

}
