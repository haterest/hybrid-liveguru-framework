package pageObjects.FrontEnd;

import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FEAccountDashboardPageUI;

public class FEAccountDashboardPageObject extends FEMyAccountSideBarPageObject {
    public FEAccountDashboardPageObject(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public String getSuccessfulMessage() {
        waitForElementVisible(FEAccountDashboardPageUI.SUCCESSFUL_MESSAGE);
        return getElementText(FEAccountDashboardPageUI.SUCCESSFUL_MESSAGE);
    }

    public Object getPageDashboardTitle() {
        waitForElementVisible(FEAccountDashboardPageUI.ACCOUNT_DASHBOARD_TITLE);
        return getElementText(FEAccountDashboardPageUI.ACCOUNT_DASHBOARD_TITLE);
    }

    public Object getAccountNameFromWelcomeMessage() {
        waitForElementVisible(FEAccountDashboardPageUI.ACCOUNT_NAME_DASHBOARD_MESSAGE);
        return getElementText(FEAccountDashboardPageUI.ACCOUNT_NAME_DASHBOARD_MESSAGE);
    }
}
