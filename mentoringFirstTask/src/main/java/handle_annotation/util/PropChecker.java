package handle_annotation.util;

@FunctionalInterface
public interface PropChecker {
    void act(Class<?> scanningClass);
}
