package PageFunctions;

import Locators.FrontEndConnectionWordsPageLocator;
import Locators.FrontEndLoginLocators;
import Locators.FrontEndResultsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ReportLoger;
import utils.WebDriverBase;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;

import utils.WebDriverBase.ElementType;

public class FrontEndConnectionWordsPage {

    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static FrontEndLoginPage frontEndLoginPage = new FrontEndLoginPage();
    static GroupsPage groupsPage = new GroupsPage();
    boolean flag;

    /**
     * This method verifies logo and feedback presence
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyLogoFeedbackButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndLoginLocators.GALAXY_LOGO, ElementType.Xpath);
            if(flag){
                log.logging("info", "Galaxy logo is visible");
                flag = webDB.waitForElement(FrontEndLoginLocators.FEEDBACK_BTN, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Feedback button is visible");
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies connection words page components
     *
     * @author Gokul - GWL
     * @return boolean flag
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    public boolean verifyConnectionWordsPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, ElementType.Id);
            if(flag){
                log.logging("info", "Connection word input field is visible");
                flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                if(flag){
                    log.logging("info", "Next button is visible");
                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Submit button is visible");
                        flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.CANCEL_BUTTON, ElementType.LinkText);
                        if(flag){
                            log.logging("info", "All components of connection words page is visible");
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies highlighted texts on words entering page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyHighlightedTextInWordsPage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.HEADER_TEXT, ElementType.ClassName);
            if(flag){
                log.logging("info", "Highlighted text is visible");
                String text = webDB.getTextFromElement(FrontEndConnectionWordsPageLocator.HEADER_TEXT, ElementType.ClassName);
                log.logging("info", "Extracted text is: "+text);
                if(text.equals(FrontEndConnectionWordsPageLocator.HEADER_TEXTS)){
                    log.logging("info", "Text is verified");
                }else{
                    log.logging("fail", "Issue on text verification");
                    flag = false;
                    webDB.switchToChildWindow();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies timer on words entry page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyTimerOnWordsEntryPage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER, ElementType.Id);
            if(flag){
                log.logging("info", "Timer is visible");
                flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                if(flag){
                    Thread.sleep(3000);
                    String timeBefore = webDB.getTextFromElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                    log.logging("info", "Extracted time at beginning is: "+timeBefore);
                    Thread.sleep(3000);
                    String timeAfter = webDB.getTextFromElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                    log.logging("info", "Extracted time after a while is: "+timeAfter);
                    if(Integer.parseInt(timeBefore) > Integer.parseInt(timeAfter)){
                        log.logging("info", "Time is reducing as expected");
                    }else{
                        log.logging("fail", "Issue on verifying");
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies numerical error on text field
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNumericalErrorOnTextField() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, "123", ElementType.Id);
                if(flag){
                    log.logging("info", "Integer value is entered on input field");
                    Thread.sleep(2000);
                    String text = webDB.getTextFromElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, ElementType.Id);
                    if(text.isEmpty()){
                        log.logging("info", "Integer value is erased automatically as expected");
                    }else{
                        log.logging("fail", "Issue on verifying numerical value");
                        flag = false;
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty field next button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyFieldNextButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.moveToElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
            if(flag){
                Thread.sleep(1000);
                flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                if(flag){
                    log.logging("info", "Clicked next button without entering any word");
                    Thread.sleep(1000);
                    String classAttr = webDB.getAttributeFromElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, ElementType.Id, "class");
                    if(classAttr.equals(FrontEndConnectionWordsPageLocator.CLASS_ATTR)){
                        log.logging("info", "Issue thrown for empty input field");
                    }else{
                        log.logging("fail", "Issue on verifying error message");
                        flag = false;
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies empty field submit button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyEmptyFieldSubmitButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.moveToElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked submit button");
                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.EMPTY_WORD_ERROR, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Error message is visible for empty word");
                    }else{
                        log.logging("fail", "Issue on verifying empty word error");
                        flag = false;
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies cancel button click navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCancelButtonClickNavigation() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.moveToElement(FrontEndConnectionWordsPageLocator.CANCEL_BUTTON, ElementType.LinkText);
            if(flag){
                flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.CANCEL_BUTTON, ElementType.LinkText);
                if(flag){
                    log.logging("info", "Clicked cancel button");
                    flag = webDB.waitForElement(FrontEndLoginLocators.NAME_TEXT_FIELD, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Navigated back successfully to login page");
                    }else{
                        log.logging("fail", "Issue on navigation");
                        flag = false;
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies next button after word entering
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyNextButtonAfterWordEnter() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.moveToElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
            if(flag){
                flag = webDB.sendTextToAnElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT,"Demo word", ElementType.Id);
                if(flag){
                    log.logging("info", "Entered demo word on input field");
                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                    if(flag){
                        flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                        if(flag){
                            log.logging("info", "Clicked next button");
                            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.WORD_BADGE, ElementType.Xpath);
                            if(flag){
                                log.logging("info", "Entered word is highlighted as a word badge");
                            }else{
                                log.logging("fail", "Issue on highlighting word badge");
                                flag = false;
                                webDB.switchToChildWindow();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies close icon for word badge
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyCloseIconForWordBadge() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyNextButtonAfterWordEnter();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.WORD_BADGE_CLOSE, ElementType.Xpath);
            if(flag){
                log.logging("info", "Word badge is visible");
                flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.WORD_BADGE_CLOSE, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Clicked close icon in word badge");
                    Thread.sleep(1000);
                    flag = webDB.isElementNotDisplayed(FrontEndConnectionWordsPageLocator.WORD_BADGE_CLOSE, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Word badge got removed after clicking close icon");
                    }else{
                        log.logging("fail", "Issue on removing word badge");
                        flag = false;
                        webDB.switchToChildWindow();
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies connection words note
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyConnectionWordsNote() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.NOTE, ElementType.ClassName);
            if(flag){
                String note = webDB.getTextFromElement(FrontEndConnectionWordsPageLocator.NOTE, ElementType.ClassName);
                log.logging("info", "Extracted text is: "+note);
                if(note.equals(FrontEndConnectionWordsPageLocator.NOTE_TEXT)){
                    log.logging("info", "Note text is verified");
                }else{
                    log.logging("fail", "Issue on verifying text");
                    flag = false;
                    webDB.switchToChildWindow();
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies start end words show
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyStartEndWordShow() throws InterruptedException {
        flag = groupsPage.verifyNavigatesToGroupsListPage();
        if(flag) {
            String strtWord = groupsPage.returnStartWordFromWordConnectionPopup();
            String endWrd = groupsPage.returnEndWordFromWordConnectionPopup();
            JavascriptExecutor js = (JavascriptExecutor) webDB.getDriver();
            String url = (String) js.executeScript("return document.getElementById('copy_url102').textContent;");
            log.logging("info", "url is: " + url);
            flag = webDB.openNewTabWithUrl(url);
            if (flag) {
                Thread.sleep(3000);
                flag = frontEndLoginPage.verifyLoginToWordsEnteringPageNav();
                if(flag){
                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.START_WORD, ElementType.Id);
                    if(flag){
                        String word1 = webDB.getAttributeFromElement(FrontEndConnectionWordsPageLocator.START_WORD, ElementType.Id, "value");
                        log.logging("info", "Start word in connection words page is: "+word1);
                        flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.END_WORD, ElementType.Id);
                        if(flag){
                            String word2 = webDB.getAttributeFromElement(FrontEndConnectionWordsPageLocator.END_WORD, ElementType.Id, "value");
                            log.logging("info", "End word in connection words page is: "+word2);
                            if(strtWord.equals(word1) && endWrd.equals(word2)){
                                log.logging("info", "Start and end words are reflected correctly as in admin page");
                            }else{
                                log.logging("fail", "Issue on start and end word");
                                flag = false;
                                webDB.switchToChildWindow();
                            }
                        }
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies timer after submit button
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyTimerAfterSubmitButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyNextButtonAfterWordEnter();
        if(flag){
            flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
            if(flag){
                flag = webDB.javaScriptClickAnElement(FrontEndConnectionWordsPageLocator.SUBMIT_BUTTON, ElementType.Xpath);
                if(flag){
                    Thread.sleep(2000);
                    log.logging("info", "Clicked submit button");
                    flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.TIMER_SECONDS, ElementType.Id);
                    if(flag){
                        log.logging("info", "Navigated to wait timer page");
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies results page navigation
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyResultsPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyTimerAfterSubmitButton();
        if(flag){
            Thread.sleep(35000);
            flag = webDB.waitForElement(FrontEndResultsPageLocators.RESULTS_PAGE_HEADING, ElementType.Xpath);
            if(flag){
                log.logging("info", "Navigated to results page successfully");
                flag = webDB.waitForElement(FrontEndResultsPageLocators.COMMON_CONTAINER, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Common container is visible");
                    flag = webDB.waitForElement(FrontEndResultsPageLocators.MODERATE_CONTAINER, ElementType.Xpath);
                    if(flag){
                        log.logging("info", "Moderate container is visible");
                        flag = webDB.waitForElement(FrontEndResultsPageLocators.RARE_CONTAINER, ElementType.Xpath);
                        if(flag){
                            log.logging("info", "All components of result page is visible");
                        }else{
                            log.logging("fail", "Issue on navigation and components");
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
     * This method passes words specific to reports page
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean passingWordsSpecificToReportsPage() {
        flag = webDB.moveToElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
        if(flag) {
            flag = webDB.sendTextToAnElement(FrontEndConnectionWordsPageLocator.CONNECTION_WORD_INPUT, "Demo word", ElementType.Id);
            if (flag) {
                log.logging("info", "Entered demo word on input field");
                flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                if (flag) {
                    flag = webDB.clickAnElement(FrontEndConnectionWordsPageLocator.NEXT_BUTTON, ElementType.Id);
                    if (flag) {
                        log.logging("info", "Clicked next button");
                        flag = webDB.waitForElement(FrontEndConnectionWordsPageLocator.WORD_BADGE, ElementType.Xpath);
                        if (flag) {
                            log.logging("info", "Entered word is highlighted as a word badge");
                        }
                    }
                }
            }
        }
        return flag;
    }
}
