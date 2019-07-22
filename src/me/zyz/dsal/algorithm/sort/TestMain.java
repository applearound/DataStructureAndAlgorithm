package me.zyz.dsal.algorithm.sort;


public class TestMain {
    public static void main(String[] args) {
        TestUtil testUtil = TestUtil.getInstance();

//        Integer[] testIntegerListData = testUtil.randomIntegerArray(20000, 2000);
        Integer[] testIntegerListData = testUtil.almostSortedIntegerArray(0, 10000);

        System.out.println("冒泡排序");
        testUtil.test(testIntegerListData.clone(), new BubbleSort());
        System.out.println("选择排序");
        testUtil.test(testIntegerListData.clone(), new SelectionSort());
        System.out.println("插入排序");
        testUtil.test(testIntegerListData.clone(), new InsertionSort());
        System.out.println("希尔排序");
        testUtil.test(testIntegerListData.clone(), new ShellSort());
        System.out.println("归并排序");
        testUtil.test(testIntegerListData.clone(), new MergeSort());
        System.out.println("快速排序");
        testUtil.test(testIntegerListData.clone(), new QuickSort());
        System.out.println("Arrays.sort()");
        testUtil.arraysSort(testIntegerListData.clone());
    }
}
