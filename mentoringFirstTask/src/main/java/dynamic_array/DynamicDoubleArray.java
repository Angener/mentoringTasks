package dynamic_array;

import java.util.Arrays;

public class DynamicDoubleArray {

    private static final int DEFAULT_CAPACITY = 3;
    private double[] array;
    private int position = 0;
    private static final int ARRAY_ELEMENTS_INCREASING_STEP = 1;

    public DynamicDoubleArray() {
        this.array = new double[DEFAULT_CAPACITY];
    }

    public DynamicDoubleArray(int initialCapacity) {
        if (initialCapacity >= 0) {
            this.array = new double[initialCapacity];
        } else {
            throw new IllegalArgumentException("incorrect capacity:" + initialCapacity);
        }
    }

    private void add(Double element, double[] array, int position) {
        if (position == array.length) {
            array = increaseCapacityOfArray();
        }
        array[position] = element;
    }

    public void add(Double element) {
        add(element, array, position);
        position++;
    }

    private double[] increaseCapacityOfArray() {
        return grow(array.length + ARRAY_ELEMENTS_INCREASING_STEP);
    }

    private double[] grow(int minCapacity) {
        return array = Arrays.copyOf(array, minCapacity);
    }

    public double get(int index) {
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

    private double[] remove(int index, double[] array) {
        double[] result = new double[array.length - 1];
        System.arraycopy(array, 0, result, 0, index);
        System.arraycopy(array, index + 1, result, index, array.length - index - 1);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (double d : array) {
            sb.append(d).append(" ");
        }
        return sb.toString();
    }
}
