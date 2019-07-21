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

    /**
     * 将pivot归位
     *
     * @param arr  待排序数组
     * @param low  下界（包含）
     * @param high 上届（包含）
     * @param <E>  Comparable
     * @return pivot下标
     */
    private <E extends Comparable<E>> int partition(E[] arr, int low, int high) {
//        int i = low;
//        int j = high + 1;

        E pivot = arr[low];

//        while (true) {
//            while (pivot.compareTo(arr[++i]) > 0) {
//                if (i == high) {
//                    break;
//                }
//            }
//
//            while (pivot.compareTo(arr[--j]) < 0) {
//                if (j == low) {
//                    break;
//                }
//            }
//            if (i >= j) {
//                break;
//            }
//            swap(arr, i, j);
//        }
//        swap(arr, low, j);
        int j = low;
        for (int i = low + 1; i <= high; ++i) {
            if (arr[i].compareTo(pivot) < 0) {
                swap(arr, j + 1, i);
                j++;
            }
        }

        swap(arr, low, j);

        return j;
    }

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        Integer[] integers = {9, 5, 2, 3, 4, 6, 7, 8, 1};

        quickSort.recursionSort(integers, 0, integers.length - 1);

        System.out.println(Arrays.toString(integers));
    }
}
