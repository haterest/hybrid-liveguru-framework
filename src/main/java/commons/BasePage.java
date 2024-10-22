package commons;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePage {
    public BasePage (WebDriver driver){
        this.driver = driver;
    }

    public void openPageURL(String pageURL) {
        driver.get(pageURL);
    }

    protected void quitPageURL() {
        driver.quit();
    }

    protected String getPageTitle() {
        return driver.getTitle();
    }

    protected String getPageURL( ) {
        return driver.getCurrentUrl();
    }

    protected void backToPage( ) {
        driver.navigate().back();
    }

    protected void forwardToPage( ) {
        driver.navigate().forward();
    }

    public void refreshCurrentPage( ) {
        driver.navigate().refresh();
    }

    public Set<Cookie> getAllCookie() {
        return driver.manage().getCookies();
    }

    public void setCookies(Set<Cookie> cookies) {
        for (Cookie cookie : cookies) {
            driver.manage().addCookie(cookie);
        }
        sleepInSecond(3);
    }

    protected Alert waitForAlertPresence() {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        return explicitWait.until(ExpectedConditions.alertIsPresent());
    }

    protected void acceptAlert() {
        waitForAlertPresence().accept();
    }

    protected void cancelAlert() {
        waitForAlertPresence().dismiss();
    }

    public void sleepInSecond(long timeInSecond) {
        try {
            Thread.sleep(timeInSecond * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private By getByLocator(String locatorType) {
        By by;
        if (locatorType.startsWith("id=") || locatorType.startsWith("Id=") || locatorType.startsWith("ID=")) {
            by = By.id(locatorType.substring(3));
        } else if (locatorType.startsWith("class=") || locatorType.startsWith("Class=")
                || locatorType.startsWith("CLASS=")) {
            by = By.className(locatorType.substring(6));
        } else if (locatorType.startsWith("name=") || locatorType.startsWith("Name=")
                || locatorType.startsWith("NAME=")) {
            by = By.name(locatorType.substring(5));
        } else if (locatorType.startsWith("css=") || locatorType.startsWith("Css=") || locatorType.startsWith("CSS=")) {
            by = By.cssSelector(locatorType.substring(4));
        } else if (locatorType.startsWith("xpath=") || locatorType.startsWith("Xpath=")
                || locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=")) {
            by = By.xpath(locatorType.substring(6));
        } else {
            throw new RuntimeException("Locator type is not supported");
        }
        return by;
    }

    private String getDynamicXpath(String locator, String... values) {
        if (locator.startsWith("xpath=") || locator.startsWith("Xpath=") || locator.startsWith("XPATH=")
                || locator.startsWith("XPath=")) {
            locator = String.format(locator, (Object[]) values);
        }
        return locator;
    }

    private WebElement getWebElement(String locatorType) {
        return driver.findElement(getByLocator(locatorType));
    }

    private List<WebElement> getListWebElement(String locatorType) {
        return driver.findElements(getByLocator(locatorType));
    }

    protected void clickToElement(String locator) {
        getWebElement(locator).click();
    }

    protected void clickToElement(String locator, String... dynamicValues) {
        getWebElement(getDynamicXpath(locator, dynamicValues)).click();
    }

    protected void sendKeyToElement(String locator, String textValue) {
        WebElement element = getWebElement(locator);
        element.clear();
        element.sendKeys(textValue);
    }

    protected void sendKeyToElement(String locator, String textValue, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        element.clear();
        element.sendKeys(textValue);
    }

    protected void selectItemInDefaultDropDown(String locator, String textItem) {
        Select select = new Select(getWebElement(locator));
        select.selectByVisibleText(textItem);
    }

    protected void selectItemInDefaultDropDown(String locator, String textItem, String... dynamicValues) {
        Select select = new Select(getWebElement(getDynamicXpath(locator, dynamicValues)));
        select.selectByVisibleText(textItem);
    }

    protected String getFirstSelectedItemDefaultDropDown(String locator) {
        Select select = new Select(getWebElement(locator));
        return select.getFirstSelectedOption().getText();
    }

    protected void selectItemInCustomDropdown(String parentLocator, String childItemLocator, String expectedItem) {
        getWebElement(parentLocator).click();
        sleepInSecond(1);
        WebDriverWait explicitWait;
        JavascriptExecutor jsExecutor;
        explicitWait = new WebDriverWait(driver, longTimeout);
        List<WebElement> allItems = explicitWait
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemLocator)));
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(expectedItem)) {
                jsExecutor = (JavascriptExecutor) driver;
                jsExecutor.executeScript("arguments[0].scrollIntoView(false);", item);
                sleepInSecond(1);
                item.click();
                break;
            }
        }
    }

    protected String getElementAttribute(String locator, String attributeName) {
        return getWebElement(locator).getAttribute(attributeName);
    }

    protected String getElementAttribute(String locator, String attributeName, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getAttribute(attributeName);
    }

    protected String getElementText(String locator) {
        return getWebElement(locator).getText();
    }

    protected String getElementText(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).getText();
    }

    protected int getElementsSize(String locator) {
        return getListWebElement(locator).size();
    }

    protected int getElementsSize(String locator, String... dynamicValues) {
        return getListWebElement(getDynamicXpath(locator, dynamicValues)).size();
    }

    protected void clickToDefautCheckboxOrRadio(String locator) {
        WebElement element = getWebElement(locator);
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void clickToDefautCheckboxOrRadio(String locator, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        if (!element.isSelected()) {
            element.click();
        }
    }

    protected void unClickToDefaultCheckbox(String locator) {
        WebElement element = getWebElement(locator);
        if (element.isSelected()) {
            element.click();
        }
    }

    protected void unClickToDefaultCheckbox(String locator, String... dynamicValues) {
        WebElement element = getWebElement(getDynamicXpath(locator, dynamicValues));
        if (element.isSelected()) {
            element.click();
        }
    }

    protected boolean isElementDisplayed(String locator) {
        return getWebElement(locator).isDisplayed();
    }

    protected boolean isElementDisplayed(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locator) {
        overrideGlobalTimeout(shortTimeout);
        List<WebElement> elements = getListWebElement(locator);
        overrideGlobalTimeout(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return !elements.get(0).isDisplayed();
    }

    protected boolean isElementUndisplayed(String locator, String... dynamicValues) {
        overrideGlobalTimeout(shortTimeout);
        List<WebElement> elements = getListWebElement(getDynamicXpath(locator, dynamicValues));
        overrideGlobalTimeout(longTimeout);
        if (elements.size() == 0) {
            return true;
        } else return !elements.get(0).isDisplayed();
    }

    /*
     * Wait for element undisplayed in DOM or not in DOM and override implicit Timeout
     */
    protected void waitForElementUndisplayed(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
        overrideGlobalTimeout(shortTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
        overrideGlobalTimeout(longTimeout);
    }

    private void overrideGlobalTimeout(Duration timeOut) {
        driver.manage().timeouts().implicitlyWait(timeOut);
    }

    protected boolean isElementSelected(String locator) {
        return getWebElement(locator).isSelected();
    }

    protected boolean isElementSelected(String locator, String... dynamicValues) {
        return getWebElement(getDynamicXpath(locator, dynamicValues)).isSelected();
    }

    protected void hoverMouseToElement(String locator) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(locator)).perform();
    }

    protected void hoverMouseToElement(String locator, String... dynamicValues) {
        Actions action = new Actions(driver);
        action.moveToElement(getWebElement(getDynamicXpath(locator, dynamicValues))).perform();
    }

    protected void scrollToTopPage() {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
    }

    protected void clickToElementByJS(String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(  locator));
    }

    protected void clickToElementByJS(String locator, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();",
                getWebElement( getDynamicXpath(locator, dynamicValues)));
    }

    protected void scrollToElement(String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                getWebElement( locator));
    }

    protected void scrollToElement(String locator, String... dynamicValues) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
                getWebElement( getDynamicXpath(locator, dynamicValues)));
    }

    protected void waitForElementVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementVisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(
                ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForAllElementsVisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locator)));
    }

    protected void waitForAllElementsVisible(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions
                .visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected void waitForElementInvisible(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locator)));
    }

    protected void waitForElementClickable(String locator) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locator)));
    }

    protected void waitForElementClickable(String locator, String... dynamicValues) {
        WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
        explicitWait
                .until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locator, dynamicValues))));
    }

    protected boolean isKeywordDisplayInSearchResult(String locator, String keywords) {
        List<WebElement> listElement = getListWebElement(locator);
        for (WebElement eachElement : listElement) {
            if (!eachElement.getText().toLowerCase().contains(keywords.toLowerCase())){
                return false;
            }
        }
        return true;
    }

    protected WebDriver driver;
    protected Duration longTimeout = Duration.ofSeconds(GlobalConstant.getGlobalConstants().getLongTimeout());
    protected Duration shortTimeout = Duration.ofSeconds(GlobalConstant.getGlobalConstants().getShortTimeout());
}
