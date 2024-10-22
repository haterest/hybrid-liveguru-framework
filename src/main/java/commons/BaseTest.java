package commons;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected final Log log;
    protected BaseTest() {
        log = LogFactory.getLog(getClass());
    }
    public WebDriver getDriverInstance() {
        return driver.get();
    }

    public WebDriver getBrowserDriver(String envName, String browserName, String osName, String ipAddress, String portNumber){
        switch (envName) {
            case "local":
                driver.set(new LocalFactory(browserName).createDriver());
                break;
            case "grid":
                driver.set(new GridFactory(browserName, osName, ipAddress, portNumber).createDriver());
                break;
        }
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalConstant.getGlobalConstants().getLongTimeout()));
        driver.get().manage().window().maximize();
        driver.get().get(GlobalConstant.getGlobalConstants().getLiveGuruFEUrl());
        return driver.get();
    }

    protected int getRandomNumber() {
        Random rand = new Random();
        return rand.nextInt(9999);
    }

    protected boolean verifyTrue(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertTrue(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyFalse(boolean condition) {
        boolean pass = true;
        try {
            Assert.assertFalse(condition);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            log.info(" -------------------------- FAILED -------------------------- ");
            pass = false;
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }

    protected boolean verifyEquals(Object actual, Object expected) {
        boolean pass = true;
        try {
            Assert.assertEquals(actual, expected);
            log.info(" -------------------------- PASSED -------------------------- ");
        } catch (Throwable e) {
            pass = false;
            log.info(" -------------------------- FAILED -------------------------- ");
            VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
            Reporter.getCurrentTestResult().setThrowable(e);
        }
        return pass;
    }
    protected void closeBrowserDriver() {
        String cmd = null;
        try {
            String osName = GlobalConstant.getGlobalConstants().getOsName();
            log.info("OS name = " + osName);

            String driverInstanceName = driver.get().toString().toLowerCase();
            log.info("Driver instance name = " + driverInstanceName);

            String browserDriverName = null;

            if (driverInstanceName.contains("chrome")) {
                browserDriverName = "chromedriver";
            }  else if (driverInstanceName.contains("firefox")) {
                browserDriverName = "geckodriver";
            } else {
                browserDriverName = "safaridriver";
            }

            if (osName.contains("Windows")) {
                cmd = "taskkill /F /FI \"IMAGENAME eq " + browserDriverName + "*\"";
            } else {
                cmd = "pkill " + browserDriverName;
            }

            if (driver != null) {
                driver.get().manage().deleteAllCookies();
                driver.get().quit();
                driver.remove();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        } finally {
            try {
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
