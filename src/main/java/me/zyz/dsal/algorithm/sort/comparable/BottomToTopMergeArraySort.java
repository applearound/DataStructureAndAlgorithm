package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public class BottomToTopMergeArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        final int length = endIdx - startIdx + 1;
        final Object aux = Array.newInstance(array.getClass().getComponentType(), length);

        for (int sz = startIdx + 1; sz < length; sz += sz) {
            for (int i = startIdx; i < length - sz; i += sz + sz) {
                merge(array, startIdx, i, i + sz - 1, Math.min(i + sz + sz - 1, length - 1), aux);
            }
        }
    }

    /**
     * 对 arr[l...m] 和 arr[m + 1...h] 两部分进行归并
     *
     * @param arr 被排序数组
     * @param l   下界（包含）
     * @param m   下届数组的上届（包含）
     * @param h   上届（包含）
     */
    private void merge(
            final Object arr,
            final int startIdx,
            final int l,
            final int m,
            final int h,
            final Object aux
    ) {
        // 复制数据从原数组到辅助数组aux
        // 原数组的复制起点为l，长度为h - l + 1
        // 辅助数组的复制起点需要减去原数组的实际排序起点startIdx
        System.arraycopy(arr, l, aux, l - startIdx, h - l + 1);

        // i表示遍历辅助数组aux的前半指针
        int i = l - startIdx;
        // j表示遍历辅助数组aux的后半指针
        int j = m - startIdx + 1;
        // k表示原数组当前待填元素的下标
        for (int k = l; k <= h; ++k) {
            // 必然是 i 先到 m 或者 j 先到 h，两种情况之一
            // 不存在 i 和 j 同时到达 m 和 h

            // 如果i指针已经超过mid，则说明[l...m]已经遍历完毕
            if (i > m) {
                Array.set(arr, k, Array.get(aux, j++));
                continue;
            }

            // 如果j指针已经超过h，则说明[m+1...h]已经遍历完毕
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
}
