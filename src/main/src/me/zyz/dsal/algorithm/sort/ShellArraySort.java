package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class ShellArraySort<E extends Comparable<E>> extends AbstractComparableArraySort<E> {

    private static final int[] SEQUENCE = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};

    @Override
    public void sort(E[] arr) {
        elementFirstSort(arr);
    }


    private void elementFirstSort(E[] arr) {
        int length = arr.length;

        for (int step : SEQUENCE) {
            for (int i = step; i < length; ++i) {
                E value = arr[i];
                int j = i;
                for (; j >= step && value.compareTo(arr[j - step]) < 0; j -= step) {
                    arr[j] = arr[j - step];
                }
                arr[j] = value;
            }
        }
    }

    private void listFirstSort(E[] arr) {
        int length = arr.length;

        for (int step : SEQUENCE) {
            for (int i = 0; i < step; ++i) {
                for (int j = i + step; j < length; j += step) {
                    E value = arr[j];
                    int k = j;
                    for (; k > i && value.compareTo(arr[k - step]) < 0; k -= step) {
                        arr[k] = arr[k - step];
                    }
                    arr[k] = value;
                }
            }
        }
    }
}
