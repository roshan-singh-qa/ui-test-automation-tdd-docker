package commons;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    protected WebDriver driver;

    @BeforeEach
    public void setUp() {
        this.driver = createDriver();
        setUpWebDriver(driver);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }

    private WebDriver createDriver() {
        var hubUrl = System.getProperty("hubUrl");

        // If the hubUrl is specified use the remote driver, else try to use the local chrome driver.
        if (hubUrl != null) {
            var browserName = System.getProperty("browserName");
            if (browserName == null) {
                throw new RuntimeException("You must specify a browserName");
            }
            try {
                return new RemoteWebDriver(new URL(hubUrl), getOptions(browserName));
            } catch (MalformedURLException e) {
                throw new RuntimeException("The supplied hubUrl: " + hubUrl + " is not a valid url.");
            }
        } else {

            // If we're not using a hub, then attempt to instantiate a local chrome driver.
            var driverPath = System.getProperty("webdriver.chrome.driver");
            if (driverPath == null) {
                throw new RuntimeException("You must specify either a hubUrl or a webdriver.chrome.driver path.");
            }
            return new ChromeDriver((ChromeOptions) getOptions("chrome"));
        }
    }

    private MutableCapabilities getOptions(String browserName) {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName(browserName);

        if (browserName.equals("firefox")) {
            var options = new FirefoxOptions();
            return options.merge(cap);
        }

        if (browserName.equals("internetExplorer")) {
            var options = new InternetExplorerOptions();
            return options.merge(cap);
        }

        if (browserName.equals("chrome")) {
            var options = new ChromeOptions();
            options.addArguments("disable-gpu");
            options.addArguments("--disable-print-preview");
//            options.addArguments("headless");
//            options.addArguments("window-size=1200x600");
            return options.merge(cap);
        }

        throw new RuntimeException(browserName + " is an invalid browserName.");
    }

    public String workingDirectory() {
        var configuredWorkingDirectory = System.getProperty("workingDirectory");
        if (configuredWorkingDirectory != null) {
            return configuredWorkingDirectory;
        }
        return System.getProperty("user.dir");
    }

    private static void setUpWebDriver(WebDriver driver) {
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout(Timeouts.PAGE, TimeUnit.SECONDS);
    }
}
