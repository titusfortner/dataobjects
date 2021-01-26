package data;

import com.github.javafaker.Faker;
import common.PageValidationException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

public class BaseData {
    protected final Faker faker = new Faker();

    private Set<String> keys = new HashSet<>();

    public BaseData() {
        for (Field field : this.getClass().getDeclaredFields()) {
            keys.add(field.getName());
        }
    }

    public Object getValue(String key) {
        try {
            Method method = this.getClass().getDeclaredMethod(generateGetter(key));
            return method.invoke(this);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            throw new PageValidationException("Unable to get value of key " + key, e);
        }
    }

    public Set<String> getKeys() {
        return keys;
    }

    private String generateGetter(String key) {
        return "get" + key.substring(0, 1).toUpperCase() + key.substring(1);
    }
}
