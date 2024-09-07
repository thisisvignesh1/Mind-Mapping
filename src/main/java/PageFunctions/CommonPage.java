package PageFunctions;

/**
 * @author Gokul - GWL
 * @company Galaxy Weblinks May 21, 2024
 */

import Locators.CommonLocators;
import Locators.DashboardPageLocators;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.JavascriptExecutor;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.Transferable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

public class CommonPage {
    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    boolean flag;
    private static String parentWindow;
    private static Set<String> windows;
    private static String childWindow;

    /**
     * This method clears local storage
     *
     * @author Gokul - GWL
     */
    public void clearLocalStorage() {
        JavascriptExecutor jse = (JavascriptExecutor) webDB.getDriver();
        jse.executeScript("window.localStorage.clear();");
        System.out.println("local storage cleared");
        webDB.getDriver().navigate().refresh();
    }

    /**
     * This method clears cookies
     *
     * @author Gokul - GWL
     */
    public void clearCookies(){
        webDB.getDriver().manage().deleteCookieNamed("cookie");
        webDB.getDriver().navigate().refresh();
    }

    /**
     * This method verifies logout
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyLogout() {
        flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
        if(flag){
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
            if(flag){
                flag = webDB.waitForElement(CommonLocators.SIGN_OUT, ElementType.Xpath);
                if(flag){
                    flag = webDB.clickAnElement(CommonLocators.SIGN_OUT, ElementType.Xpath);
                }
            }
        }
        return flag;
    }

    /**
     * This method returns today's date
     *
     * @author Gokul - GWL
     * @return string
     */
    public String getFormattedDate() {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return today.format(formatter);
    }

    /**
     * This method gets clipboard link
     *
     * @author Gokul - GWL
     * @return
     * @throws UnsupportedFlavorException
     * @throws IOException
     */
    public String getClipboardContents() throws UnsupportedFlavorException, IOException {
        String result = "";
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        // Check if the clipboard contains data of type string
        if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
            try {
                Transferable transferable = clipboard.getContents(null);
                if (transferable != null && transferable.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    result = (String) transferable.getTransferData(DataFlavor.stringFlavor);
                }
            } catch (Exception e) {
                System.err.println("Failed to get clipboard contents: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * This method closes all child windows
     *
     * @author Gokul - GWL
     */
    public void closeAllChildWindows() {
        parentWindow = webDB.getDriver().getWindowHandle();
        windows = webDB.getDriver().getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        while (iterator.hasNext()) {
            childWindow = iterator.next();
            if (!parentWindow.equals(childWindow)) {
                webDB.getDriver().switchTo().window(childWindow);
                webDB.getDriver().close();

            }
        }
        webDB.getDriver().switchTo().window(parentWindow);
    }

    /**
     * This method verifies admin icon options
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyAdminIconOptions() throws InterruptedException {
        flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Profile image is clicked");
                flag = webDB.waitForElement(CommonLocators.PROFILE_OPTION, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Profile option is visible");
                    flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                    if(flag){
                        flag = webDB.clickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                        Thread.sleep(2000);
                    }
                }
            }
        }
        return flag;
    }

    /**
     * This method verifies change password option
     *
     * @author Gokul - GWL
     * @return boolean flag
     */
    public boolean verifyChangePasswordOption() throws InterruptedException {
        flag = webDB.waitForElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
        if(flag) {
            flag = webDB.javaScriptClickAnElement(CommonLocators.PROFILE_IMAGE, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Profile image is clicked");
                flag = webDB.waitForElement(CommonLocators.CHANGE_PASSWORD_OPTION, ElementType.Xpath);
                if(flag){
                    log.logging("info", "Change password option is visible on profile logo options");
                    flag = webDB.waitForElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                    if(flag) {
                        flag = webDB.clickAnElement(DashboardPageLocators.DASHBOARD_PAGE_TAB, ElementType.Xpath);
                        Thread.sleep(2000);
                    }
                }
            }
        }
        return flag;
    }
}
