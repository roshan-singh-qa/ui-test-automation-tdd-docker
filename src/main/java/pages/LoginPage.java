package pages;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "email")
    public WebElement username;

    @FindBy(name = "password")
    public WebElement password;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "signInSubmit")
    public WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
