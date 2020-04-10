package reflection_api_usage.controller;

import reflection_api_usage.entity.Appliance;
import reflection_api_usage.entity.Hoover;

import java.lang.reflect.InvocationTargetException;

public final class ClassCreator {

    public Object create(String className) {
        Object object = null;
        try {
            object = setObject(className);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return object;
    }

    private Object setObject(String className) throws ClassNotFoundException,
            InstantiationException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Class<?> classOfClass = Class.forName(className);
        return classOfClass.getDeclaredConstructor().newInstance();
    }

    public Appliance createAppliance(float weigh, int voltage) {
        Appliance appliance = null;
        try {
            appliance = setAppliance(weigh, voltage);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return appliance;
    }

    private Appliance setAppliance(float weigh, int voltage) throws
            ClassNotFoundException, InstantiationException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Class<?> classOfClass = Class.forName(Appliance.class.getName());
        Class<?>[] param = {float.class, int.class};
        return (Appliance)
                classOfClass.getConstructor(param).newInstance(weigh, voltage);
    }

    public Hoover createHoover(float weigh, int voltage, String name,
                               String brand, String originCountry) {
        Hoover hoover = null;
        try {
            hoover = setHoover(weigh, voltage, name, brand, originCountry);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return hoover;
    }

    private Hoover setHoover(float weigh, int voltage, String name,
                             String brand, String originCountry) throws
            ClassNotFoundException, InstantiationException,
            NoSuchMethodException, IllegalAccessException,
            InvocationTargetException {
        Class<?> classOfClass = Class.forName(Hoover.class.getName());
        Class<?>[] param = {float.class, int.class, String.class,
                String.class, String.class};
        return (Hoover)
                classOfClass.getConstructor(param)
                        .newInstance(weigh, voltage, name,
                                brand, originCountry);
    }
}
