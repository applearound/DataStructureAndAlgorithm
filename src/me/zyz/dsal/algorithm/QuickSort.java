package me.zyz.dsal.algorithm;

import java.util.Arrays;

/**
 * @author yezhou
 */
public class QuickSort extends AbstractSort {
    @Override
    public <E extends Comparable> void sort(E[] arr) {

    }

    public <E extends Comparable> void recursionSort(E[] arr, int low, int high) {
        int p = partition(arr, 0, arr.length - 1);
        recursionSort(arr, 0, p - 1);
        recursionSort(arr, p + 1, arr.length - 1);
    }

    private <E extends Comparable> int partition(E[] arr, int low, int high) {
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

        quickSort.partition(integers, 0, 8);

        System.out.println(Arrays.toString(integers));
    }
}
