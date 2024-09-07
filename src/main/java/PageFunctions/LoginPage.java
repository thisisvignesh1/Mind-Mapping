package PageFunctions;

/**
 * @author Gokul - GWL
 * @company Galaxy Weblinks May 21, 2024
 */

import Locators.DashboardPageLocators;
import Locators.LoginPageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class LoginPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method clears username and password fields
     *
     * @author Gokul - GWL
     * @return Boolean flag
     */
    public boolean clearUsernameAndPassword() {
        flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
        if(flag) {
            flag = webDB.clearTextField(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clearTextField(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Xpath);
                }
            }
        }
        return flag;
    }

    /**
     * This method will Log in with valid credentials
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean loginWithValidCredentials() {
        flag = clearUsernameAndPassword();
        if(flag){
            flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD,LoginPageLocators.USERNAME, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD,LoginPageLocators.PASSWORD, ElementType.Xpath);
                        if(flag){
                            flag = signInButtonClick();
                            if(flag){
                                flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method clicks sign in button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean signInButtonClick() {
        flag = webDB.waitForElement(LoginPageLocators.SIGN_IN_BUTTON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(LoginPageLocators.SIGN_IN_BUTTON, ElementType.Xpath);
        }
        return flag;
    }

    /**
     * This method will log in with in valid credentials
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean loginWithInValidCredentials() {
        flag = clearUsernameAndPassword();
        log.logging("info", "Username and password field is cleared");
        if(flag){
            flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD,LoginPageLocators.USERNAME+"1", ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered invalid email");
                    flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD,LoginPageLocators.PASSWORD+"2", ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Entered invalid password");
                            flag = signInButtonClick();
                            if(flag){
                                flag = webDB.waitForElement(LoginPageLocators.INVALID_CREDENTIALS_ERROR_MSG, ElementType.Xpath);
                                if(flag) {
                                    log.logging("info", "Error message for invalid credentials is verified");
                                }else{
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
     * This method will Log in with new valid credentials
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean loginWithNewValidCredentials() {
        flag = clearUsernameAndPassword();
        if(flag){
            flag = webDB.waitForElement(LoginPageLocators.EMAIL_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                flag = webDB.sendTextToAnElement(LoginPageLocators.EMAIL_TEXT_FIELD,LoginPageLocators.USERNAME, ElementType.Xpath);
                if(flag){
                    flag = webDB.waitForElement(LoginPageLocators.PASSWORD_TEXT_FIELD, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.sendTextToAnElement(LoginPageLocators.PASSWORD_TEXT_FIELD,"IamVengeance@16", ElementType.Xpath);
                        if(flag){
                            flag = signInButtonClick();
                            if(flag){
                                //flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

}
