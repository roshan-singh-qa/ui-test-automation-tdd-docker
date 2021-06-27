package commons;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {

    protected WebDriver driver;

    protected ExplicitWait wait;

    protected JavaScriptHelper js;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new ExplicitWait(driver);
        js = new JavaScriptHelper(driver);
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement webElement) {
        click(webElement, Timeouts.EXPLICIT);
    }

    public void click(WebElement webElement, int timeOutInSeconds) {
        wait.forElementToBeClickable(webElement, timeOutInSeconds, Timeouts.POLLING_INTERVAL);
        js.clickElement(webElement);
    }

    public void sendKeys(WebElement webElement, String value, int timeOutInSeconds) {
        click(webElement, timeOutInSeconds);
        webElement.clear();
        webElement.sendKeys(value);
    }

    public void sendKeys(WebElement webElement, String value) {
        sendKeys(webElement, value, Timeouts.EXPLICIT);
    }

    public void executeJavascript(String string) {
        js.executeScript(string);
    }

    public void executeJavascript(String string, WebElement element) {
        js.executeScript(string, element);
    }
}
