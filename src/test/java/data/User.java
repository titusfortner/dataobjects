package data;

public class User extends BaseData {
    private String email = faker.internet().emailAddress();
    private String password = faker.internet().password();

    public static User valid() {
        return new User("user@example.com", "password");
    }

    public static User random() {
        return new User();
//        String email = faker.internet().emailAddress();
//        String password = faker.internet().password();
//        return new User(email, password);
    }

    public static User blankPassword() {
        User user = new User();
        user.setPassword("");
        return user;
    }

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
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

