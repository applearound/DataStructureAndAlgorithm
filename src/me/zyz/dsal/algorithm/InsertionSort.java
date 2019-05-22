package me.zyz.dsal.algorithm;

/**
 * @author yezhou
 */
public class InsertionSort extends AbstractSort {

    @Override
    public <E extends Comparable> void sort(E[] arr) {
        copySort(arr);
    }

    public <E extends Comparable> void swapSort(E[] arr) {
        int length = arr.length;

        for (int i = 1; i < length; ++i) {
            E value = arr[i];
            int j = i;
            for (; j >= 1 && value.compareTo(arr[j - 1]) < 0; --j) {
                arr[j] = arr[j - 1];
            }
            arr[j] = value;
        }
    }

    public <E extends Comparable> void copySort(E[] arr) {
        int length = arr.length;

        for (int i = 1; i < length; ++i) {
            E value = arr[i];
            int j = i - 1;
            for (; j > -1 && value.compareTo(arr[j]) < 0; --j) {
            }
            System.arraycopy(arr, j + 1, arr, j + 2, i - j - 1);
            arr[j + 1] = value;
        }
    }

    public static void main(String[] args) {
        TestUtil testUtil = new TestUtil();
        InsertionSort insertionSort = new InsertionSort();

        Integer[] ints = testUtil.randomIntArray(1000, 1000);

        Integer[] clone1 = ints.clone();
        Integer[] clone2 = ints.clone();

        long start = System.nanoTime();
        insertionSort.swapSort(clone1);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.testSorted(clone1);

        start = System.nanoTime();
        insertionSort.swapSort(clone2);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.testSorted(clone2);
    }
}
