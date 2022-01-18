package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yz
 */
public class MergeArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        recursionSort(array, startIdx, endIdx, Array.newInstance(array.getClass().getComponentType(), endIdx - startIdx + 1));
    }

    /**
     * 使用递归的归并排序，同时也是自顶向下的归并排序
     * 对 arr[l, h] 的范围进行排序
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param h   上届（包含）
     */
    private void recursionSort(final Object arr, final int l, final int h, final Object aux) {
        // 当a[l...h]区间足够小时，可以使用其他排序方法以便获得更好的性能
        if (h - l < 16) {
            insertionSort(arr, l, h);
            return;
        }

        // 当 l 和 h 较大时，(l + h) / 2 可能会产生溢出导致不希望的结果
        int mid = l + (h - l) / 2;
        recursionSort(arr, l, mid, aux);
        recursionSort(arr, mid + 1, h, aux);

        // 当arr[mid] <= arr[mid + 1]时，由于递归的特性，arr[l..h]完全有序，不需要进行归并
        if (compare(Array.get(arr, mid), Array.get(arr, mid + 1)) > 0) {
            merge(arr, l, mid, h, aux);
        }
    }

    /**
     * 对 arr[l...mid] 和 arr[mid+1...h] 两部分进行归并
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param mid 下届数组的上届（包含）
     * @param h   上届（包含）
     */
    private void merge(final Object arr, final int l, final int mid, final int h, final Object aux) {
        // 初始化辅助数组aux
        System.arraycopy(arr, l, aux, l, h - l + 1);

        int i = l;
        int j = mid + 1;
        // k代表的是原数组arr上当前的待填元素下标
        for (int k = l; k <= h; ++k) {
            // 必然是 i 先到 mid 或者 j 先到 h，两种情况之一
            // 不存在 i 和 j 同时到达 mid 和 h

            // 如果i指针已经超过mid，则说明[l...mid]已经遍历完毕
            if (i > mid) {
                Array.set(arr, k, Array.get(aux, j++));
                continue;
            }

            // 如果j指针已经超过h，则说明[mid+1...h]已经遍历完毕
            if (j > h) {
                Array.set(arr, k, Array.get(aux, i++));
                continue;
            }

            // 比较两个数组对应位置下标，填入k
            if (compare(Array.get(aux, i), Array.get(arr, j)) <= 0) {
                Array.set(arr, k, Array.get(aux, i++));
            } else {
                Array.set(arr, k, Array.get(aux, j++));
            }
        }
    }

    /**
     * 用于小规模范围的插入排序
     *
     * @param arr 被排序数组
     * @param l   上界（包含）
     * @param h   下界（包含）
     */
    private void insertionSort(final Object arr, final int l, final int h) {
        for (int i = l + 1; i <= h; i++) {
            final Object e = Array.get(arr, i);
            int j = i;
            while (j > l) {
                final Object o = Array.get(arr, j - 1);
                if (compare(e, o) >= 0) {
                    break;
                }
                Array.set(arr, j--, o);
            }
            Array.set(arr, j, e);
        }
    }
}
