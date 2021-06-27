package commons;

import com.google.common.base.Function;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ExplicitWait extends GenericHelper {

    private static Logger log = LogManager.getLogger(ExplicitWait.class);
    private WebDriver driver;
    private JavaScriptHelper js;

    public ExplicitWait(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.js = new JavaScriptHelper(driver);
    }

    private WebDriverWait getWait() {
        return getWait(Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    private WebDriverWait getWait(int timeOutInSeconds, int pollingEveryInMiliSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds, pollingEveryInMiliSec);
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(ElementNotVisibleException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.ignoring(NoSuchFrameException.class);
        return wait;
    }

    public void setImplicitWait(long timeout, TimeUnit unit) {
        log.info("Implicit timeout is set to: " + timeout);
        driver.manage().timeouts().implicitlyWait(timeout, unit);
    }

    public void setImplicitWait(long timeout) {
        setImplicitWait(timeout, TimeUnit.SECONDS);
    }

    public WebElement forElementToBePresent(By locator) {
        log.info("Waiting for element to be present: " + locator);
        return waitFor(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public WebElement forElementToBeVisible(By locator) {
        return forElementToBeVisible(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeVisible(By locator, int timeoutInSeconds) {
        return forElementToBeVisible(locator, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeVisible(WebElement webElement) {
        return forElementToBeVisible(webElement, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeVisible(WebElement webElement, int timeoutInSeconds) {
        return forElementToBeVisible(webElement, timeoutInSeconds, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeVisible(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for locator to be visible: " + locator);
        return waitFor(ExpectedConditions.visibilityOfElementLocated(locator), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public WebElement forElementToBeVisible(WebElement webElement, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for element to be visible: " + webElement);
        return waitFor(ExpectedConditions.visibilityOf(webElement), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public boolean forTextToBePresentInElementValue(WebElement element, String searchedText) {
        log.info("Waiting for text " + searchedText + "to be present in element: " + element);
        return waitFor(ExpectedConditions.textToBePresentInElementValue(element, searchedText));
    }

    public WebElement forElementToBeClickable(By element) {
        return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeClickable(By element, int timeoutInSeconds) {
        return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeClickable(WebElement element) {
        return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeClickable(WebElement element, int timeoutInSeconds) {
        return forElementToBeClickable(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public WebElement forElementToBeClickable(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for element to be clickable: " + element);
        return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public WebElement forElementToBeClickable(By element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for element to be clickable: " + element);
        forPage();
        return waitFor(ExpectedConditions.elementToBeClickable(element), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public boolean forInvisibilityOfElement(WebElement element) {
        return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public boolean forInvisibilityOfElement(By locator) {
        return forInvisibilityOfElement(locator, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds) {
        return forInvisibilityOfElement(element, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    public boolean forInvisibilityOfElement(WebElement element, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for invisibility of element: " + element);
        return waitFor(ExpectedConditions.invisibilityOf(element), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public boolean forInvisibilityOfElement(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info("Waiting for invisibility of element: " + locator);
        return waitFor(ExpectedConditions.invisibilityOfElementLocated(locator), timeOutInSeconds, pollingEveryInMiliSec);
    }

    public boolean forPage() {
        return forPage(Timeouts.PAGE);
    }

    public boolean forPage(int timeoutInSeconds) {
        log.debug("Waiting for the document to load");
        WebDriverWait wait = getWait(timeoutInSeconds, Timeouts.POLLING_INTERVAL);
        return wait.until(jQueryLoad()) && wait.until(documentLoad());
    }

    public boolean isElementExist(By locator, int timeOutInSeconds) {
        WebDriverWait wait = getWait(timeOutInSeconds, Timeouts.POLLING_INTERVAL);
        return wait.until(elementIsPresentInDOM(locator));
    }

    public void forIframe(By locator, int timeOutInSeconds, int pollingEveryInMiliSec) {
        log.info(locator);
        waitFor(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator), timeOutInSeconds, pollingEveryInMiliSec);
        driver.switchTo().defaultContent();
    }

    public void until(Function expectedCondition) {
        getWait().until(expectedCondition);
    }

    private <V> V waitFor(Function expectedCondition) {
        return waitFor(expectedCondition, Timeouts.EXPLICIT, Timeouts.POLLING_INTERVAL);
    }

    private <V> V waitFor(Function expectedCondition, int timeOutInSeconds, int pollingEveryInMiliSec) {
        setImplicitWait(0);
        WebDriverWait wait = getWait(timeOutInSeconds, pollingEveryInMiliSec);
        V result = (V) wait.until(expectedCondition);
        setImplicitWait(Timeouts.IMPLICIT);
        return result;
    }

    private Function<WebDriver, Boolean> textExistInElement(WebElement element, String searchedText) {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String textFromElement = element.getText();
                if (textFromElement.contains(searchedText)) {
                    return true;
                }
                return false;
            }
        };
    }

    private Function<WebDriver, Boolean> elementIsPresentInDOM(final By locator) {
        return new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElements(locator).size() > 0;
            }
        };
    }

    private Function<WebDriver, Boolean> jQueryLoad() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) js.executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };
    }

    private Function<WebDriver, Boolean> documentLoad() {
        return new Function<WebDriver, Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
    }
}
