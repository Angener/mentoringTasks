package reflection_api_usage.controller;

import reflection_api_usage.view.Printer;

import java.lang.reflect.Modifier;

public final class ClassScanner {

    private Class<?> scanningClass;

    public ClassScanner(String fullName) {
        setScanningClass(fullName);
        executeScanning();
    }

    private void setScanningClass(String fullName) {
        scanningClass = retrieveScanningClass(fullName);
    }

    private Class<?> retrieveScanningClass(String fullName) {
        Class<?> temporary = null;
        try {
            temporary = Class.forName(fullName);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return temporary;
    }

    private void executeScanning() {
        retrieveSimpleName();
        retrieveName();
        checkModifiers();
        retrieveSuperclasses(scanningClass);
        retrieveInterfaces(scanningClass);
        retrieveFields(scanningClass);
        retrieveMethods(scanningClass);
        retrieveConstructors();
    }

    private void retrieveSimpleName() {
        print("Class name:");
        print(scanningClass.getSimpleName());
    }

    private <V> void print(V v) {
        new Printer<V>().print(v);
    }

    private void retrieveName() {
        print("Class full name:");
        print(scanningClass.getName());
    }

    private void checkModifiers() {
        int modifier = scanningClass.getModifiers();
        retrieveModifiers(modifier);
    }

    private void retrieveModifiers(int modifier) {
        print("Class modifiers:");
        print("Public - " + Modifier.isPublic(modifier));
        print("Protected - " + Modifier.isProtected(modifier));
        print("Private - " + Modifier.isPrivate(modifier));
        print("Static - " + Modifier.isStatic(modifier));
        print("Final - " + Modifier.isFinal(modifier));
        print("Abstract - " + Modifier.isAbstract(modifier));
    }

    private void retrieveSuperclasses(Class<?> scanningClass){
        print("Superclasses of the class:");
        checkHierarchy(scanningClass, (s) -> print(s.getSuperclass().getSimpleName()));
    }

    private void checkHierarchy(Class<?> scanningClass, PropChecker pr){
        if (hasSuperclass(scanningClass)){
            pr.act(scanningClass);
            checkHierarchy(scanningClass.getSuperclass(), pr);
        }
    }

    private boolean hasSuperclass(Class<?> scanningClass){
        return checkParentClass(scanningClass) != null;
    }

    private Class<?> checkParentClass(Class<?> scanningClass){
        return scanningClass.getSuperclass();
    }

    private void retrieveInterfaces(Class<?> scanningClass){
        print("Interfaces of class:");
        checkHierarchy(scanningClass, (s) -> print(s.getInterfaces()) );
    }

    private void retrieveFields(Class<?> scanningClass) {
        print("Fields of class:");
        checkHierarchy(scanningClass, (s) -> print(s.getDeclaredFields()));
    }

    private void retrieveMethods(Class<?> scanningClass){
        print("Methods of class:");
        checkHierarchy(scanningClass, (s) -> print(s.getDeclaredMethods()));
    }

    private void retrieveConstructors(){
        print("Constructors of class:");
        print(scanningClass.getDeclaredConstructors());
    }
}
