package pages;

import data.User;
import elements.Element;
import org.openqa.selenium.By;

public class NavBar extends BasePage {
    protected Element email = browser().element(By.cssSelector("[data-test=current-user]"));

    public boolean isLoggedIn() {
        return email.doesExist();
    }

    public boolean isLoggedIn(User user) {
        return email.doesExist() && email.getText().equals(user.getEmail());
    }

    public boolean isLoggedIn(String userEmail) {
        return email.doesExist() && email.getText().equals(userEmail);
    }
}
