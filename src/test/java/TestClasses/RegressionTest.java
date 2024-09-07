package TestClasses;

/**
 * @author Gokul - GWL
 * @company Galaxy Weblinks May 21, 2024
 */

import PageFunctions.*;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.*;
import utils.Mailer;
import utils.WebDriverBase;
import utils.ReportLoger;

import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.testng.Assert.assertEquals;

public class RegressionTest {
    WebDriverBase webDB = new WebDriverBase();
    static LoginPage loginPage = new LoginPage();
    static CommonPage commonFunctions = new CommonPage();
    static MindMappingPage mindMappingPage = new MindMappingPage();
    static GroupsPage groupsPage = new GroupsPage();
    static FrontEndLoginPage frontEndLoginPage = new FrontEndLoginPage();
    static FrontEndFeedbackPage frontEndFeedbackPage = new FrontEndFeedbackPage();
    static FeedbackPage feedbackPage = new FeedbackPage();
    static DashboardPage dashboardPage = new DashboardPage();
    static ProfilePage profilePage = new ProfilePage();
    static ChangePasswordPage changePasswordPage = new ChangePasswordPage();
    static FrontEndConnectionWordsPage frontEndConnectionWordsPage = new FrontEndConnectionWordsPage();
    static FrontEndResultsPage frontEndResultsPage = new FrontEndResultsPage();
    static ReportsPage reportsPage = new ReportsPage();

    boolean flag;
    String endTime;
    String startTime;

    /**
     * This method verifies before test
     *
     * @author Gokul - GWL
     * @throws Exception
     */
    @BeforeTest
    public void BrowserOpen() throws Exception {
        webDB.Setup(System.getProperty("platformName"));
        startTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("Test case started at" + " " + startTime);
        webDB.enterURL(webDB.getDataFromProperties("loginPageUrl"));
        Thread.sleep(2000);
    }

