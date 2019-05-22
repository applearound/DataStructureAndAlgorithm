package me.zyz.dsal.algorithm;

public class TestMain {
    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();

        Integer[] integers = testUtil.randomIntArray(20000, 10000);

        testUtil.test(integers.clone(), new BubbleSort());
        testUtil.test(integers.clone(), new SelectionSort());
        testUtil.test(integers.clone(), new InsertionSort());
    }
}
