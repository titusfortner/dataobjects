package apis;

import data.User;

public class UserAPI extends BaseAPI {
    public String getAPIUrl() {
        return "http://a.testaddressbook.com/users";
    }

    public User create() {
        User user = new User();
        create(user);
        return user;
    }
}
