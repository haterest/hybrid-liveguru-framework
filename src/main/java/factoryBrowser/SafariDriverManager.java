package factoryBrowser;

import commons.GlobalConstant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import static org.apache.commons.lang3.SystemUtils.IS_OS_MAC;

public class SafariDriverManager implements BrowserFactory{
    @Override
    public WebDriver getBrowserDriver() {
        if (!IS_OS_MAC) {
            throw new BrowserNotSupportedException("Safari is not supported on: " + GlobalConstant.getGlobalConstants().getOsName());
        }
        SafariOptions options = new SafariOptions();
        return new SafariDriver(options);
    }
}