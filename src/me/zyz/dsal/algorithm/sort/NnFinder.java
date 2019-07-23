package me.zyz.dsal.algorithm.sort;

/**
 * @author yz
 */
public class NnFinder {

    /**
     * 寻找数组中第n大的元素
     *
     * @param arr 查找数组
     * @param n   排名
     * @param <E> Comparable<E>
     */
    public static <E extends Comparable<E>> void findMax(E[] arr, int n) {
        if (n < 1 || n > arr.length) {
            throw new IllegalArgumentException("n must: 1 <= n <= arr.length");
        }


    }

    private static <E extends Comparable<E>> E find0(E[] arr, int n, int low, int high) {
        if (low >= high) {
            return arr[low];
        }

        int mid = partition(arr, low, high);
        if (n > mid + 1) {
            return find0(arr, n, mid + 1, high);
        } else if (n < mid + 1) {
            return find0(arr, n, low, mid - 1);
        } else {
            return arr[mid];
        }
    }

    private static <E extends Comparable<E>> int partition(E arr[], int low, int high) {
        return 1;
    }
}
