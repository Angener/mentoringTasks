package reflection_api_usage.controller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class MethodInvoker {
    private MethodInvoker() {
    }

    public static String invokeMethod(Object obj, String existMethod) {
        String returnsValue = null;
        try {
            returnsValue = accessMethod(obj, existMethod);
        } catch (NoSuchMethodException | IllegalAccessException |
                InvocationTargetException ex) {
            ex.printStackTrace();
        }
        return returnsValue;
    }

    private static String accessMethod(Object obj, String existMethod) throws
            NoSuchMethodException, IllegalAccessException, InvocationTargetException{
        Method method = obj.getClass().getDeclaredMethod(existMethod);
        method.setAccessible(true);
        return  (String) method.invoke(obj);
    }
}
