package pages;


public class ListAddressPage extends BasePage {
    public Boolean hasAddress(String firstName) {
        return browser().getDriver().getPageSource().contains(firstName);
    }
}
