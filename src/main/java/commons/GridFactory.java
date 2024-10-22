package commons;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GridFactory {
    private WebDriver driver;
    private String browserName;
    private String osName;
    private String ipAddress;
    private String portNumber;
    public GridFactory(String browserName, String osName, String ipAddress, String portNumber) {
        this.browserName = browserName;
        this.osName = osName;
        this.ipAddress = ipAddress;
        this.portNumber = portNumber;
    }

    public WebDriver createDriver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        Capabilities capability;
        Platform platform;
        if (osName.contains("windows")) {
            platform = Platform.WINDOWS;
        } else {
            platform = Platform.MAC;
        }
        switch (browserName) {
            case "firefox":
                capability = new FirefoxOptions();
                desiredCapabilities.setCapability("browserName","Firefox");
                capability.merge(desiredCapabilities);
                break;
            case "chrome":
                MutableCapabilities multableCapabilities = new MutableCapabilities();
                capability = new ChromeOptions();
                multableCapabilities.setCapability(ChromeOptions.CAPABILITY, capability);
                multableCapabilities.setCapability("browserName", "Chrome");
                capability.merge(multableCapabilities);
                break;
            case "edge":
                capability = new EdgeOptions();
                desiredCapabilities.setCapability("browserName","Edge");
                capability.merge(desiredCapabilities);
                break;
            default:
                throw new RuntimeException("Browser is not valid!");
        }
        try {
            driver = new RemoteWebDriver(new URL(String.format("http://%s:%s/wd/hub", ipAddress, portNumber)), capability);
            // waiting for fixing
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
