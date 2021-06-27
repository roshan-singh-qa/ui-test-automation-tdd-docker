package commons;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavaScriptHelper {

    private final WebDriver driver;

    public static final Logger log = LogManager.getLogger(JavaScriptHelper.class);

    public JavaScriptHelper(WebDriver driver) {
        this.driver = driver;
    }

    public Object executeScript(String script) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script);
    }

    public Object executeScript(String script, Object... args) {
        JavascriptExecutor exe = (JavascriptExecutor) driver;
        log.info(script);
        return exe.executeScript(script, args);
    }

    public void scrollToElement(WebElement element) {
        executeScript("window.scrollTo(arguments[0],arguments[1])",
                element.getLocation().x, element.getLocation().y);
        log.info(element);
    }

    public void scrollToElement(By locator) {
        scrollToElement(driver.findElement(locator));
        log.info(locator);
    }

    public void scrollToElementAndClick(WebElement element) {
        scrollToElement(element);
        element.click();
        log.info(element);
    }

    public void scrollIntoView(WebElement element) {
        executeScript("arguments[0].scrollIntoView(true)", element);
        log.info(element);
    }

    public void scrollIntoViewAndClick(WebElement element) {
        scrollIntoView(element);
        element.click();
        log.info(element);
    }

    public void clickElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()", element);
    }

    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }
}
