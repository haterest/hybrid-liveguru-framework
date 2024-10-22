package pageObjects.FrontEnd;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FELoginPageUI;

public class FELoginPageObject extends BasePage {
    public FELoginPageObject (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void inputToEmailAddressTextbox(String emailAdress) {
        waitForElementVisible(FELoginPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(FELoginPageUI.EMAIL_ADDRESS_TEXTBOX, emailAdress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(FELoginPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(FELoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public FEAccountDashboardPageObject clickToLoginButton() {
        waitForElementClickable(FELoginPageUI.LOGIN_BUTTON);
        clickToElement(FELoginPageUI.LOGIN_BUTTON);
        return PageGeneratorManager.getFEAcountDashboardPage(driver);
    }
}
