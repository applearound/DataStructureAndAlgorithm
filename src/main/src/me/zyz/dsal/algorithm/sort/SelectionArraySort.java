package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class SelectionArraySort<E extends Comparable<E>> extends AbstractComparableArraySort<E> {

    @Override
    public void sort(E[] arr) {
        singleSelectionSort(arr);
    }

    public void singleSelectionSort(E[] arr) {
        int length = arr.length;

        for (int i = 0; i < length - 1; ++i) {
            int minIndex = i;
            for (int j = i + 1; j < length; ++j) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            swap(arr, minIndex, i);
        }
    }

    public void doubleSelectionSort(E[] arr) {
        int length = arr.length;

        for (int i = 0; i < length / 2; ++i) {
            int minIndex = i;
            int maxIndex = i;
            for (int j = i + 1; j < length - i; ++j) {
                if (arr[j].compareTo(arr[minIndex]) < 0) {
                    minIndex = j;
                }
                if (arr[j].compareTo(arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }

            E minValue = arr[minIndex];
            E maxValue = arr[maxIndex];

            if (maxIndex != i) {
                arr[maxIndex] = arr[length - i - 1];
            }

            if (minIndex != length - i - 1) {
                arr[minIndex] = arr[i];
            }

            arr[i] = minValue;
            arr[length - i - 1] = maxValue;
        }
    }
}
