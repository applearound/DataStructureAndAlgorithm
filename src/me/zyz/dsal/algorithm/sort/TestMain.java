package me.zyz.dsal.algorithm.sort;


import java.util.Arrays;

public class TestMain {
    public static void main(String[] args) {
        TestUtil testUtil = TestUtil.getInstance();

        Integer[] testIntegerListData = testUtil.randomIntegerArray(10000, 1000);

        System.out.println("冒泡排序");
        testUtil.test(testIntegerListData.clone(), new BubbleArraySort<>());
        System.out.println("选择排序");
        testUtil.test(testIntegerListData.clone(), new SelectionArraySort<>());
        System.out.println("插入排序");
        testUtil.test(testIntegerListData.clone(), new InsertionArraySort<>());
        System.out.println("希尔排序");
        testUtil.test(testIntegerListData.clone(), new ShellArraySort<>());
        System.out.println("归并排序");
        testUtil.test(testIntegerListData.clone(), new MergeArraySort<>());
        System.out.println("快速排序");
        testUtil.test(testIntegerListData.clone(), new QuickArraySort<>());
        System.out.println("堆排序");
        testUtil.test(testIntegerListData.clone(), new HeapSort<>());
        System.out.println("Arrays.sort()");
        testUtil.test(testIntegerListData.clone(), Arrays::sort);
    }
}