    /**
     * This method verifies before method
     *
     * @author Gokul - GWL
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    @BeforeMethod
    public void LoginToHomePage() throws FileNotFoundException, IOException, Exception {
        loginPage.loginWithValidCredentials();
    }

    /**
     * This method verifies login with invalid credentials
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that user should see error message while login using invalid credentials", priority = 1)
    public void VerifyLoginWithInValidCredentials() {
        flag = commonFunctions.verifyLogout();
        if (flag) {
            flag = loginPage.loginWithInValidCredentials();
            if (flag) {
                flag = loginPage.loginWithValidCredentials();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for numerical values
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if admin can navigate to Mind Map Add feature and able to see error mesage 'Please enter only alphabets.\" if numberical characters are entered in start and end text fields.", priority = 2)
    public void VerifyAlphabetOnlyErrorMessage() {
        flag = mindMappingPage.verifyAlphabetOnlyErrorMessage();
        assertEquals(true, flag);
    }

    /**
     * This method veifies empty field error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if admin can navigate to Mind Map Add feature and see \"This field is required\" message when submit button is clicked without entering start and end word.", priority = 3)
    public void VerifyEmptyFieldErrorMessage() {
        flag = mindMappingPage.verifyEmptyFieldErrorMsg();
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button in mind mapping add page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that admin can navigate to Mind Map Add feature. After entering start and end words, if cancel button is clicked, then the list should not get updated.", priority = 4)
    public void VerifyCancelButtonInMindMappingAdd() {
        flag = mindMappingPage.verifyCancelBtnInAddFeature();
        assertEquals(true, flag);
    }

    /**
     * This method verifies valid start and end word updation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if Mind Mapping list gets updated after entering valid start and end words.", priority = 5)
    public void VerifyValidStartAndEndWordUpdation() {
        flag = mindMappingPage.verifyValidStartEndWord();
        assertEquals(true, flag);
    }

    /**
     * This method verifies search box in mind mapping list
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that if admin enters any start name or end name on the list search box, it should show that row details alone.", priority = 6)
    public void VerifySearchBoxInMindMappingList() throws InterruptedException {
        flag = mindMappingPage.verifySearchBoxInList();
        assertEquals(true, flag);
    }

    /**
     * This method verifiesmind mapping list status
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in Mind Mapping list, 'Status' can be changed from active to inactive and vice-versa", priority = 7)
    public void VerifyMindMappingListStatus() throws InterruptedException {
        flag = mindMappingPage.verifyMindMappingListStatusCheck();
        assertEquals(true, flag);
    }

    /**
     * This method verifies status cancel popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in status confirm popup, if cancel is clicked, then it should not change the existing status.", priority = 8)
    public void VerifyStatusCancelPopup() throws InterruptedException {
        flag = mindMappingPage.verifyStatusCancelPopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies list to add page redirection
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that when edit button is clicked, it should redirected to the \"add\" page where admin can edit the start and end words.", priority = 9)
    public void VerifyListToAddPageRedirectionForEdit() {
        flag = mindMappingPage.verifListToAddPageRedirection();
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit changes success in list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, after making changes in \"add\" page, if submit button is clicked, then the changes should get updated in the list page.", priority = 10)
    public void VerifyEditChangesSuccessInListPage() {
        flag = mindMappingPage.verifyEditChangesSuccessInListPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies changes cancel in list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verif that, while making changes in \"add\" page, if cancel button is clicked, then the changes should not get updated in list page.", priority = 11)
    public void VerifyEditChangesCancelInListPage() {
        flag = mindMappingPage.verifyEditCancelChangesInListPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete popup in list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, the delete popup should appear once after clicking delete icon in the list.", priority = 12)
    public void VerifyDeletePopupInList() {
        flag = mindMappingPage.verifyDeletePopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies confirm button on list page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, the list item gets deleted once delete button is clicked from the delete popup", priority = 13)
    public void VerifyDeleteConfirmOnList() throws InterruptedException {
        flag = mindMappingPage.verifyDeleteConfirm();
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete cancel on list page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, the list item should not get deleted if cancel button is clicked on delete popup", priority = 14)
    public void VerifyDeleteCancelOnList() throws InterruptedException {
        flag = mindMappingPage.verifyDeletePopupCancel();
        assertEquals(true,flag);
    }

    /**
     * This method verifies add button on list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, when \"Add\" button is clicked on list page, admin should get redirected to \"add\" page.", priority = 15)
    public void VerifyAddButtonOnListPage() {
        flag = mindMappingPage.verifyAddButtonClickRedirection();
        assertEquals(true,flag);
    }

    /**
     * This method verifies add button functionality on list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, after redirected to 'Add page, admin should able to enter valid start and end word. If submit button is clicked then the \n" +
            "list should get get updated on list item.", priority = 16)
    public void VerifyAddButtonFunctionalityInListPage() {
        flag = mindMappingPage.verifyAddButtonFunctionality();
        assertEquals(true,flag);
    }

    /**
     * This method verifies add button cancel functionality
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, after redirected to 'Add page, admin should able to enter valid start and end word. If cancel button is clicked then the \n" +
            "list should not get get updated on list item.", priority = 17)
    public void VerifyAddButtonCancelFunctionality() throws InterruptedException {
        flag = mindMappingPage.verifyAddButtonCancelFunctionality();
        assertEquals(true,flag);
    }

    /**
     * This method verifies default status of list item
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, while creating a new list item, the default status should be 'Active'.", priority = 18)
    public void VerifyDefaultStatusOfListItem() throws InterruptedException {
        flag = mindMappingPage.verifyDefaultStatusValue();
        assertEquals(true,flag);
    }

    /**
     * This method verifies navigation to groups tab
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that admin can navigate to Groups \"add\" menu and able to enter group name in text field.", priority = 19)
    public void VerifyNavigationToGroupsTab() {
        flag = groupsPage.verifyNavigationToGroupTab();
        assertEquals(true,flag);
    }

    /**
     * This method verifies group name error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, Groups name text field should not accept any numerical values. It should show the error message.", priority = 20)
    public void VerifyGroupNameErrorMessage() {
        flag = groupsPage.verifyGroupNameErrorMsg();
        assertEquals(true,flag);
    }

    /**
     * This method verifies valid group name updation
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that admin can enter valid group name and clicks submit button. The group name should be updated in list tab.", priority = 21)
    public void VerifyValidGroupNameUpdation() throws InterruptedException {
        flag = groupsPage.verifyValidGroupNameUpdate();
        assertEquals(true, flag);
    }

    /**
     * This method verifies group name update cancel
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that admin enter valid group name and if clicks cancel. The group name should not get updated in list tab.", priority = 22)
    public void VerifyGroupNameUpdateCancel() throws InterruptedException {
        flag = groupsPage.verifyGroupNameCancel();
        assertEquals(true, flag);
    }

    /**
     * This method verifies add button in group list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that if Add button in the list tab is clicked, admin should redirected to list add tab to create a new group", priority = 23)
    public void VerifyAddButtonInGroupListPage() {
        flag = groupsPage.verifyAddButtonInListPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies add button to create new group
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, after navigating to list \"add\" page, if valid group name is entered and clicked submit button, new group should get\n" +
            "created on groups list page.", priority = 24)
    public void VerifyAddButtonToCreateNewGroup() throws InterruptedException {
        flag = groupsPage.verifyAddToCreateNewGrp();
        if(flag){
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verififes cancel button on create new group
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, after navigating to list \"add\" page, if valid group name is entered and clicked cancel button, new group should not get\n" +
            "created on groups list page.", priority = 25)
    public void VerifyAddButtonToCreateNewGroupCancel() {
        flag = groupsPage.verifyAddButtonToCancelNewGroup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies search box in list page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that in list \"search\" box, if any group name is entered, then it should show that group details alone.", priority = 26)
    public void VerifySearchBoxInList() throws InterruptedException {
        flag = groupsPage.verifySearchBoxInListPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies default word connection count
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that if new group is created, then the default word connection for that list item should be zero.", priority = 27)
    public void VerifyDefaultWordConnectionCount() {
        flag = groupsPage.verifyDefaultWordConnectionCount();
        assertEquals(true, flag);
    }

    /**
     * This method verifies popup on word connection
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that when the default word connection \"0\" is clicked, a popup should appear for assigning start and end words.", priority = 28)
    public void VerifyPopupOnWordConnection() throws InterruptedException {
        flag = groupsPage.verifyPopupOnWordConnection();
        if(flag) {
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies word connection count
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that after assigning the words, the \"word connection\" count should be changed from 0 to 1", priority = 29)
    public void VerifyWordConnectionCount() throws InterruptedException {
        flag = groupsPage.verifyWordPopupConnectionCount();
        if(flag) {
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies default status of list item
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, the default status of list item status should be \"active\".", priority = 30)
    public void VerifyDefaultGroupStatusOfListItem() {
        flag = groupsPage.verifyDefaultStatusOfListItem();
        if(flag) {
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies active to inactive status change
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, when \"active\" button is clicked it should show the popup where we can change the status. It should be changed from\n" +
            "\"active\" to \"inactive\"", priority = 31)
    public void VerifyActiveToInactiveStatusChange() throws InterruptedException {
        flag = groupsPage.verifyActiveToInactiveStatusChange();
        if(flag) {
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies creation date in list item
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in list item, \"created date\" shows the actual creation date of the group list item", priority = 32)
    public void VerifyCreationDateInListItem() {
        flag = groupsPage.verifyCreationDateInList();
        if(flag) {
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies copy clipboard link
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that when \"Copy\" link is clicked, it should generate an unique url.", priority = 33)
    public void VerifyCopyClipboardLink() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = groupsPage.verifyCopyClipboardLink();
        if(flag) {
            //flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit button redirection
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that if edit button is clicked on any list item, then it should redirect to add tab where name can be edited.", priority = 34)
    public void VerifyEditButtonRedirection() {
        flag = groupsPage.verifyEditButtonRedirection();
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit confirm in list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if submit button is clicked while editing the list name, then the updated name should be reflected on list page", priority = 35)
    public void VerifyEditConfirmInList() {
        flag = groupsPage.verifyEditConfirmInList();
        if(flag){
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies edit cancel list
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if cancel button is clicked while editing the list name, then the updated name should not be reflected on list page", priority = 36)
    public void VerifyEditCancelList() {
        flag = groupsPage.verifyEditCancelList();
        if(flag){
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * Verifies deete popup in list page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that if delete button is clicked on any list item, then delete popup should appear on screen.", priority = 37)
    public void VerifyDeletePopupInListPage() {
        flag = groupsPage.verifyDeletePopupInList();
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete confirm on group
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in delete popup, if delete button is clicked, then it should delete the list item.", priority = 38)
    public void VerifyDeleteConfirmOnGroup() throws InterruptedException {
        flag = groupsPage.verifyDeleteConfirmOnGroup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete cancel on group
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in delete popup, if cancel button is clicked, then it should not delete the list item.", priority = 39)
    public void VerifyDeleteCancelOnGroup() throws InterruptedException {
        flag = groupsPage.verifyDeleteCancelOnGrp();
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation to mind mapping front end page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, when new group is created and mind mapping words are added to that group, the url should get generated and if \n" +
            "admin clicks \"copy\" link, url should get copied so that user can navigate to Mind Mapping Front end page using that url.", priority = 40)
    public void VerifyNavigationToMindMappingFrontEnd() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = groupsPage.navigateToMindMappingFrontEnd();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies galaxy logo in front end page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that the url generated is navigating user into the Mind Mapping Frond end page which has galaxy logo.", priority = 41)
    public void VerifyGalaxyLogoInFrontEndPage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyGalaxyLogoInFrontEnd();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies front end login page components
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that the Mind Mapping Front end page has Feedback button, Name text field, Email text field, Next and Cancel buttons.",  priority = 42)
    public void VerifyFrontEndLoginPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFrontEndLoginComponents();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for name field
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that in \"Name\" text field if numbers are entered, then the error message should show as \"Please enter only alphabets.\"", priority = 43)
    public void VerifyErrorMessageForNameField() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyErrorMessageForNameFld();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This message verifies error message for email field
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that in \"Email\" text field if invalid email address is entered, then it should show error message as \"Please enter a valid \n" +
            "email address.\"", priority = 44)
    public void VerifyErrorMessageForEmailField() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyErrorMessageForEmailFld();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty name email fields
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if \"Next\" button is clicked without entering name and email, then the error message should display as \"This field is \n" +
            "required\"", priority = 45)
    public void VerifyEmptyNameEmailFields() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyEmptyEmailNameField();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button in login
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if valid name and valid email is entered in text fields and clicked \"cancel\", then it should not navigate to the next page.", priority = 46)
    public void VerifyCancelButtonInLogin() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyCancelButtonInLogin();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies error message for invalid email and valid name
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that when valid name and invalid email is entered and user clicks \"next\". It should not navigate to next page and error \n" +
            "message should be visible for invalid email id.", priority = 47)
    public void VerifyValidNameInvalidEmailLogin() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyValidNameInvalidEmail();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies Invalid name and valid email login
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that when invalid name and valid email is entered and user clicks \"next\". It should not navigate to next page and error\n" +
            "message should be visible for invalid name field.", priority = 48)
    public void VerifyInValidNameValidEmailLogin() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyInvalidNamevalidEmail();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies namefield letters count error
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, \"Name\" text field should not accepts the words less than 3 letters.", priority = 49)
    public void VerifyNameFieldLettersCount() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyNameFieldLettersCount();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    @Test(description = "Verify that, if \"Feedback\" button is clicked, then user should get navigated into feedback page.", priority = 50)
    public void VerifyFeedBackPageNavigation() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyFeedBackPageNavigation();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback page componenets
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the feedback page has  all components like \"Name\", \"Email\", \"Message\" text fields and \"Submit\", \"Cancel\" buttons.", priority = 51)
    public void VerifyFeedbackPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackPageComponents();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback page heading
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that feedback page has the heading text as \"Please share your feedback\".", priority = 52)
    public void VerifyFeedbackPageHeading() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackPageHeading();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid name in feedback
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, when invalid name is entered in \"Name\" text field, it should throw error message as \"Please enter only alphabets.\"", priority = 53)
    public void VerifyInvalidNameInFeedback() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyInvalidNameInFeedback();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies invalid email error message in feedback page
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, when invalid email is entered in \"Email\" text field, it should throw error message as \"Please enter a valid email address.\"", priority = 54)
    public void VerifyInvalidEmailErrorInFeedback() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyInvalidEmailError();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty email error message
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, if valid name and feedback text is entered and clicked submit without entering email. Then error message should show as \n" +
            "\"This field is required.\"", priority = 55)
    public void VerifyEmptyEmailErrorMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyEmptyEmailErrorMessage();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty name error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if valid email and feedback text is entered and clicked submit without entering name. Then error message should show as \n" +
            "\"This field is required.\"", priority = 56)
    public void VerifyEmptyNameErrorMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyEmptyNameErrorMessage();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty feedback error
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify if, valid name and email is entered and clicked submit without entering feedback. Then error message should show as \"This field is required.\"", priority = 57)
    public void VerifyEmptyFeedbackErrorMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyEmptyFeedbackError();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback length error
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, in feed back text field, if less than 10 characters are entered, then it should throw an error as \"Feedbck must be at least 10 characters long.\"", priority = 58)
    public void VerifyFeedbackLengthError() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackLengthError();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies name field length
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, in name field, if less than 3 characters enetered then it should show error as \"Name must be at least 3 characters long.\"", priority = 59)
    public void VerifyNameFieldLength() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyNameFieldLength();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button redirection
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, if valid name, email and feedback is entered and clicked cancel, user should navigate back to login page.", priority = 60)
    public void VerifyCancelButtonRedirection() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyCancelButtonRedirection();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback success message
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, after succesfully sending feedback, Success message should show as \"Your feedback sent successfully!\".", priority = 61)
    public void VerifyFeedbackSuccessMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSuccessMessage();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback success message close symbol
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that the feedback success message has \"x\" button, and if it is clicked, then it should close the success message.", priority = 62)
    public void VerifyCloseButtonOnSuccessMsg() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSuccessMessageCloseBtn();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button after feedback submission
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, after sending successfull feedback, \"Cancel\" button is clicked, then it should come back to the login page.", priority = 63)
    public void VerifyCancelBtnAfterFeedbackSubmission() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyCancelBtnAfterFeedback();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback sender details
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that the feedback sender details are updated in admin end \"Feedback\" tab.", priority = 64)
    public void VerifyFeedbackSenderDetails() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndFeedbackPage.verifyFeedbackSenderDetails();
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback popup view
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, the feedback popup appears after clicking \"View\" link.", priority = 65)
    public void VerifyFeedbackPopupView() {
        flag = feedbackPage.verifyFeedbackPopupView();
        if(flag){
            flag = feedbackPage.closeFeedbackPopup();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback popup components
     *
     * @author Gokul - GWl
     */
    @Test(description = "Verify that feedback popup has its all componenets like \"View Feedback\" title, \"Ok\" button and \"x\"(close) button.", priority = 66)
    public void VerifyFeedbackPopupComponents() {
        flag = feedbackPage.verifyFeedbackPopupComponents();
        if(flag){
            flag = feedbackPage.closeFeedbackPopup();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback popup ok button
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Ok\" is clicked on feedback popup, it should close the feedback popup.", priority = 67)
    public void VerifyFeedbackPopupOkBtn() throws InterruptedException {
        flag = feedbackPage.verifyFeedbackPopupOkBtn();
        assertEquals(true, flag);
    }

    /**
     * This method verifies close button in feedback popup
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"x\"(close) is clicked, then feedback popup should get closed.", priority = 68)
    public void VerifyCloseButtonOnPopup() throws InterruptedException {
        flag = feedbackPage.verifyCloseBtnOnPopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies actual feedback message
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, when \"View\" link is clicked, it should show the actual feedback message which was entered in front end page.", priority = 69)
    public void VerifyActualFeedbackMessage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = feedbackPage.verifyActualFeedbackMessage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback created date
     *
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the feedback created date is correctly reflected back on admin end feedback page.", priority = 70)
    public void VerifyFeedbackCreatedDate() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = feedbackPage.verifyFeedbackCreatedDate();
        if(flag){
            flag = feedbackPage.deleteFeedbackContent();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete icon popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if delete icon is clicked, then it should show the delete popup.", priority = 71)
    public void VerifyDeleteIconPopup() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = feedbackPage.verifyDeletePopup();
        if(flag){
            flag = feedbackPage.deleteFeedbackContent();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies delete popup message
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in delete popup the following message should be shown \"Are you sure you want to delete this feedback?\"", priority = 72)
    public void VerifyDeletePopupMessage() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = feedbackPage.verifyDeletePopupMessage();
        if(flag){
            flag = feedbackPage.deleteFeedbackContent();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies close icon in delete popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if close icon \"x\" is clicked, it should close the delete popup.", priority = 73)
    public void VerifyCloseIconOnDeletePopup() throws InterruptedException {
        flag = feedbackPage.verifyDeletePopupClose();
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button in delete popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if cancel button is clicked on popup, it should close the popup and should not delete the selected content.", priority = 74)
    public void VerifyCancelButtonInDeletePopup() throws InterruptedException {
        flag = feedbackPage.verifyCancelButtonInDeletePopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies popup delete confirm
     *
     * @throws InterruptedException
     * @throws IOException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, if \"ok\" button is clicked on popup, it should delete the selected content.", priority = 75)
    public void VerifyConfirmDeletePopup() throws InterruptedException, IOException, UnsupportedFlavorException, AWTException {
        flag = feedbackPage.verifyConfirmDeletePopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies show button count
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in \"Show\" select box, if 10 is selected, then it should show only 10 feedback details.", priority = 76)
    public void VerifyShowButtonNumber() throws InterruptedException {
        flag = feedbackPage.verifyShowButtonNumber();
        assertEquals(true, flag);
    }

    /**
     * This method verifies search box functionality
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in \"Search\" box, if feedback, sender name or email is enetered, then it should show their details only.", priority = 77)
    public void VerifySearchBoxFunctionality() throws InterruptedException {
        flag = feedbackPage.verifySearchBoxFunctionality();
        assertEquals(true, flag);
    }

    /**
     * This method verifies clear text on search box
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in \"Search\" box, if \"x\" symbol is clicked, then it should empty the search box.", priority = 78)
    public void VerifyClearTextOnSearchBox() throws InterruptedException {
        flag = feedbackPage.verifyClearTextInSearchBox();
        assertEquals(true, flag);
    }

    /**
     * This method verifies feedback to dashboard page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in feedback tab, if \"Dashboard\" is clicked on, \"Dashboard / Feedback List\", then it admin should navigated into dashbaord page.", priority = 79)
    public void VerifyFeedbackToDashboardNavigation() {
        flag = feedbackPage.verifyFeedbackToDashboardPageNav();
        assertEquals(true, flag);
    }

    /**
     * This method verifies Number of table entries
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, number of entries are showing exactly same as the count mentioned below table.", priority = 80)
    public void VerifyNumberOfTableEntries() {
        flag = feedbackPage.verifyNumberOfTableEntries();
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination next button
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that in pagination, if next button is clicked, it should navigate to next page.", priority = 81)
    public void VerifyPaginationNextButton() throws InterruptedException {
        flag = feedbackPage.verifyPaginationNextButton();
        assertEquals(true, flag);
    }

    /**
     * This method verifies previous button in pagination
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that in pagination, after navigated to next page, \"previous\" button should get enabled.", priority = 82)
    public void VerifyPreviousButtonInPagination() throws InterruptedException {
        flag = feedbackPage.verifyPreviousButtonInPagination();
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination navigation
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that in pagination, after navigated to next page, if \"previous\" button is clicked, admin should navigated back to previous page.", priority = 83)
    public void VerifyPaginationNavigation() throws InterruptedException {
        flag = feedbackPage.verifyPaginationNextButton();
        assertEquals(true, flag);
    }

    /**
     * This method verifies dashboard page welcome text
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in admin end dashboard page, welcome text should be visible as \"Welcome back, Super Admin!\"", priority = 84)
    public void VerifyDashboardPageWelcomeText() {
        flag = dashboardPage.verifyDashboadHeadingText();
        assertEquals(true, flag);
    }

    /**
     * This method verifies dashboard page contents
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, the dashboard page contains \"Participants\", \"Groups\" and \"Recent Participants\" contents.", priority = 85)
    public void VerifyDahboardPageContents() {
        flag = dashboardPage.verifyDashboardPageContents();
        assertEquals(true, flag);
    }

    /**
     * This method verifies recent participants contents
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Recent Participants\" table contains columns for \"Name\", \"Email\" and \"Created At\"", priority = 86)
    public void VerifyRecentParticipantsContents() {
        flag = dashboardPage.verifyRecentParticipantsContents();
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants count for new user
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Participants\" count should get increased if a new user is logged in on mind mapping front end page.", priority = 87)
    public void VerifyParticipantCountForNewUser() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = dashboardPage.verifyParticipantCountForNewUser();
        assertEquals(true, flag);
    }

    /**
     * This method verifies participant count for existing user
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Participants\" count should not get increased if an existing user logged in on mind mapping front end page.", priority = 88)
    public void VerifyParticipantCountForExistingUser() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = dashboardPage.verifyParticipantCountForExistingUser();
        assertEquals(true, flag);
    }

    /**
     * This method verifies group count increase
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Groups\" count should get increased once a new group is created on admin page.", priority = 89)
    public void VerifyGroupCountIncrease() throws InterruptedException {
        flag = dashboardPage.verifyGroupCountIncrease();
        if(flag){
            flag = mindMappingPage.deleteFirstRowInList();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies existing group count
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Groups\" count should not get increased once an existing group is tried to create.", priority = 90)
    public void VerifyExistingGroupCount() {
        flag = dashboardPage.verifyExistingGroupCount();
        assertEquals(true, flag);
    }

    /**
     * This method verifies deleted group count
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if an existing group gets deleted, then the groups count should get decreased.", priority = 91)
    public void VerifyDeletedGroupCount() throws InterruptedException {
        flag = dashboardPage.verifyDeletedGroupCount();
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants count for different email
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the \"participants\" count should get increased if user logged in with same name and different email id.", priority = 92)
    public void VerifyParticipantsCountForDifferentEmail() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = dashboardPage.verifyParticipantCountForNewUser();
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants count for different name
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the \"participants\" count should not get increased if user logged in with different name with existing email id.", priority = 93)
    public void VerifyParticipantCountForDifferentName() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = dashboardPage.verifyParticipantCountForDifferentName();
        assertEquals(true, flag);
    }

    /**
     * This method verifies admin icon options
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in top right corner user icon should be there and if it is clicked, then it should show the \"Profile\" option", priority = 94)
    public void VerifyAdminIconOptions() throws InterruptedException {
        flag = commonFunctions.verifyAdminIconOptions();
        assertEquals(true, flag);
    }

    /**
     * This method verifies profile update page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if profile option is clicked, then it should navigate to profile updating page.", priority = 95)
    public void VerifyProfileUpdatePageNavigation() {
        flag = profilePage.verifyProfileUpdateNavigation();
        assertEquals(true, flag);
    }

    /**
     * This method verifies pforil update page componenets
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, profile updating page has the name text field, cancel button and submit button. ", priority = 96)
    public void VerifyProfileUpdatePageComponents() {
        flag = profilePage.verifyProfileUpdatePageComponents();
        assertEquals(true, flag);
    }

    /**
     * This method verifies existing admin name
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, by default the name text field should contain existing admin name.", priority = 97)
    public void VerifyExistingAdminName() {
        flag = profilePage.verifyExistingAdminName();
        assertEquals(true, flag);
    }

    /**
     * This method verifies dashboard link navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if Dashboard link is clicked, then user should navigated to dashboard page.", priority = 98)
    public void VerifyDashboardLinkNavigation() {
        flag = profilePage.verifyDashboardLinkNav();
        assertEquals(true, flag);
    }

    /**
     * This method verifies admin name update in header
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if existing admin name is cleared and entered new name and clicked submit button, then the new name should get updated in top.", priority = 99)
    public void VerifyUpdatedAdminNameInHeader() throws InterruptedException {
        flag = profilePage.verifyUpdatedAdminNameInHeader();
        if(flag){
            flag = profilePage.renameAdminName();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies updated admin name in profile
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if existing admin name is changed then the new name should get reflected below profile button.", priority = 100)
    public void VerifyUpdatedAdminNameInProfile() throws InterruptedException {
        flag = profilePage.verifyUpdatedAdminNameInProfile();
        if(flag){
            flag = profilePage.renameAdminName();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies name change popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that if existing admin name is changed, then success message popup should appear as 'Admin name updated successfully!\"", priority = 101)
    public void VerifyNameChangePopup() throws InterruptedException {
        flag = profilePage.verifyNameChangePopup();
        if(flag){
            flag = profilePage.renameAdminName();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies close icon in success message
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that in update success popup, if \"x\" icon is clicked, then it should close the popup message.", priority = 102)
    public void VerifyCloseIconInSuccessMessage() throws InterruptedException {
        flag = profilePage.verifyCloseIconInSuccessMessage();
        if(flag){
            flag = profilePage.renameAdminName();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button in update page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if new admin name is entered on text field and if cancel button is clicked, then it should not update the new admin name.", priority = 103)
    public void VerifyCancelButtonInUpdatePage() throws InterruptedException {
        flag = profilePage.verifyCancelButtonInUpdatePage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies profile logo change passwordoption
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if admin logo is clicked on top right corner, it should show \"Change Password\" option.", priority = 104)
    public void VerifyProfileLogoChangePassword() throws InterruptedException {
        flag = commonFunctions.verifyChangePasswordOption();
        assertEquals(true, flag);
    }

    /**
     * This method verifies change passwrod page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if \"Change Password\" option is clicked, then user should navigated to Change Password page.", priority = 105)
    public void VerifyChangePasswordPageNavigation() {
        flag = changePasswordPage.changePasswordPageNavigation();
        assertEquals(true, flag);
    }

    /**
     * This method verifies dashbaord page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in Change Password page, if \"Dashboard\" link is clicked, then user should navigated to dashboard page.", priority = 106)
    public void VerifyChangePasswordDashboardLinkNavigation() throws InterruptedException {
        flag = changePasswordPage.dashboardPageNavigation();
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password page components
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, Change Password page contains \"Old Password\", \"New Password\", \"Confirm Password\" text fields & \"Update\", \"Cancel\" buttons.", priority = 107)
    public void VerifyChangePasswordComponents() {
        flag = changePasswordPage.verifyChangePasswordComponents();
        assertEquals(true, flag);
    }

    /**
     * This method verifies Change password success message
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, correct old password is entered on \"Old Password\" field and valid new password is entered on \"New Password\" and \"Confirm Password\",\n" +
            "if submit button is clicked, then the success popup should appear as \"Your password was successfully updated!\"", priority = 108)
    public void VerifyChangePasswordSuccessPopup() throws InterruptedException {
        flag = changePasswordPage.changePasswordSuccessPopup();
        if(flag) {
            flag = webDB.navigateToRefresh();
            if (flag) {
                flag = changePasswordPage.changeToDefaultPassword();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies change password success close button
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, after updating new password, in success popup, \"x\" icon must be visible and if it is clicked, then it should close the popup.", priority = 109)
    public void VerifyChangePasswordSuccessCloseBtn() throws InterruptedException {
        flag = changePasswordPage.verifyChangePasswordSuccessCloseBtn();
        if(flag) {
            flag = webDB.navigateToRefresh();
            if (flag) {
                flag = changePasswordPage.changeToDefaultPassword();
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies login with modified pasword
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, after updating new password, try to signout and sign in again with new password. It should get successfully login.", priority = 110)
    public void VerifyChangedPasswordLogin() throws InterruptedException {
        flag = changePasswordPage.changePasswordSuccessPopup();
        if(flag){
            flag = commonFunctions.verifyLogout();
            if(flag){
                flag = loginPage.loginWithNewValidCredentials();
                if(flag){
                    flag = changePasswordPage.changePasswordPageNavigation();
                    if(flag){
                        flag = changePasswordPage.changeToDefaultPassword();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty fields error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if \"Update\" button is clicked without entering any values, then it should show an error as \"This field is required.\" on all fields.", priority = 111)
    public void VerifyEmptyFieldsErrorMessage() {
        flag = changePasswordPage.verifyEmptyFieldErrorMessage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button to dashboard page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if \"Cancel\" button is clicked without entering any values, then it should navigate back to dashboard page.", priority = 112)
    public void VerifyCancelButtonDashboardNavigation() {
        flag = changePasswordPage.verifyCancelButtonDashboardNav();
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty error message on new update password
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if, valid old password is entered and clicked \"update\" button without entering new password in \"New password\" and \"Confirm password\" field\n" +
            "then the error message should show below those text field as \"This field is required\".", priority = 113)
    public void VerifyEmptyErrorMessageOnNewUpdatePwd() throws InterruptedException {
        flag = changePasswordPage.verifyEmptyErrorMessageOnNewUpdatePwd();
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty error message on old update password
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if, \"New password\" is entered and clicked \"update\" button without entering \"Old password\" and \"Confirm Password\" then error message should\n" +
            "show below those text field as \"This field is required\".", priority = 114)
    public void VerifyEmptyErrorMessageOnOldUpdatePwd() throws InterruptedException {
        flag = changePasswordPage.verifyEmptyErrorMessageOnOldUpdatePwd();
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty error message on old and new password
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify if, \"Confirm password\" is entered and clicked \"update\" button without entering \"Old password\" and \"New Password\" then error message should\n" +
            "show below those text field as \"This field is required\".", priority = 115)
    public void VerifyEmptyErrorMessageOnOldNewPwd() {
        flag = changePasswordPage.verifyEmptyErrorMessageOnOldNewPwd();
        assertEquals(true, flag);
    }

    /**
     * This method verifies new password length error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that in \"New password\" text field if password is entered with less than 8 characters, then it should show error message as \"New password must \n" +
            "be at least 8 characters long.\"", priority = 116)
    public void VerifyNewPasswordLengthErrorMsg() {
        flag = changePasswordPage.verifyNewPasswordLengthErrorMsg();
        assertEquals(true, flag);
    }

    /**
     * This method verifies confirm password length error message
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that in \"Confirm password\" text field if password is entered with less than 8 characters,then it should show an error message as \"Confirm \n" +
            "password must be at least 8 characters long.\"", priority = 117)
    public void VerifyConfirmPasswordLengthError() {
        flag = changePasswordPage.verifyConfirmPasswordLengthError();
        assertEquals(true, flag);
    }

    /**
     * This method verifies new password mismatch error
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if valid \"Old password\" and \"New password\" is entered, in \"Confirm password\" field, if different new password entered and if user click\n" +
            "submit, then it should show an error message as \"The new passwords do not match\"", priority = 118)
    public void VerifyNewPasswordMismatchError() {
        flag = changePasswordPage.verifyNewPasswordMismatchError();
        assertEquals(true, flag);
    }

    /**
     * This method verifies old password wrong error
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that if wrong \"Old password\" is entered and clicked submit with new password, then it should show an error message as \"Old password is \n" +
            "incorrect\"", priority = 119)
    public void VerifyOldPasswordWrongError() {
        flag = changePasswordPage.verifyOldPasswordWrongError();
        assertEquals(true, flag);
    }

    /**
     * This method verifies login to words entering page navigation
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if valid name and email is entered and clicked \"next\". Then user should be navigated to the page where they enter\n" +
            "\"Connection words\" in given text field.", priority = 120)
    public void VerifyLoginToWordsEnteringPageNav() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndLoginPage.verifyWordsEnteringPageNavigation();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies logo and feedback button presence
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that this \"Connection words\" entering page has Feedback button and Galaxy logo in it.", priority = 121)
    public void VerifyLogoFeedbackButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyLogoFeedbackButton();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies connection words page components
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the words entering page has its components like, \"Connection words\" text field, \"next\" button, \"Submit\" button and \n" +
            "\"Cancel\" button.", priority = 122)
    public void VerifyConnectionWordsPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyConnectionWordsPageComponents();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies highlighted texts in words page
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, the words entering page has a highlighted text as \"Please use this tool to understand how the group members think\"", priority = 123)
    public void VerifyHighlightedTextInWordsPage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyHighlightedTextInWordsPage();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies timer on words entry page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that the timer on the mind mapping words entry page counts down correctly from 50 to 0 seconds.", priority = 124)
    public void VerifyTimerOnWordsEntryPage() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyTimerOnWordsEntryPage();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies numerical error on text field
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that \"Connection words\" text field should accept only alphabets and not numerical values.", priority = 125)
    public void VerifyNumericalErrorOnTextField() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyNumericalErrorOnTextField();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empty field submit button
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if \"submit\" button is clicked without entering any connection words, then it should throw an error message.", priority = 126)
    public void VerifyEmptyFieldNextButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyEmptyFieldNextButton();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies empt field submit button
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if \"submit\" button is clicked without entering any conection words, then it should throw an error message as \n" +
            "\"Please enter atleast a word.\"", priority = 127)
    public void VerifyEmptyFieldSubmitButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyEmptyFieldSubmitButton();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies cancel button click navigation
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that if \"cancel\" button is clicked without entering any connection words, then it should navigate back to the page where\n" +
            "we need to enter name and email again.", priority = 128)
    public void VerifyCancelButtonClickNavigation() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyCancelButtonClickNavigation();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies next button after word enter
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, when words are entered in \"Connection words\" field and clicked \"Next\", then entered name should get visible above\n" +
            "the text field.", priority = 129)
    public void VerifyNextButtonAfterWordEnter() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyNextButtonAfterWordEnter();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies close icon for word badge
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that the entered word has \"x\" symbol on it and if it is clicked, then it should remove that word from that highlighted list.", priority = 130)
    public void VerifyCloseIconForWordBadge() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyCloseIconForWordBadge();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies connection words note
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that below \"Connection words\" text field there should be message \"(Note: Kindly click the 'Next' button after each answer)\"", priority = 131)
    public void VerifyConnectionWordsNote() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyConnectionWordsNote();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies start and end word in connection words page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, the starting and ending words are represented correctly from the admin end.", priority = 132)
    public void VerifyStartEndWordShow() throws InterruptedException {
        flag = frontEndConnectionWordsPage.verifyStartEndWordShow();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies timer after submit button
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, after entering connection words, if submit button is clicked, then it should naviagte to \"Countdown\" page where the page has timer on it", priority = 133)
    public void VerifyTimerAfterSubmitButton() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyTimerAfterSubmitButton();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies resutlts page components
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, results page has its components like \"Thank you\" text in heading, \"Common\", \"Moderate\", \"Rare\" containers.", priority = 134)
    public void VerifyResultPageComponents() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyResultsPageComponents();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies connection words on container
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, the words entered on previous page is reflected on any one of the three containers.", priority = 135)
    public void VerifyConnectionWordsOnContainer() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndResultsPage.verifyConnectionWordsOnContainer();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies duplicate word on containers
     *
     * @author Gokul - GWL
     * @throws IOException
     * @throws InterruptedException
     * @throws UnsupportedFlavorException
     * @throws AWTException
     */
    @Test(description = "Verify that, if existing words are entered and clicked submit, duplicate words should not get stored on container.", priority = 136)
    public void VerifyDuplicateWordContainers() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndResultsPage.verifyDuplicateWordContainers();
        if(flag){
            flag = webDB.switchToChildWindow();
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies group name delete confirm button
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in reports page, if delete icon is clicked, it should display the delete popup and if \"delete\" button is clicked, then it should\n" +
            "delete the specific group details.", priority = 137)
    public void VerifyGroupNameDeleteConfirmOnReport() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = reportsPage.verifyGroupNameConfirmOnReport();
        if(flag){
            flag = reportsPage.verifyCreatedGroupDelete();
            if(flag) {
                flag = reportsPage.deleteCreatedGroup();
                if (flag) {
                    flag = reportsPage.deleteCreatedStartEndWord();
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies group name delete cancel button
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in reports page, in delete popup if cancel is clicked, it should not delete the specific group details.", priority = 138)
    public void VerifyGroupNameDeleteCancelOnReport() throws InterruptedException {
        flag = reportsPage.verifyGroupNameDeleteCancelOnReport();
        assertEquals(true, flag);
    }

    /**
     * This method verifies group name delete close popup
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in reports page, if close \"x\" icon is clicked on delete popup, then it should not delete the specific group details.", priority = 139)
    public void VerifyGroupNameDeleteClosePopup() throws InterruptedException {
        flag = reportsPage.verifyGroupNameDeleteClosePopup();
        assertEquals(true, flag);
    }

    /**
     * This method verifies reports to dashboard page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in reports page, if dashboard link is clicked, then user should navigated to dashboard page.", priority = 140)
    public void VerifyReportsToDashboardNavigation() {
        flag = reportsPage.verifyReportsToDashboardNavigation();
        assertEquals(true, flag);
    }

    /**
     * This method verifies search box in reports page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in reports page, in search box if specific group name is entered, then it should display that specific group details only.", priority = 141)
    public void VerifySearchBoxOnReportsPage() throws InterruptedException {
        flag = reportsPage.verifySearchBoxOnReportsPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies total rows displayed
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in reports page total number of rows of groups are displayed correctly on \"Showing entries\" details.", priority = 142)
    public void VerifyTotalRowsDisplayed() {
        flag = reportsPage.verifyTotalRowsDisplayed();
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination in report
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that pagination like \"previous\", \"next\" and page number is available for the group list datatable.", priority = 143)
    public void VerifyPaginationInReport() {
        flag = reportsPage.verifyPaginationInReport();
        assertEquals(true, flag);
    }

    /**
     * This method verifies group name update on report
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, if new group is created and mind mapping words are entered in front end page, the correct group name should be \n" +
            "updated on admin end reports page.", priority = 144)
    public void VerifyGroupNameUpdateOnReport() throws InterruptedException {
        flag = reportsPage.verifyGroupNameConfirmOnReport();
        if(flag){
            flag = reportsPage.verifyCreatedGroupDelete();
            if(flag) {
                flag = reportsPage.deleteCreatedGroup();
                if (flag) {
                    flag = reportsPage.deleteCreatedStartEndWord();
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created group start and end word on report
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, if new group is created and mind mapping words are entered in front end page, the correct start and end word should\n" +
            "be updated on admin end reports page.", priority = 145)
    public void VerifyGroupStartEndWordOnReport() throws InterruptedException {
        flag = reportsPage.verifyGroupNameConfirmOnReport();
        if(flag) {
            flag = reportsPage.verifyGroupStartEndWordOnReport();
            if (flag) {
                flag = reportsPage.verifyCreatedGroupDelete();
                if (flag) {
                    flag = reportsPage.deleteCreatedGroup();
                    if (flag) {
                        flag = reportsPage.deleteCreatedStartEndWord();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants count on reports page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in reports page, number of participants count should get increased once new user entered mind mapping words using\n" +
            "that specific group.", priority = 146)
    public void VerifyParticipantsCountOnReport() throws InterruptedException {
        flag = reportsPage.verifyGroupNameConfirmOnReport();
        if(flag) {
            flag = reportsPage.verifyParticipantsCountOnReport();
            if (flag) {
                flag = reportsPage.verifyCreatedGroupDelete();
                if (flag) {
                    flag = reportsPage.deleteCreatedGroup();
                    if (flag) {
                        flag = reportsPage.deleteCreatedStartEndWord();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies navigation to participants report page
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in reports page, if \"number of participants\" count is clicked, it should navigated to \"Participants Report\" page.", priority = 147)
    public void VerifyNavigationToParticipantsReportPage() {
        flag = reportsPage.verifyNavigationToParticipantReportPage();
        assertEquals(true, flag);
    }

    /**
     * This method verifies name and email from front end page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in participants report page, the correct participant's name and email should get displayed as mentioned in front end.", priority = 148)
    public void VerifyNameAndEmailInParticipantsPage() throws InterruptedException {
        flag = reportsPage.verifyNameAndEmailInParticipantsPage();
        if(flag){
            flag = reportsPage.navigateToReportsPage();
            if (flag) {
                flag = reportsPage.verifyCreatedGroupDelete();
                if (flag) {
                    flag = reportsPage.deleteCreatedGroup();
                    if (flag) {
                        flag = reportsPage.deleteCreatedStartEndWord();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies participant entered words
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in participants report page, users answers are correctly updated as mentioned in front end page.", priority = 149)
    public void VerifyParticipantEnteredWords() throws InterruptedException {
        flag = reportsPage.verifyParticipantEnteredWord();
        if(flag){
            flag = reportsPage.navigateToReportsPage();
            if (flag) {
                flag = reportsPage.verifyCreatedGroupDelete();
                if (flag) {
                    flag = reportsPage.deleteCreatedGroup();
                    if (flag) {
                        flag = reportsPage.deleteCreatedStartEndWord();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies created date on participants page
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in participant report page, correct date of creation should get updated.", priority = 150)
    public void VerifyCreatedDateOnParticipantsPage() throws InterruptedException {
        flag = reportsPage.verifyCreatedDateOnParticipantsPage();
        if(flag){
            flag = reportsPage.navigateToReportsPage();
            if (flag) {
                flag = reportsPage.verifyCreatedGroupDelete();
                if (flag) {
                    flag = reportsPage.deleteCreatedGroup();
                    if (flag) {
                        flag = reportsPage.deleteCreatedStartEndWord();
                    }
                }
            }
        }
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants to report page navigation
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in participant report page, if \"Reports\" link is clicked, then it should navigate back to reports page.", priority = 151)
    public void VerifyParticipantsToReportPageNav() {
        flag = reportsPage.verifyParticipantsToReportPageNav();
        assertEquals(true, flag);
    }

    /**
     * This method verifies participants search box
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @Test(description = "Verify that, in participants report page, if participant's name is entered in search box, then it should display those details alone.", priority = 152)
    public void VerifyParticipantsSearchBox() throws InterruptedException {
        flag = reportsPage.verifyParticipantsSearchBox();
        assertEquals(true, flag);
    }

    /**
     * This method verifies pagination button
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in participants report page, by default in pagination, previous button should be disabled and if there are more than 1 \n" +
            "page available, then next button should be enabled.", priority = 153)
    public void VerifyPaginationPreviousNextBtn() {
        flag = reportsPage.verifyPaginationPreviousNextBtn();
        assertEquals(true, flag);
    }

    /**
     * This method verifies number of listing items shown
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in participants report page, number of entries showing properly as number of rows in the table.", priority = 154)
    public void VerifyNumberOfListingItemsShown() {
        flag = reportsPage.verifyNumberOfListingItemsShown();
        assertEquals(true, flag);
    }

    /**
     * This method verifies table drop down values
     *
     * @author Gokul - GWL
     */
    @Test(description = "Verify that, in participants report page, in entries drop down there should be the options of 10, 20, 50, 100 and it should show \n" +
            "regarding rows on the table.", priority = 155)
    public void VerifyTableDropDownValues() throws Exception {
        flag = reportsPage.verifyTableDropDownValues();
        assertEquals(true, flag);
    }

    /**
     * This method verifies logout
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @AfterMethod
    public void Logout() throws InterruptedException {
        Thread.sleep(2000);
        commonFunctions.verifyLogout();
        commonFunctions.closeAllChildWindows();
        commonFunctions.clearLocalStorage();
        endTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
        commonFunctions.clearCookies();
    }

    /**
     * This method verifies tear down
     *
     * @author Gokul - GWL
     * @throws InterruptedException
     */
    @AfterTest
    public void TearDown() throws InterruptedException {
        System.out.println("Tear down");
        webDB.tearDown();
    }

//    @AfterSuite(alwaysRun = true)
//    public void SendMail() throws Exception {
//		Mailer mailer = new Mailer();
//		mailer = new Mailer();
//		mailer.execute("Mind Mapping");
//    }

}
