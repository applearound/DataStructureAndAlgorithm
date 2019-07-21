package me.zyz.dsal.algorithm;

import java.util.Arrays;

/**
 * @author yezhou
 */
public class QuickSort extends AbstractSort {
    @Override
    public <E extends Comparable<E>> void sort(E[] arr) {
        recursionSort(arr, 0, arr.length - 1);
    }

    public <E extends Comparable<E>> void recursionSort(E[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int p = partition(arr, low, high);
        recursionSort(arr, low, p - 1);
        recursionSort(arr, p + 1, high);
    }

    private <E extends Comparable<E>> int partition(E[] arr, int low, int high) {
        int lo = low;
        int hi = high + 1;

        E pivot = arr[lo];

        while (true) {
            while (pivot.compareTo(arr[++lo]) > 0) {
                if (lo == high) {
                    break;
                }
            }

            while (pivot.compareTo(arr[--hi]) < 0) {
                if (hi == low) {
                    break;
                }
            }
            if (lo >= hi) {
                break;
            }
            swap(arr, lo, hi);
        }
        swap(arr, low, hi);

        return hi;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        Integer[] integers = {9, 5, 2, 3, 4, 6, 7, 8, 1};

        quickSort.recursionSort(integers, 0, integers.length - 1);

        System.out.println(Arrays.toString(integers));
    }
}
