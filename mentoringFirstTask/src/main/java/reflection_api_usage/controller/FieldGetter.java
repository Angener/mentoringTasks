package reflection_api_usage.controller;

import java.lang.reflect.Field;

public final class FieldGetter {
    private FieldGetter() {
    }

    public static String getString(Object obj, String existField) {
        String askedField = null;
        try {
            askedField = getField(obj, existField);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return askedField;
    }

    private static String getField(Object obj, String existField) throws
            NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(existField);
        field.setAccessible(true);
        return (String) field.get(obj);
    }
}
