package generalized_dynamic_array;

import java.util.Arrays;

public class GeneralizedArray<T> {

    private static final int DEFAULT_CAPACITY = 3;
    private Object[] array;
    private int position = 0;
    private static final int ARRAY_ELEMENTS_INCREASING_STEP = 1;

    public GeneralizedArray() {
        this.array = new Object[DEFAULT_CAPACITY];
    }

    public GeneralizedArray(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.array = new Object[initialCapacity];
        } else {
            throw new IllegalArgumentException("incorrect capacity:" + initialCapacity);
        }
    }

    private void add(T element, Object[] array, int position) {
        if (position == array.length) {
            array = increaseCapacityOfArray();
        }
        array[position] = element;
    }

    public void add(T element) {
        add(element, array, position);
        position++;
    }

    private Object[] increaseCapacityOfArray() {
        return grow(array.length + ARRAY_ELEMENTS_INCREASING_STEP);
    }

    private Object[] grow(int minCapacity) {
        return array = Arrays.copyOf(array, minCapacity);
    }

    public Object get(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException("incorrect input: " + index);
        } else {
            return array[index];
        }
    }

    public void remove(int index) {
        if (index < array.length && index >= 0) {
            array = remove(index, array);
        } else {
            throw new IndexOutOfBoundsException("incorrect index:" + index);
        }
    }

    private Object[] remove(int index, Object[] array) {
        Object[] result = new Object[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object o : array) {
            sb.append(o).append(" ");
        }
        return sb.toString();
    }
}
