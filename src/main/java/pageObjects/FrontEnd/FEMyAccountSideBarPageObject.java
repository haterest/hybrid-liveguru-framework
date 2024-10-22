package pageObjects.FrontEnd;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FEMyAccountSideBarPageUI;

public class FEMyAccountSideBarPageObject extends BasePage {
    public FEMyAccountSideBarPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public FEAccountInforPageObject clickToAccountInforPage() {
        waitForElementClickable(FEMyAccountSideBarPageUI.ACCOUNT_INFORMATION_LINK);
        clickToElement(FEMyAccountSideBarPageUI.ACCOUNT_INFORMATION_LINK);
        return PageGeneratorManager.getFEAccountInforPage(driver);
    }

}
