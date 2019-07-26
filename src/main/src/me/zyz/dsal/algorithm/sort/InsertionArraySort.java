package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class InsertionArraySort<E extends Comparable<E>> extends AbstractArraySort<E> {

    @Override
    public void sort(E[] arr) {
        copySort(arr);
    }

    public void swapSort(E[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            E value = arr[i];
            int j = i;
            for (; j >= 1 && value.compareTo(arr[j - 1]) < 0; --j) {
                arr[j] = arr[j - 1];
            }
            arr[j] = value;
        }
    }

    public void copySort(E[] arr) {
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
    }
}
