package reflection_api_usage.controller;

@FunctionalInterface
public interface PropChecker {
    void act(Class<?> scanningClass);
}
