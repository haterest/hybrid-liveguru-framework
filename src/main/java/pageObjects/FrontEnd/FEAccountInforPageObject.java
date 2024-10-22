package pageObjects.FrontEnd;

import org.openqa.selenium.WebDriver;
import pageUIs.FrontEnd.FEAccountInforPageUI;

public class FEAccountInforPageObject extends FEMyAccountSideBarPageObject{
    public FEAccountInforPageObject (WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public String getTextFromFirstNameTextbox() {
        waitForElementVisible(FEAccountInforPageUI.FIRST_NAME_TEXTBOX);
        return getElementAttribute(FEAccountInforPageUI.FIRST_NAME_TEXTBOX, "value");
    }

    public String getTextFromLastNameTextbox() {
        waitForElementVisible(FEAccountInforPageUI.LAST_NAME_TEXTBOX);
        return getElementAttribute(FEAccountInforPageUI.LAST_NAME_TEXTBOX, "value");
    }

    public String getTextFromEmailAddressTextbox() {
        waitForElementVisible(FEAccountInforPageUI.EMAIL_ADDRESS_TEXTBOX);
        return getElementAttribute(FEAccountInforPageUI.EMAIL_ADDRESS_TEXTBOX, "value");
    }
}
