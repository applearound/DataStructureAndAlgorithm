package me.zyz.dsal.util;

import java.security.SecureRandom;
import java.util.Comparator;

/**
 * @author yz
 */
public class NnFinder {
    private static final SecureRandom random = new SecureRandom();

    private NnFinder() {
    }

    /**
     * 寻找数组中第n大的元素
     *
     * @param arr 查找数组
     * @param n   排名
     * @param <E> Comparable<E>
     */
    public static <E extends Comparable<E>> E findMaxN(E[] arr, int n) {
        if (n < 1 || n > arr.length) {
            throw new IllegalArgumentException("n must: 1 <= n <= arr.length");
        }

        return findN0(arr, 0, arr.length - 1, n, Comparator.reverseOrder());
    }

    /**
     * 寻找数组中第n小的元素
     *
     * @param arr 查找数组
     * @param n   排名
     * @param <E> Comparable<E>
     */
    public static <E extends Comparable<E>> E findMinN(E[] arr, int n) {
        if (n < 1 || n > arr.length) {
            throw new IllegalArgumentException("n must: 1 <= n <= arr.length");
        }

        return findN0(arr, 0, arr.length - 1, n, Comparator.naturalOrder());
    }

    private static <E extends Comparable<E>> E findN0(E[] arr, int low, int high, int n, Comparator<E> comparator) {
        int mid = partition(arr, low, high, comparator);
        if (n < mid + 1) {
            return findN0(arr, low, mid - 1, n, comparator);
        } else if (n > mid + 1) {
            return findN0(arr, mid + 1, high, n, comparator);
        } else {
            return arr[mid];
        }
    }

    private static <E extends Comparable<E>> int partition(E[] arr, int low, int high, Comparator<E> comparator) {
        ArrayUtil.swap(arr, low, low + random.nextInt(high - low + 1));
        E pivot = arr[low];

        // [areaP:p][areaLP:<p][areaGP:>=p][areaUR:unread]
        // 指向areaGP区的最低位指针
        int areaGtLow = low + 1;

        // i表示当前未进入分区的待排元素
        for (int i = low + 1; i <= high; ++i) {
            if (comparator.compare(arr[i], pivot) < 0) {
                ArrayUtil.swap(arr, areaGtLow, i);
                areaGtLow++;
            }
        }

        int partitionIndex = areaGtLow - 1;
        // pivot需要和areaLP区的最高位互换，areaLP区的最高位是p的最终位置
        ArrayUtil.swap(arr, low, partitionIndex);

        return partitionIndex;
    }
}
