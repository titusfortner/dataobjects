package elements;

import common.PageValidationException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Instant;
import java.util.function.Function;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Element {
    private WebDriver driver;
    protected By locator;
    protected WebElement element;
    protected WebDriverWait wait;
    protected static int DEFAULT_WAIT_TIME = 20;

    public Element(By locator, WebDriver driver) {
        this.locator = locator;
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_WAIT_TIME);
    }

    public String getText() {
        return locateElement().getText();
    }

    public String getAttribute(String attribute) {
        return locateElement().getAttribute(attribute);
    }

    public void click() {
        long expireTime = Instant.now().toEpochMilli() + SECONDS.toMillis(DEFAULT_WAIT_TIME);

        try {
            locateElement().click();
        } catch (StaleElementReferenceException | NotFoundException | InvalidElementStateException e) {
            if (Instant.now().toEpochMilli() > expireTime) {
                String message = "After attempting for " + DEFAULT_WAIT_TIME + " seconds, " + e.getMessage();
                throw new PageValidationException(message);
            } else {
                element = null;
                click();
            }
        }
    }

    protected WebElement locateElement() {
        if (element == null) {
            return driver.findElement(locator);
        } else {
            return element;
        }
    }

    protected void waitForExists() {
        try {
            wait.until((Function<WebDriver, Object>) driver -> doesExist());
        } catch (TimeoutException e) {
            throw new PageValidationException("After " + DEFAULT_WAIT_TIME
                    + " seconds, unable to find any elements with the locator " + locator.toString());
        }
    }

    protected void waitForDisplayed() {
        waitForExists();
        try {
            wait.until((Function<WebDriver, Object>) driver -> element.isDisplayed());
        } catch (TimeoutException e) {
            throw new PageValidationException("Located Element, but after"
                    + DEFAULT_WAIT_TIME + " seconds, it was still not visible. Locator: "
                    + locator.toString());
        }
    }

    protected void waitForEnabled() {
        waitForExists();
        try {
            wait.until((Function<WebDriver, Object>) driver -> element.isEnabled());
        } catch (TimeoutException e) {
            throw new PageValidationException("Located Element, but after"
                    + DEFAULT_WAIT_TIME + " seconds, it was still not visible. Locator: "
                    + locator.toString());
        }
    }


    public Boolean doesExist() {
        try {
            element = driver.findElement(locator);
        } catch (NoSuchElementException e) {
            return false;
        }
        return true;
    }

    public WebElement getElement() {
        return this.element;
    }

}
