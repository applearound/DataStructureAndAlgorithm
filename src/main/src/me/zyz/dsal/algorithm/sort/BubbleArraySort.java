package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class BubbleArraySort<E extends Comparable<E>> extends AbstractComparableArraySort<E> {

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

        boolean flag;
        for (int i = 0; i < length / 2; ++i) {
            flag = false;
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
}
