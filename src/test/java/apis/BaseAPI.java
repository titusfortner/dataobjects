package apis;

import data.BaseData;
import data.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseAPI {
    public abstract String getAPIUrl();

    public void create(BaseData data) {
        Map<String, String> payload = new HashMap<>();

        for (String key : data.getKeys()) {
            String value = (String) data.getValue(key);
            payload.put(key, value);
        }

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(getAPIUrl());
    }

    public User create() {
        User user = new User();
        create(user);
        return user;
    }


}
