package pageObjects.FrontEnd;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
    public static FEHomePageObject getFEHomePage (WebDriver driver){
        return new FEHomePageObject(driver);
    }

    public static FERegisterPageObject getFERegisterPage (WebDriver driver){
        return new FERegisterPageObject(driver);
    }

    public static FEAccountDashboardPageObject getFEAcountDashboardPage(WebDriver driver){
        return new FEAccountDashboardPageObject(driver);
    }

    public static FEAccountInforPageObject getFEAccountInforPage (WebDriver driver){
        return new FEAccountInforPageObject(driver);
    }

    public static FELoginPageObject getFELoginPage (WebDriver driver){
        return new FELoginPageObject(driver);
    }
}
