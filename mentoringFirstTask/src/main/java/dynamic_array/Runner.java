package dynamic_array;

public class Runner {
    public static void main(String[] args) {
        DynamicDoubleArray dynamicDoubleArray = new DynamicDoubleArray();
        DynamicDoubleArray dynamicDoubleArray1 = new DynamicDoubleArray(4);
        dynamicDoubleArray.add(0.1);
        dynamicDoubleArray.add(1.1);
        dynamicDoubleArray.add(2.1);
        dynamicDoubleArray.add(3.1);
        dynamicDoubleArray.add(4.1);
        dynamicDoubleArray.add(5.1);
        dynamicDoubleArray.add(6.1);

        dynamicDoubleArray.get(4);
        dynamicDoubleArray.remove(2);
    }
}
