import common.Browser;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.BasePage;

public class BaseTest {
    Browser browser;

    @Before
    public void startSession() {
        WebDriverManager.chromedriver().setup();

        browser = new Browser(new ChromeDriver());

        BasePage.setBrowser(browser);
    }

    @After
    public void endSession() {
        browser.stop();
    }
}
