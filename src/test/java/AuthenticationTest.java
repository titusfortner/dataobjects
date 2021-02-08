import apis.UserAPI;
import data.User;
import org.junit.Assert;
import org.junit.Test;
import pages.NavBar;
import pages.SignInPage;
import pages.SignUpPage;

public class AuthenticationTest extends BaseTest {

    @Test
    public void loginDeclarative() {
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
    public void blankPassword() {
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
    public void signUpAPICreatedUser() {
        User user = new UserAPI().create();

        SignInPage.visit().signIn(user);

        Assert.assertTrue(new NavBar().validate(user));
    }
}
