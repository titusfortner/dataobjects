import apis.UserAPI;
import data.User;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.NavBar;
import pages.SignInPage;
import pages.SignUpPage;

public class AuthenticationTest extends BaseTest {

    @Test
    public void loginImperative() {
        driver.navigate().to("http://a.testaddressbook.com/sign_in");

        WebElement emailElement = driver.findElement(By.id("session_email"));
        emailElement.sendKeys("user@example.com");

        WebElement passwordElement = driver.findElement(By.id("session_password"));
        passwordElement.sendKeys("password");

        WebElement submitElement = driver.findElement(By.cssSelector("[data-test=submit]"));
        submitElement.click();

        WebElement currentUserElement = driver.findElement(By.cssSelector("[data-test=current-user]"));

        Assert.assertEquals("user@example.com", currentUserElement.getText());
    }

    @Test
    public void loginWithData() {
        SignInPage signInPage = SignInPage.visit();

        signInPage.signIn("user@example.com", "password");

        Assert.assertTrue(new NavBar().isLoggedIn("user@example.com"));
    }

    @Test
    public void loginDataObject() {
        SignInPage signInPage = SignInPage.visit();

        User user = new User("user@example.com", "password");
        signInPage.signIn(user);

        Assert.assertTrue(new NavBar().isLoggedIn(user.getEmail()));
    }

    @Test
    public void loginStaticMethod() {
        SignInPage signInPage = SignInPage.visit();

        User user = User.valid();
        signInPage.signIn(user);

        Assert.assertTrue(new NavBar().isLoggedIn(user));
    }

    @Test
    public void signUp() {
        SignUpPage signUpPage = SignUpPage.visit();

        User user = User.random();
        signUpPage.signUp(user);

        Assert.assertTrue(new NavBar().isLoggedIn(user));
    }

    @Test
    public void signUpDefault() {
        SignUpPage signUpPage = SignUpPage.visit();

        User user = new User();
        signUpPage.signUp(user);

        Assert.assertTrue(new NavBar().isLoggedIn(user));
    }

    @Test
    public void loginBlankPassword() {
        SignInPage signInPage = SignInPage.visit();

        User user = new User();
        user.setPassword("");
        signInPage.signIn(user);

        Assert.assertFalse(new NavBar().isLoggedIn(user.getEmail()));
    }

    @Test
    public void loginBlankPasswordObject() {
        SignInPage signInPage = SignInPage.visit();

        User user = User.blankPassword();
        signInPage.signIn(user);

        Assert.assertFalse(new NavBar().isLoggedIn(user));
    }

    @Test
    public void signUpSuccessfully() {
        SignUpPage signUpPage = SignUpPage.visit();

        User user = new User();
        signUpPage.submitForm(user);

        Assert.assertTrue(new NavBar().validate(user));
    }

    @Test
    public void createUserAPI() {
        User user = new UserAPI().create();

        SignInPage.visit().signIn(user);

        Assert.assertTrue(new NavBar().validate(user));
    }
}
