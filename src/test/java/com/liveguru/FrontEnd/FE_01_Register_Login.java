package com.liveguru.FrontEnd;

import commons.BaseTest;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import pageObjects.FrontEnd.*;
import utilities.DataHelper;

public class FE_01_Register_Login extends BaseTest {
    @Parameters({"envName", "browserName", "osName", "ipAddress", "portNumber"})
    @BeforeClass
    public void beforeClass(@Optional("local") String envName, @Optional("chrome")
    String browserName, @Optional("10") String osName, @Optional("142") String ipAddress, @Optional("9999") String portNumber) {
        driver = getBrowserDriver(envName, browserName, osName, ipAddress, portNumber);
        dataFaker = DataHelper.getData();
        firstName = dataFaker.getFirstName();
        lastName = dataFaker.getLastName();
        emailAdress = dataFaker.getEmail();
        password = dataFaker.getPassword();

        feHomePage = PageGeneratorManager.getFEHomePage(driver);
    }

    @Test
    public void Register_Login_01_Register_successful() {
        feHomePage.clickToAccountMenu();
        fERegisterPage = feHomePage.clickToRegisterMenuLink();
        fERegisterPage.inputToFirstNameTextbox(firstName);
        fERegisterPage.inputToLastNameTextbox(lastName);
        fERegisterPage.inputToEmailAddressTextbox(emailAdress);
        fERegisterPage.inputToPasswordTextbox(password);
        fERegisterPage.inputToConfirmPasswordTextbox(password);
        feAccountDashboardPage = fERegisterPage.clickToRegisterButton();
        verifyEquals(feAccountDashboardPage.getSuccessfulMessage(), "Thank you for registering with Main Website Store.");
    }

    @Test
    public void Register_Login_02_Verified_Register_successful(){
        feHomePage.clickToAccountMenu();
        feAccountDashboardPage =  feHomePage.clickToMyAccountMenuLink();
        feAccountInforPage = feAccountDashboardPage.clickToAccountInforPage();
        verifyEquals(feAccountInforPage.getTextFromFirstNameTextbox(), firstName);
        verifyEquals(feAccountInforPage.getTextFromLastNameTextbox(), lastName);
        verifyEquals(feAccountInforPage.getTextFromEmailAddressTextbox(), emailAdress);
    }

    @Test
    public void Register_Login_03_Login_successful(){
        feHomePage.clickToAccountMenu();
        feHomePage.clickToLogOutMenuLink();
        verifyEquals(feHomePage.getLogOutSuccessfulMessage(), "You are now logged out");
        feHomePage.clickToAccountMenu();
        feLoginPage = feHomePage.clickToLogInMenuLink();
        feLoginPage.inputToEmailAddressTextbox(emailAdress);
        feLoginPage.inputToPasswordTextbox(password);
        feAccountDashboardPage = feLoginPage.clickToLoginButton();
        verifyEquals(feAccountDashboardPage.getPageDashboardTitle(), "My Dashboard");
        verifyEquals(feAccountDashboardPage.getAccountNameFromWelcomeMessage(), "Hello, " + firstName + " " + lastName + "!");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        closeBrowserDriver();
    }

    private WebDriver driver;
    private DataHelper dataFaker;
    private String firstName, lastName, emailAdress, password;
    private FERegisterPageObject fERegisterPage;
    private FEHomePageObject feHomePage;
    private FEAccountDashboardPageObject feAccountDashboardPage;
    private FEAccountInforPageObject feAccountInforPage;
    private FELoginPageObject feLoginPage;
}
