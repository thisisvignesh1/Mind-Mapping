package PageFunctions;

/**
 * @author Gokul - GWL
 * @company Galaxy Weblinks May 22, 2024
 */

import Locators.MindMappingLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.time.Duration;
import java.util.List;

public class MindMappingPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    boolean flag;
    String alphabetOnlyErrorMessage;
    String emptyErrorForStartField;
    String emptyErrorForEndField;
    String randomStartWord;
    String randomEndWord;
    String firstRowStartName;
    int numberOfElements;

    /**
     * This method navigates to mind mapping add page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean navigateToMindMappingPage() {
        flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_SIDEBAR, ElementType.Xpath);
        if (flag) {
            log.logging("info", "Mind Mapping side bar is visible.");
            flag = webDB.javaScriptClickAnElement(MindMappingLocators.MIND_MAPPING_SIDEBAR, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Clicked mind mapping side bar");
                flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD, ElementType.Xpath);
            }
        }
        return flag;
    }

    /**
     * This method navigates to mind mapping add page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean navigateToMindMappingAddPage() {
        flag = navigateToMindMappingPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD, ElementType.Xpath);
            if (flag) {
                flag = webDB.clickAnElement(MindMappingLocators.MIND_MAPPING_ADD, ElementType.Xpath);
                if (flag) {
                    flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Navigated to Mind Mapping add page");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies alphabet only error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAlphabetOnlyErrorMessage() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, "123", ElementType.Xpath);
            if (flag) {
                log.logging("info", "Numerical values are entered in start text field");
                flag = webDB.waitForElement(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                        if (flag) {
                            alphabetOnlyErrorMessage = webDB.getTextFromElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                            if (alphabetOnlyErrorMessage.contains(MindMappingLocators.ALPHABET_ONLY_ERROR_MESSAGE)) {
                                log.logging("info", "Verified error message on start text field");
                                webDB.clearTextField(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                                flag = verifyErrorMessageOnEndField();
                                if (flag) {
                                    log.logging("info", "Verified error message on end text field");
                                } else {
                                    log.logging("fail", "Error message text is not verified on end field");
                                    flag = false;
                                }
                            } else {
                                log.logging("fail", "Error message text is not verified on start field");
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
     * This method verifies error message on end field
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyErrorMessageOnEndField() {
        flag = webDB.waitForElement(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
        if (flag) {
            flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, "123", ElementType.Xpath);
            if (flag) {
                log.logging("info", "Numerical values are entered in end text field");
                flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                if (flag) {
                    flag = webDB.clickAnElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        flag = webDB.waitForElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                        if (flag) {
                            alphabetOnlyErrorMessage = webDB.getTextFromElement(MindMappingLocators.ALPHABETS_ONLY_ERROR_MSG, ElementType.Xpath);
                            System.out.println(alphabetOnlyErrorMessage);
                            if (alphabetOnlyErrorMessage.contains(MindMappingLocators.ALPHABET_ONLY_ERROR_MESSAGE)) {
                                webDB.clearTextField(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);

                            } else {
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
     * This method verifies empty field error message
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyFieldErrorMsg() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
            if (flag) {
                flag = webDB.javaScriptClickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked submit button without entering values");
                    flag = webDB.waitForElement(MindMappingLocators.ERROR_MSG_FOR_START_FIELD, ElementType.Id);
                    if (flag) {
                        emptyErrorForStartField = webDB.getTextFromElement(MindMappingLocators.ERROR_MSG_FOR_START_FIELD, ElementType.Id);
                        if (emptyErrorForStartField.contains(MindMappingLocators.EMPTY_FIELD_ERROR_MESSAGE)) {
                            log.logging("info", "Empty field error message is verified for start field");
                            flag = webDB.waitForElement(MindMappingLocators.ERROR_MSG_FOR_END_FIELD, ElementType.Id);
                            if (flag) {
                                emptyErrorForEndField = webDB.getTextFromElement(MindMappingLocators.ERROR_MSG_FOR_END_FIELD, ElementType.Id);
                                if (emptyErrorForEndField.contains(MindMappingLocators.EMPTY_FIELD_ERROR_MESSAGE)) {
                                    log.logging("info", "Empty field error message is verified for end field");
                                } else {
                                    log.logging("info", "Empty field error message is not verified for end field");
                                    flag = false;
                                }
                            }
                        } else {
                            log.logging("info", "Empty field error message is not verified for start field");
                            flag = false;
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel button in add feature
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelBtnInAddFeature() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                randomStartWord = MindMappingLocators.randomAlphabets;
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, randomStartWord, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Text has been passed in start field");
                    flag = webDB.waitForElement(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
                    if (flag) {
                        randomEndWord = MindMappingLocators.randomAlphabets;
                        flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, randomEndWord, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Text has been passed in end field");
                            flag = webDB.waitForElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                            if (flag) {
                                flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "Clicked cancel button");
                                    flag = verifyStartAndEndWordNotGettingUpdated();
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
     * This method verifies start and end word not getting updated
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyStartAndEndWordNotGettingUpdated() {
        WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(5));
        flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_LIST_TABLE, ElementType.Xpath);
        if (flag) {
            log.logging("info", "Navigated to List page");
            boolean isStartElementNotVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[normalize-space()='"+randomStartWord+"']")));
            flag = isStartElementNotVisible;
            if(flag){
                log.logging("info", "Start word is not updated on list as expected");
                boolean isEndElementNotVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[normalize-space()='"+randomEndWord+"']")));
                flag = isEndElementNotVisible;
                if(flag) {
                    log.logging("info", "End word is not updated on list as expected");
                }else{
                    log.logging("info", "List page got updated");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies valid start end word
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyValidStartEndWord() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                randomStartWord = MindMappingLocators.randomAlphabets;
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, randomStartWord, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered valid start name");
                    randomEndWord = MindMappingLocators.randomAlphabets;
                    flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, randomEndWord, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Entered valid end name");
                        flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            log.logging("info", "Clicked submit button");
                            flag = webDB.waitForElement("//td[normalize-space()='"+randomStartWord+"']", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Start word is updated on list");
                                flag = webDB.waitForElement("//td[normalize-space()='"+randomEndWord+"']", ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "End word is updated on list");
                                    flag = deleteFirstRowInList();
                                }else{
                                    log.logging("info", "End word is not visible in list");
                                    flag = false;
                                }
                            }else{
                                log.logging("info", "Start word is not visible in list");
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
     * This method deletes first row of list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean deleteFirstRowInList() {
        flag = webDB.waitForElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
        if(flag){
            flag = webDB.clickAnElement(MindMappingLocators.FIRST_TABLE_DELETE_ICON, ElementType.Xpath);
            if(flag){
                log.logging("info", "Clicked delete icon");
                flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                    log.logging("info", "Clicked confirm on delete popup");
                }
            }
        }
        return flag;
    }

    /**
     * This method naviates to mind mapping list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNavigateToMindMappingListPage() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_LIST_TAB, ElementType.Xpath);
            if(flag) {
                flag = webDB.javaScriptClickAnElement(MindMappingLocators.MIND_MAPPING_LIST_TAB, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked mind mapping list tab");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies sarch bos in mind mapping list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifySearchBoxInList() throws InterruptedException {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.SEARCH_BOX, ElementType.Xpath);
            if(flag){
                log.logging("info", "List search box is visible");
                webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                firstRowStartName = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                log.logging("info", "First row start name is: "+firstRowStartName);
                flag = webDB.sendTextToAnElement(MindMappingLocators.SEARCH_BOX,firstRowStartName, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Start name is sent to search box");
                    Thread.sleep(3000);
                    flag = webDB.waitForElement(MindMappingLocators.TABLE_ROWS, ElementType.Xpath);
                    if(flag){
                        List<WebElement> elements = webDB.getDriver().findElements(By.xpath(MindMappingLocators.TABLE_ROWS));
                        numberOfElements = elements.size();
                        if(numberOfElements == 1){
                            log.logging("info", "After entering name in search box, it shows that details as expected");
                            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                            if(flag){
                                String name = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                                if(name.equals(firstRowStartName)){
                                    log.logging("info", "Start name also matches");
                                }else{
                                    log.logging("info", "Start name did not matched");
                                    flag = false;
                                }
                            }
                        }else{
                            log.logging("info", "Issue on listing items");
                            flag = false;
                        }
                    }

                }
            }
        }
        return flag;
    }

    /**
     * This method verifies mind mapping list status
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyMindMappingListStatusCheck() throws InterruptedException {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
            if(flag){
                String status = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                log.logging("info", "Current status for first row is: "+status);
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row status button");
                    flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Status popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP_CONFIRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.STATUS_POPUP_CONFIRM, ElementType.Xpath);
                            log.logging("info", "Clicked confirm for status popup");
                            Thread.sleep(5000);
                            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                            if(flag) {
                                String postStatus = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                                log.logging("info", "Post status for first row is: " + postStatus);
                                if (status.equals(postStatus)) {
                                    log.logging("info", "Status not changed as expected");
                                    flag = false;
                                } else {
                                    log.logging("info", "Status got changed as expected");
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
     * This method verifies status cancel popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyStatusCancelPopup() {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
            if(flag) {
                String status = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                log.logging("info", "Current status for first row is: " + status);
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row status button");
                    flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Status popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.STATUS_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.STATUS_POPUP_CANCEL, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked cancel button");
                                flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                                if(flag) {
                                    String postStatus = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                                    log.logging("info", "Post status for first row is: " + postStatus);
                                    if (status.equals(postStatus)) {
                                        log.logging("info", "Status not changed as expected");
                                    } else {
                                        log.logging("info", "Status got changed");
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
     * This method verifies list to add page redirection
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifListToAddPageRedirection() {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Edit button in list item is clicked");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to add page as expected");
                    }else{
                        log.logging("fail", "Issue while navigating to add page.");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies valid start end word
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyValidStartEndWordWithoutDeleting() {
        flag = navigateToMindMappingAddPage();
        if (flag) {
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                randomStartWord = MindMappingLocators.randomAlphabets;
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, randomStartWord, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered valid start name");
                    randomEndWord = MindMappingLocators.randomAlphabets;
                    flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, randomEndWord, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Entered valid end name");
                        flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            log.logging("info", "Clicked submit button");
                            flag = webDB.waitForElement("//td[normalize-space()='"+randomStartWord+"']", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Start word is visible in list");
                                flag = webDB.waitForElement("//td[normalize-space()='"+randomEndWord+"']", ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "End word is visible in list");
                                }else{
                                    log.logging("info", "End word is not visible in list");
                                    flag = false;
                                }
                            }else{
                                log.logging("info", "Start word is not visible in list");
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
     * This method verifies edit changes success in list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEditChangesSuccessInListPage() {
        flag = verifyValidStartEndWordWithoutDeleting();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Edit button in list item is clicked");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to add page");
                        webDB.clearTextField(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                        flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, randomStartWord+" Edited", ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Edited start word");
                            webDB.clearTextField(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
                            flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, randomEndWord+" Edited", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Edited end word");
                                flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked submit button");
                                    flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                                    if(flag){
                                        String editedFirstName = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                                        if(editedFirstName.equals(randomStartWord+" Edited")){
                                            log.logging("info", "Name got edited as expected");
                                            flag = deleteFirstRowInList();
                                        }else{
                                            log.logging("info", "Issue on updating name");
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
     * This method verifies edit cancel changes in list page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEditCancelChangesInListPage() {
        flag = verifyValidStartEndWordWithoutDeleting();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_EDIT, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Edit button in list item is clicked");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated to add page");
                        webDB.clearTextField(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
                        flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, randomStartWord+" Edited", ElementType.Xpath);
                        if(flag){
                            log.logging("info", "Edited start word");
                            webDB.clearTextField(MindMappingLocators.END_TEXT_FIELD, ElementType.Xpath);
                            flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, randomEndWord+" Edited", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Edited end word");
                                flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "Clicked cancel button");
                                    flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                                    if(flag){
                                        String editedFirstName = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_START_NAME, ElementType.Xpath);
                                        if(editedFirstName.equals(randomStartWord+" Edited")){
                                            log.logging("fail", "Name got updated which should not be the case");
                                            deleteFirstRowInList();
                                            flag = false;
                                        }else{
                                            log.logging("info", "Name did not get updated as expected");
                                            flag = deleteFirstRowInList();
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
     * This method deletes popup
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopup() {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked first row delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Delete popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        }
                    }else{
                        log.logging("info", "Delete popup is not visible");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies delete confirm on list
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeleteConfirm() throws InterruptedException {
        flag = verifyValidStartEndWordWithoutDeleting();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delete popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.POPUP_DELETE_CONFRM, ElementType.Xpath);
                            log.logging("info", "Clicked delete confirm button");
                            Thread.sleep(3000);
                            WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(3));
                            boolean isEndElementNotVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[normalize-space()='"+randomStartWord+"']")));
                            flag = isEndElementNotVisible;
                            if(flag){
                                log.logging("info", "Selected item got deleted successfully");
                            }else{
                                log.logging("info", "Selected item not deleted as expected");
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
     * This method verifies delete popup cancel
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDeletePopupCancel() throws InterruptedException {
        flag = verifyValidStartEndWordWithoutDeleting();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
            if(flag) {
                flag = webDB.clickAnElement(MindMappingLocators.TABLE_FIRST_ROW_DELETE, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Clicked first row delete icon");
                    flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Delete popup is visible");
                        flag = webDB.waitForElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(MindMappingLocators.DELETE_POPUP_CANCEL, ElementType.Xpath);
                            log.logging("info", "Clicked delete cancel button");
                            Thread.sleep(2000);
                            flag = webDB.waitForElement("//td[normalize-space()='"+randomStartWord+"']", ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Item did not get deleted as expected");
                                flag = deleteFirstRowInList();
                            }else{
                                log.logging("info", "Item got deleted");
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
     * This method verifies add button click redirection
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAddButtonClickRedirection() {
        flag = verifyNavigateToMindMappingListPage();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
                //flag = webDB.clickAnElement(MindMappingLocators.ADD_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked add button on list page");
                    flag = webDB.waitForElement(MindMappingLocators.MIND_MAPPING_ADD_HEADING, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Successfully redirected to add page");
                    }else{
                        log.logging("info", "Issue on add button");
                        flag = false;
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies add button functionality
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAddButtonFunctionality() {
        flag = verifyAddButtonClickRedirection();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if(flag){
                String startWord = MindMappingLocators.randomAlphabets;
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD,startWord, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Entered valid start name");
                    flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD,MindMappingLocators.randomAlphabets, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Entered valid end name");
                        flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                        if(flag){
                            flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Clicked submit button");
                                flag = webDB.waitForElement("//td[normalize-space()='"+startWord+"']", ElementType.Xpath);
                                if(flag){
                                    log.logging("info", "List got updated with new values as expected");
                                    flag = deleteFirstRowInList();
                                }else{
                                    log.logging("info", "Issue on creating new list item");
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
     * This method verifies add button cancel functionality
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAddButtonCancelFunctionality() throws InterruptedException {
        flag = verifyAddButtonClickRedirection();
        if(flag) {
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if (flag) {
                String startWord = MindMappingLocators.randomAlphabets;
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, startWord, ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Entered valid start name");
                    flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, MindMappingLocators.randomAlphabets, ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Entered valid end name");
                        flag = webDB.waitForElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                        if(flag) {
                            flag = webDB.clickAnElement(MindMappingLocators.CANCEL_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked cancel button");
                                Thread.sleep(3000);
                                WebDriverWait wait = new WebDriverWait(webDB.getDriver(), Duration.ofSeconds(2));
                                boolean isEndElementNotVisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//td[normalize-space()='"+startWord+"']")));
                                flag = isEndElementNotVisible;
                                if(flag){
                                    log.logging("info", "After clicking cancel button name not updated as expected");
                                }else{
                                    log.logging("info", "Issue on cancel button");
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
     * This method verifies default status value
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyDefaultStatusValue() {
        flag = verifyValidStartEndWordWithoutDeleting();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
            if(flag){
                log.logging("info", "Status is visible");
                String defaultStatus = webDB.getTextFromElement(MindMappingLocators.TABLE_FIRST_ROW_STATUS, ElementType.Xpath);
                if(defaultStatus.equals("Active")){
                    log.logging("info", "Status is active in default");
                    flag = deleteFirstRowInList();
                }else{
                    log.logging("info", "Issue while fetching status");
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * This method creates words specific to report page
     *
     * @author Gokul - GWL
     * @return booleanflag
     */
    public boolean crateWordsSpecificToReport() {
        flag = verifyAddButtonClickRedirection();
        if(flag){
            flag = webDB.waitForElement(MindMappingLocators.START_TEXT_FIELD, ElementType.Xpath);
            if(flag) {
                flag = webDB.sendTextToAnElement(MindMappingLocators.START_TEXT_FIELD, "Demo start word", ElementType.Xpath);
                if (flag) {
                    log.logging("info", "Entered valid start name");
                    flag = webDB.sendTextToAnElement(MindMappingLocators.END_TEXT_FIELD, "Demo end word", ElementType.Xpath);
                    if (flag) {
                        log.logging("info", "Entered valid end name");
                        flag = webDB.waitForElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                        if (flag) {
                            flag = webDB.clickAnElement(MindMappingLocators.SUBMIT_BUTTON, ElementType.Xpath);
                            if (flag) {
                                log.logging("info", "Clicked submit button");
                                flag = webDB.waitForElement("//td[normalize-space()='Demo start word']", ElementType.Xpath);
                                if (flag) {
                                    log.logging("info", "List got updated with new values as expected");
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
