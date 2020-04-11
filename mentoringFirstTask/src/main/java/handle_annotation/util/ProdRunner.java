package handle_annotation.util;

import handle_annotation.annotation.ProdCode;
import handle_annotation.entity.Hoover;
import reflection_api_usage.controller.PropChecker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ProdRunner {

    private static List<Method[]> methods = new ArrayList<>();

    private ProdRunner() {
    }

    public static void run(Hoover obj) {
        execute(obj);
    }

    private static void execute(Object obj) {
        retrieveMethods(obj.getClass());
        runProdMethods(obj);
    }

    private static void runProdMethods(Object obj) {

        methods.stream()
                .flatMap(Arrays::stream)
                .filter(m -> m.isAnnotationPresent(ProdCode.class))
                .peek(m -> m.setAccessible(true))
                .forEach(m -> {
                    try {
                        m.invoke(obj);
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static void retrieveMethods(Class<?> scanningClass) {
        checkHierarchy(scanningClass, (s) -> collectMethods(s.getDeclaredMethods()));
    }

    private static void checkHierarchy(Class<?> scanningClass, PropChecker pr) {
        if (hasSuperclass(scanningClass)) {
            pr.act(scanningClass);
            checkHierarchy(scanningClass.getSuperclass(), pr);
        }
    }

    private static boolean hasSuperclass(Class<?> scanningClass) {
        return checkParentClass(scanningClass) != null;
    }

    private static Class<?> checkParentClass(Class<?> scanningClass) {
        return scanningClass.getSuperclass();
    }

    private static void collectMethods(Method[] met) {
        methods.add(met);
    }
}
