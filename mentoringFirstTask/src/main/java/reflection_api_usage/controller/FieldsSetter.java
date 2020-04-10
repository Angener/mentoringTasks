package reflection_api_usage.controller;

import java.lang.reflect.Field;

public final class FieldsSetter {
    private FieldsSetter() {
    }

    public static void setString(Object obj, String existField, String value) {
        try {
            setField(obj, existField, value);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    private static void setField(Object obj, String existField, String value) throws
            NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(existField);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
