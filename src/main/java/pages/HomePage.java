package pages;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = "a[data-csa-c-content-id=nav_ya_signin]")
    public WebElement signInButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
