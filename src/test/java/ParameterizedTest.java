import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pages.ListAddressPage;
import pages.NewAddressPage;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParameterizedTest extends BaseTest {
    private String firstName;
    private String lastName;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private String birthday;
    private Boolean success;

    public ParameterizedTest(String firstName, String lastName, String address1, String address2,
                             String city, String state, String zipCode, String country, String birthday, Boolean success) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.birthday = birthday;
        this.success = success;
    }

    @Parameterized.Parameters
    public static Collection input() {
        return Arrays.asList(new Object[][]{
                {"Brandon", "Walsh", "123 Main", "Apt 4", "Beverly Hills", "CA", "90210", "United States", "01 / 02 / 1970", true},
                {"Roger", "Ramjet", "201 First St", "", "Portland", "OR", "97200", "United States", "01 / 02 / 2001", false},
                {"Sam", "Smith", "844 Wash Ave", "Floor 2", "New York City", "ZR", "10002", "United States", "02 / 01 / 1930", true},
                {"Fred", "Flintstone", "Rock Road", "3rd", "Bedrock City", "AZ", "86046", "United States", "01 / 01 / 0001", false},
                {"George", "Jetson", "Skypad Apts", "Apt 9877", "Orbit City", "CA", "97001", "United States", "01 / 01 / 2500", true}}); }

    @Test
    public void createAddress() {
        NewAddressPage.visit().createAddress(firstName, lastName, address1, address2, city, state, zipCode, country, birthday);

        Assert.assertEquals(new ListAddressPage().hasAddress(firstName), success);
    }

}
