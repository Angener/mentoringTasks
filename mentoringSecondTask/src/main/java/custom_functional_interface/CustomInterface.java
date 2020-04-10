package custom_functional_interface;

@FunctionalInterface
public interface CustomInterface<T, S, U, V> {
    V get(T t, S s, U u);
}