import common.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.BasePage;

public class BaseTest {
    Browser browser;
    RemoteWebDriver driver;

    @Before
    public void startSession() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        browser = new Browser(driver);

        BasePage.setBrowser(browser);
    }

    @After
    public void endSession() {
        browser.stop();
    }
}
