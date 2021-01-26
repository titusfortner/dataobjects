package pages;

import data.User;
import elements.Element;
import elements.TextElement;
import org.openqa.selenium.By;

public class SignUpPage extends BasePage {

    protected TextElement email = browser().textField(By.id("user_email"));
    protected TextElement password = browser().textField(By.id("user_password"));
    protected Element submit = browser().element(By.cssSelector("[data-test=submit]"));

    public static final String URL = "http://a.testaddressbook.com/sign_up";

    public static SignUpPage visit() {
        browser().navigate().to(URL);
        return new SignUpPage();
    }

    public void signUp(User user) {
        email.sendKeys(user.getEmail());
        password.sendKeys(user.getPassword());
        submit.click();
    }

    public void signUp() {
        signUp(new User());
    }

    protected Element getSubmit() {
        return submit;
    }
}
