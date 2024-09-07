package PageFunctions;

import Locators.CommonLocators;
import Locators.DashboardPageLocators;
import Locators.ProfilePageLocators;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

public class ProfilePage {
    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    boolean flag;

    /**
     * This method verifies profile update page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyProfileUpdateNavigation() {
        flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, WebDriverBase.ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, WebDriverBase.ElementType.Xpath);
            if (flag) {
                log.logging("info", "Profile image is clicked");
                flag = webDB.waitForElement(CommonLocators.PROFILE_OPTION, WebDriverBase.ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Profile option is visible");
                    flag = webDB.clickAnElement(CommonLocators.PROFILE_OPTION, WebDriverBase.ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Clicked profile update option");
                        flag = webDB.waitForElement(ProfilePageLocators.PROFILE_HEADING, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Successfully navigated to profile updating page");
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
     * This method verifies profile update page components
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyProfileUpdatePageComponents() {
        flag = verifyProfileUpdateNavigation();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if(flag){
                log.logging("info", "Name update text field is visible");
                flag = webDB.waitForElement(ProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Update button is visible");
                    flag = webDB.waitForElement(ProfilePageLocators.CANCEL_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "All components are visile on profile update page");
                    }else{
                        log.logging("fail", "Issue on verifying cancel button");
                        flag = false;
                    }
                }else{
                    log.logging("fail", "Issue on verifying update button");
                    flag = false;
                }
            }else{
                log.logging("fail", "Issue on verifying text field");
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies existing admin name
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyExistingAdminName() {
        flag = verifyProfileUpdateNavigation();
        if(flag){
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, WebDriverBase.ElementType.Xpath);
            if (flag) {
                flag = webDB.waitForElement(ProfilePageLocators.ADMIN_NAME, ElementType.CssSelector);
                if(flag){
                    String existingAdmin = webDB.getTextFromElement(ProfilePageLocators.ADMIN_NAME, ElementType.CssSelector);
                    log.logging("info", "Existing admin name is: "+existingAdmin);
                    flag = webDB.waitForElement(ProfilePageLocators.NAME_TEXT_FIELD,ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(ProfilePageLocators.NAME_TEXT_FIELD,ElementType.Id);
                        if(flag) {
                            log.logging("info", "Clicked name text field");
                            String name = webDB.getAttributeFromElement(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id, "value");
                            if (name.equals(existingAdmin)) {
                                log.logging("info", "Existing admin name is verified on text field");
                            } else {
                                log.logging("fail", "Issue on verifying existing admin name");
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
     * This method verifies dashboard link navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDashboardLinkNav() {
        flag = verifyProfileUpdateNavigation();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.DASHBOARD_LINK, ElementType.LinkText);
            if(flag){
                flag = webDB.clickAnElement(ProfilePageLocators.DASHBOARD_LINK, ElementType.LinkText);
                if(flag){
                    log.logging("info", "Clicked dashboard link");
                    flag = webDB.waitForElement(DashboardPageLocators.WELCOME_PAGE_TEXT, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Successfully navigated to dashboard page");
                    }else{
                        log.logging("fail", "Issue on dashboard navigation");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies updated admin name in header
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyUpdatedAdminNameInHeader() throws InterruptedException {
        flag = verifyProfileUpdateNavigation();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if(flag){
                flag = webDB.clearTextField(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
                if(flag){
                    log.logging("info", "Cleared existing admin name");
                    flag = webDB.sendTextToAnElement(ProfilePageLocators.NAME_TEXT_FIELD,"Demo Admin Name", ElementType.Id);
                    if(flag){
                        log.logging("info", "Sent demo admin name in text field");
                        flag = webDB.waitForElement(ProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(ProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked update button");
                                Thread.sleep(2000);
                                flag = webDB.waitForElement(ProfilePageLocators.UPDATED_ADMIN_NAME_HEADER, ElementType.Xpath);
                                if(flag){
                                    String updateName = webDB.getTextFromElement(ProfilePageLocators.UPDATED_ADMIN_NAME_HEADER, ElementType.Xpath);
                                    log.logging("info", "Extracted updated name is: "+updateName);
                                    if(updateName.contains("Demo Admin Name")){
                                        log.logging("info", "Name got updated successfully in header");
                                    }else{
                                        log.logging("fail", "Issue on updating name");
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
     * This method rename admin name
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean renameAdminName() {
        flag = webDB.waitForElement(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
        if(flag){
            flag = webDB.clearTextField(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if(flag){
                log.logging("info", "Cleared name text field");
                flag = webDB.sendTextToAnElement(ProfilePageLocators.NAME_TEXT_FIELD,"Super Admin", ElementType.Id);
                if(flag){
                    log.logging("info", "Name has been changed in text field");
                    flag = webDB.waitForElement(ProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(ProfilePageLocators.UPDATE_BUTTON, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Clicked update button");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies updated name in profile
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyUpdatedAdminNameInProfile() throws InterruptedException {
        flag = verifyUpdatedAdminNameInHeader();
        if(flag){
            flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked profile logo");
                    flag = webDB.waitForElement(CommonLocators.UPDATED_ADMIN_NAME, ElementType.Xpath);
                    if(flag){
                        String text = webDB.getTextFromElement(CommonLocators.UPDATED_ADMIN_NAME, ElementType.Xpath);
                        log.logging("info", "Extracted name is: "+text);
                        if(text.equals("Demo Admin Name")){
                            log.logging("info", "Name has been modified as expected");
                        }else{
                            log.logging("fail", "Isue on modifying name");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies name cange popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNameChangePopup() throws InterruptedException {
        flag = verifyUpdatedAdminNameInHeader();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.UPDATE_NAME_ALERT, ElementType.Xpath);
            if(flag){
                String text = webDB.getTextFromElement(ProfilePageLocators.UPDATE_NAME_ALERT, ElementType.Xpath);
                log.logging("info", "Alert text extracted is: "+text);
                if(text.equals(ProfilePageLocators.ALERT_MSG)){
                    log.logging("info", "Alert text is verified");
                }else{
                    log.logging("fail", "Issue on alert text verification");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies close icon in success message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCloseIconInSuccessMessage() throws InterruptedException {
        flag = verifyUpdatedAdminNameInHeader();
        if(flag){
            flag = webDB.waitForElement(ProfilePageLocators.NAME_ALERT_CLOSE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Name update alert is visible");
                flag = webDB.clickAnElement(ProfilePageLocators.NAME_ALERT_CLOSE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked close icon in alert");
                    Thread.sleep(2000);
                    flag = webDB.isElementNotDisplayed(ProfilePageLocators.NAME_ALERT_CLOSE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Alert popup is closed as expected");
                    }else{
                        log.logging("fail", "Issue while closing the popup");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel button in update page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelButtonInUpdatePage() throws InterruptedException {
        flag = verifyProfileUpdateNavigation();
        if(flag) {
            flag = webDB.waitForElement(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
            if (flag) {
                flag = webDB.clearTextField(ProfilePageLocators.NAME_TEXT_FIELD, ElementType.Id);
                if (flag) {
                    log.logging("info", "Cleared existing admin name");
                    flag = webDB.sendTextToAnElement(ProfilePageLocators.NAME_TEXT_FIELD, "Demo Admin Name", ElementType.Id);
                    if (flag) {
                        log.logging("info", "Sent demo admin name in text field");
                        flag = webDB.waitForElement(ProfilePageLocators.CANCEL_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(ProfilePageLocators.CANCEL_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked cancel button");
                                Thread.sleep(2000);
                                flag = webDB.isElementNotDisplayed(ProfilePageLocators.UPDATED_ADMIN_NAME_HEADER, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Name did not got updated as expected");
                                }else{
                                    log.logging("fail", "Issue on cancel button");
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

}
