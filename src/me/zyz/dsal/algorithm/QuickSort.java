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
        int pivotIndex = low;
        E pivot = arr[pivotIndex];

        int lo = low + 1;
        int hi = high;

        while (lo < hi) {
            while (lo <= high && pivot.compareTo(arr[lo]) > 0) {
                lo++;
            }

            while (hi >= lo && pivot.compareTo(arr[hi]) < 0) {
                hi--;
            }
            if (lo < hi) {
                swap(arr, lo, hi);
            }
        }
        swap(arr, pivotIndex, hi);

        return hi;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        Integer[] integers = {1, 5, 2, 3, 4, 6, 7, 8, 1};

        quickSort.recursionSort(integers, 0, 8);

        System.out.println(Arrays.toString(integers));
    }
}
