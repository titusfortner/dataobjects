package common;

import elements.Element;
import elements.TextElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Browser {
    private final WebDriver driver;

    public Browser(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public Element element(By locator) {
        return new Element(locator, driver);
    }

    public TextElement textField(By locator) {
        return new TextElement(locator, driver);
    }

    public WebDriver.Navigation navigate() {
        return driver.navigate();
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    public WebDriver.Options cookies() {
        return driver.manage();
    }

    public void stop() {
        driver.quit();
    }
}
