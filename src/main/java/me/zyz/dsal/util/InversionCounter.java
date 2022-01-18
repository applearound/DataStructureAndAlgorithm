package me.zyz.dsal.util;

import java.util.Arrays;

/**
 * @author yz
 */
public class InversionCounter {
    public static <E extends Comparable<E>> int count(E[] arr) {
        return divideScan(arr.clone(), 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> int divideScan(E[] arr, int low, int high) {
        if (low == high) {
            return 0;
        }

        int value = 0;

        int mid = low + (high - low) / 2;
        value += divideScan(arr, low, mid);
        value += divideScan(arr, mid + 1, high);

        value += mergeCount(arr, low, mid, high);

        return value;
    }

    private static <E extends Comparable<E>> int mergeCount(E[] arr, int low, int mid, int high) {
        int count = 0;
        E[] aux = Arrays.copyOfRange(arr, low, high + 1);

        int i = low;
        int j = mid + 1;
        // k代表的是原数组arr上当前的待填元素下标
        for (int k = low; k <= high; ++k) {
            // 必然是 i 先到 mid 或者 j 先到 h，两种情况之一
            // 不存在 i 和 j 同时到达 mid 和 h

            // 如果i指针已经超过mid，则说明[l...mid]已经遍历完毕
            if (i > mid) {
                arr[k] = aux[j - low];
                j++;
                continue;
            }

            // 如果j指针已经超过h，则说明[mid+1...h]已经遍历完毕
            if (j > high) {
                arr[k] = aux[i - low];
                i++;
                continue;
            }

            // 比较两个数组对应位置下标，填入k
            if (aux[i - low].compareTo(aux[j - low]) <= 0) {
                arr[k] = aux[i - low];
                i++;
            } else {
                arr[k] = aux[j - low];
                j++;
                count += mid - i + 1;
            }
        }

        return count;
    }
}
