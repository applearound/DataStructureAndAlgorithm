package me.zyz.dsal.algorithm;


public class TestMain {
    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();

        Integer[] integers = testUtil.randomIntArray(10000, 1000);

        System.out.println("冒泡排序");
        testUtil.test(integers.clone(), new BubbleSort());
        System.out.println("选择排序");
        testUtil.test(integers.clone(), new SelectionSort());
        System.out.println("插入排序");
        testUtil.test(integers.clone(), new InsertionSort());
        System.out.println("希尔排序");
        testUtil.test(integers.clone(), new ShellSort());
        System.out.println("Arrays.sort()");
        testUtil.testArraysSort(integers.clone());
    }
}
