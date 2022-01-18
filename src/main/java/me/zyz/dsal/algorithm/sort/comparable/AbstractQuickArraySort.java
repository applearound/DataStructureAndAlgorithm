package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public abstract class AbstractQuickArraySort extends AbstractComparableArraySort implements ArraySort {
    /**
     * 用于小规模范围的插入排序
     *
     * @param arr 被排序数组
     * @param l   上界（包含）
     * @param h   下界（包含）
     */
    protected void insertionSort(final Object arr, final int l, final int h) {
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
