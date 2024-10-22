package pageObjects.FrontEnd;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FEHomePageUI;

public class FEHomePageObject extends BasePage {

    public FEHomePageObject (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void clickToAccountMenu() {
        waitForElementClickable(FEHomePageUI.ACCOUNT_MENU_HEADER);
        clickToElement(FEHomePageUI.ACCOUNT_MENU_HEADER);
        sleepInSecond(2);
    }

    public FERegisterPageObject clickToRegisterMenuLink() {
        waitForElementClickable(FEHomePageUI.REGISTER_MENU_LINK);
        clickToElement(FEHomePageUI.REGISTER_MENU_LINK);
        return PageGeneratorManager.getFERegisterPage(driver);
    }

    public FEAccountDashboardPageObject clickToMyAccountMenuLink() {
        waitForElementClickable(FEHomePageUI.MY_ACCOUNT_MENU_LINK);
        clickToElement(FEHomePageUI.MY_ACCOUNT_MENU_LINK);
        return PageGeneratorManager.getFEAcountDashboardPage(driver);
    }

    public void clickToLogOutMenuLink() {
        waitForElementClickable(FEHomePageUI.LOG_OUT_MENU_LINK);
        clickToElement(FEHomePageUI.LOG_OUT_MENU_LINK);
    }

    public String getLogOutSuccessfulMessage() {
        waitForElementVisible(FEHomePageUI.LOG_OUT_SUCCESSFUL_MESSAGE_TITLE);
        return getElementText(FEHomePageUI.LOG_OUT_SUCCESSFUL_MESSAGE_TITLE);
    }

    public FELoginPageObject clickToLogInMenuLink() {
        waitForElementClickable(FEHomePageUI.LOG_IN_MENU_LINK);
        clickToElement(FEHomePageUI.LOG_IN_MENU_LINK);
        return PageGeneratorManager.getFELoginPage(driver);
    }
}
