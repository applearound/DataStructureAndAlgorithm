package me.zyz.dsal.algorithm.sort;

public class BubbleArraySort<E extends Comparable<E>> extends AbstractArraySort<E> {

    @Override
    public void sort(E[] arr) {
        doubleDirectionSort(arr);
    }

    public void singleDirectionSort(E[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; ++i) {
            boolean flag = false;
            for (int j = 0; j < length - i - 1; ++j) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public void doubleDirectionSort(E[] arr) {
        int length = arr.length;

        for (int i = 0; i < length / 2; ++i) {
            boolean flag = false;
            for (int j = i; j < length - i - 1; ++j) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    swap(arr, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }

            flag = false;
            for (int j = length - i - 2; j > i; --j) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(arr, j, j - 1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        TestUtil testUtil = TestUtil.getInstance();
        BubbleArraySort bubbleSort = new BubbleArraySort();

        Integer[] ints = testUtil.randomIntegerArray(1000, 1000);

        Integer[] clone1 = ints.clone();
        Integer[] clone2 = ints.clone();

        long start = System.nanoTime();
        bubbleSort.singleDirectionSort(clone1);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.isSorted(clone1);

        start = System.nanoTime();
        bubbleSort.doubleDirectionSort(clone2);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert testUtil.isSorted(clone2);
    }
}
