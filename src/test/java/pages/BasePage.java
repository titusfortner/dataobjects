package pages;

import common.Browser;
import common.PageValidationException;
import data.BaseData;
import elements.Element;
import elements.TextElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

public class BasePage {
    protected WebDriverWait wait;
    protected static final int DEFAULT_WAIT_TIME = 20;
    protected static Browser browser;

    private Set<Field> elements = new HashSet<>();

    public BasePage() {
        for (Field field : this.getClass().getDeclaredFields()) {
            if (isElementType(field)) {
                elements.add(field);
            }
        }
        wait = new WebDriverWait(browser().getDriver(), DEFAULT_WAIT_TIME);
    }

    public static void setBrowser(Browser b) {
        browser = b;
    }

    protected static Browser browser() {
        return browser;
    }

    public void fillForm(BaseData data) {
        for (Field field : elements) {
            if (isTextFieldType(field)) {
                field.setAccessible(true);
                String dataValue = (String) data.getValue(field.getName());
                try {
                    ((TextElement) field.get(this)).sendKeys(dataValue);
                } catch (IllegalAccessException e) {
                    throw new PageValidationException("Unable to access field " + field, e);
                }
            }
        }
    }

    public void submitForm(BaseData data) {
        fillForm(data);
        getSubmit().click();
    }

    protected Element getSubmit() {
        return null;
    }

    private Boolean isElementType(Field field) {
        return Element.class.isAssignableFrom(field.getType());
    }

    private Boolean isTextFieldType(Field field) {
        return TextElement.class.isAssignableFrom(field.getType());
    }

    public boolean validate(BaseData data) {
        for (Field field : elements) {
            if (!isTextFieldType(field)) {
                String dataValue = (String) data.getValue(field.getName());
                try {
                    if (!((Element) field.get(this)).getText().equals(dataValue)) {
                        return false;
                    }
                } catch (IllegalAccessException e) {
                    throw new PageValidationException("Unable to access field " + field, e);
                }
            }
        }
        return true;
    }
}

