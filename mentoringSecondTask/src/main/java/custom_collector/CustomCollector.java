package custom_collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomCollector implements Collector<A, Set<String>, List<String>> {
    @Override
    public Supplier<Set<String>> supplier() {
        return TreeSet<String>::new;
    }

    @Override
    public BiConsumer<Set<String>, A> accumulator() {
        return (l, r) -> l.add(new String(r.getTEXT_ELEMENTS()));
    }

    @Override
    public BinaryOperator<Set<String>> combiner() {
        return (l1, l2) -> Stream.of(l1, l2)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    @Override
    public Function<Set<String>, List<String>> finisher() {
        return ArrayList::new;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.UNORDERED);
    }
}
