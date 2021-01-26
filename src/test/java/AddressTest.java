import org.junit.Test;
import pages.NewAddressPage;

import java.util.ArrayList;

public class AddressTest extends BaseTest {
    @Test
    public void loginWithData() {
        NewAddressPage newAddressPage = NewAddressPage.visit();

        newAddressPage.createAddress("First",
                                     "Last",
                                     "123 Main",
                                     "Apt B",
                                     "Beverly Hills",
                                     "California",
                                     "90210",
                                     "United States",
                                     "01/01/1970",
                                     "orange",
                                     51,
                                     "http://mysite.com",
                                     "/path/to/picture",
                                     "800-867-5309",
                                      new ArrayList<>(),
                                     "Notes about this person");
    }

}
