package handle_annotation.util;

import handle_annotation.annotation.ProdCode;
import handle_annotation.annotation.ThisCodeSmells;

import java.util.Arrays;
import java.util.List;

public final class HandlerSmellsCode {

    private List<Class<?>> classesOfPackage;

    public HandlerSmellsCode(String packageName) {
        this.classesOfPackage =
                ClassArchive.getClassesOfPackage(packageName);
        execute();
    }

    private void execute() {
        showSmellsClasses();
        showSmellsMethods();
        showSmellsFields();
    }

    private void showSmellsClasses() {
        classesOfPackage.stream()
                .filter(c -> c.isAnnotationPresent(ThisCodeSmells.class))
                .forEach(System.out::println);
    }

    private void showSmellsMethods() {
        classesOfPackage.stream()
                .map(Class::getDeclaredMethods)
                .flatMap(Arrays::stream)
                .filter(m -> m.isAnnotationPresent(ThisCodeSmells.class))
                .forEach(System.out::println);
    }

    private void showSmellsFields() {
        classesOfPackage.stream()
                .map(Class::getDeclaredFields)
                .flatMap(Arrays::stream)
                .filter(f -> f.isAnnotationPresent(ThisCodeSmells.class))
                .forEach(System.out::println);
    }
}
