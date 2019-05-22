package me.zyz.dsal.algorithm;

public class BubbleSort extends AbstractSort {

    @Override
    public <E extends Comparable> void sort(E[] arr) {
        singleDirectionSort(arr);
    }

    public <E extends Comparable> void singleDirectionSort(E[] arr) {

        int length = arr.length;
        for (int i = 0; i < length - 1; ++i) {
            for (int j = 0; j < length - i - 1; ++j) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    public <E extends Comparable> void doubleDirectionSort(E[] arr) {
        int length = arr.length;

        for (int i = 0; i < length / 2; ++i) {
            for (int j = i; j < length - i - 1; ++j) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                }
            }

            for (int j = length - i - 2; j > i; --j) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                }
            }
        }
    }

    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();
        BubbleSort bubbleSort = new BubbleSort();

        Integer[] ints = testUtil.randomIntArray(1000, 1000);

        Integer[] clone1 = ints.clone();
        Integer[] clone2 = ints.clone();

        long start = System.nanoTime();
        bubbleSort.singleDirectionSort(clone1);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.testSorted(clone1);

        start = System.nanoTime();
        bubbleSort.doubleDirectionSort(clone2);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.testSorted(clone2);
    }
}
