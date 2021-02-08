package pages;

import java.util.List;

public class NewAddressPage extends BasePage {
    private static final String URL = "http://a.testaddressbook.com/addresses/new";

    public static NewAddressPage visit() {
        browser().navigate().to(URL);
        return new NewAddressPage();
    }

    public void createAddress(String firstName,
                              String lastName,
                              String address1,
                              String address2,
                              String city,
                              String state,
                              String zipCode,
                              String country,
                              String birthday,
                              String color,
                              Integer age,
                              String website,
                              String imagePath,
                              String phone,
                              List<String> interests,
                              String note){
    }

    public void createAddress(String firstName, String lastName, String address1, String address2, String city, String state, String zipCode, String country, String birthday) {

    }
}
