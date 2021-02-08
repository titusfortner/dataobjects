package data;

public class User extends BaseData {
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User() {
    }

    public static User valid() {
        return new User("user@example.com", "password");
    }

    public static User random() {
        return new User();
    }

    public static User blankPassword() {
        User user = new User();
        user.setPassword("");
        return user;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

