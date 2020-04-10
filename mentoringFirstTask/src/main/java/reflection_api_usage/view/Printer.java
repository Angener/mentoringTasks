package reflection_api_usage.view;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Consumer;

/**
 * The class using for printing
 * of results to the console
 */
@SuppressWarnings("unused")
public final class Printer<V> {

    public void print(List<V> v) {
        consumers.accept(v);
    }

    public void print(V v) {
        if (v instanceof Class<?>[]) {
            consumerArr.accept((Class<?>[]) v);
        } else if (v instanceof Field[]){
            consumerFields.accept((Field[]) v);
        } else if (v instanceof Method[]){
            consumerMethods.accept((Method[]) v);
        } else if (v instanceof Constructor<?>[]){
            consumerConstructor.accept((Constructor<?>[]) v);
        } else {
            consumerObj.accept(v);
        }
    }

    private Consumer<List<V>> consumers = (v)
            -> v.forEach(System.out::println);
    private Consumer<Class<?>[]> consumerArr = (v) -> {
        for (Class<?> o : v) {
            System.out.println(o);
        }
    };
    private Consumer<Field[]> consumerFields = (v) -> {
        for (Field f : v) {
            System.out.println(Modifier.toString(f.getModifiers()) + " " + f.getName());
        }
    };
    private Consumer<Method[]> consumerMethods = (v) -> {
        for (Method m : v){
            System.out.println(m);
        }
    };
    private Consumer<Constructor<?>[]> consumerConstructor = (v) -> {
        for (Constructor<?> c : v){
            System.out.println(c);
        }
    };
    private Consumer<V> consumerObj = (System.out::println);
}
