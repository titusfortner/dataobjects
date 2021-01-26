package pages;

import data.User;
import elements.Element;
import elements.TextElement;
import org.openqa.selenium.By;

public class SignInPage extends BasePage {
    public static final String URL = "http://a.testaddressbook.com/sign_in";

    protected TextElement email = browser().textField(By.id("session_email"));
    protected TextElement password = browser().textField(By.id("session_password"));
    protected Element submit = browser().element(By.cssSelector("[data-test=submit]"));

    public static SignInPage visit() {
        browser().navigate().to(URL);
        return new SignInPage();
    }

    public void signIn(User user) {
        fillForm(user);
        submit.click();
    }

    public void signIn(String emailString, String passwordString) {
        email.sendKeys(emailString);
        password.sendKeys(passwordString);
        submit.click();
    }

//    public void signIn(User user) {
//        email.sendKeys(user.getEmail());
//        password.sendKeys(user.getPassword());
//        submit.click();
//    }

}
