import commons.TestBase;
import org.junit.jupiter.api.*;
import pages.HomePage;
import pages.LoginPage;

import static commons.Configuration.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest extends TestBase {

    @BeforeEach
    public void openPage() {
        driver.get(url.asString());
    }

    @Test
    @Tag("smoke")
    public void verifyLoginWithValidCred() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);

        homePage.signInButton.click();
        loginPage.username.sendKeys(username.asString());
        loginPage.continueButton.click();
        loginPage.password.sendKeys(password.asString());
        loginPage.loginButton.click();
    }
}