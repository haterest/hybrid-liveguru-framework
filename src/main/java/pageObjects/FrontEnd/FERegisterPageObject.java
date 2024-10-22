package pageObjects.FrontEnd;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FERegisterPageUI;

public class FERegisterPageObject extends BasePage {
    public FERegisterPageObject (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void inputToFirstNameTextbox(String firstName) {
        waitForElementVisible(FERegisterPageUI.FIRST_NAME_TEXTBOX);
        sendKeyToElement(FERegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void inputToLastNameTextbox(String lastName) {
        waitForElementVisible(FERegisterPageUI.LAST_NAME_TEXTBOX);
        sendKeyToElement(FERegisterPageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public void inputToEmailAddressTextbox(String emailAdress) {
        waitForElementVisible(FERegisterPageUI.EMAIL_ADDRESS_TEXTBOX);
        sendKeyToElement(FERegisterPageUI.EMAIL_ADDRESS_TEXTBOX, emailAdress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(FERegisterPageUI.PASSWORD_TEXTBOX);
        sendKeyToElement(FERegisterPageUI.PASSWORD_TEXTBOX, password);
    }

    public void inputToConfirmPasswordTextbox(String password) {
        waitForElementVisible(FERegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
        sendKeyToElement(FERegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
    }

    public FEAccountDashboardPageObject clickToRegisterButton() {
        waitForElementClickable(FERegisterPageUI.REGISTER_BUTTON);
        clickToElement(FERegisterPageUI.REGISTER_BUTTON);
        return PageGeneratorManager.getFEAcountDashboardPage(driver);
    }
}
