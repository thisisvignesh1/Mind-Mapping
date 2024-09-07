package PageFunctions;

import Locators.FrontEndConnectionWordsPageLocator;
import Locators.FrontEndLoginLocators;
import Locators.FrontEndResultsPageLocators;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.ReportLoger;
import utils.WebDriverBase;
import utils.WebDriverBase.ElementType;
import java.awt.*;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;

public class FrontEndResultsPage {
    static WebDriverBase webDB = new WebDriverBase();
    static ReportLoger log = new ReportLoger();
    static FrontEndConnectionWordsPage frontEndConnectionWordsPage = new FrontEndConnectionWordsPage();
    boolean flag;

    /**
     * This method verifies connection words on container
     *
     * @return boolean flag
     * @author Gokul - GWL
     */
    public boolean verifyConnectionWordsOnContainer() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = frontEndConnectionWordsPage.verifyResultsPageComponents();
        if (flag) {
            flag = webDB.waitForElement(FrontEndResultsPageLocators.DEMO_WORD_IN_CONTAINER, ElementType.Xpath);
            if (flag) {
                log.logging("info", "Found entered word on results page");
            } else {
                log.logging("fail", "Issue on verifying entered word");
                webDB.switchToChildWindow();
                flag = false;
            }
        }
        return flag;
    }

    /**
     * This method verifies duplicate word containers
     *
     * @return boolean flag
     * @author Gokul - GWL
     */
    public boolean verifyDuplicateWordContainers() throws IOException, InterruptedException, UnsupportedFlavorException, AWTException {
        flag = verifyConnectionWordsOnContainer();
        if (flag) {
            List<WebElement> elements = webDB.getDriver().findElements(By.xpath(FrontEndResultsPageLocators.DEMO_WORD_IN_CONTAINER));
            if (elements.size() == 1) {
                log.logging("info", "No duplicate word is entered on container");
            } else {
                log.logging("fail", "Duplicate words are entered");
                flag = false;
                webDB.switchToChildWindow();
            }
        }
        return flag;
    }
}
