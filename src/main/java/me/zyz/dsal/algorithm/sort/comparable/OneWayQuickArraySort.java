package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;
import java.util.Random;

public class OneWayQuickArraySort extends AbstractQuickArraySort implements ArraySort {
    private final Random random = new Random(System.nanoTime());

    @Override
    protected void sort0(final Object array, final int lowIdx, final int highIdx) {
        if (highIdx - lowIdx < 16) {
            insertionSort(array, lowIdx, highIdx);
            return;
        }

        int p = partition(array, lowIdx, highIdx);
        sort0(array, lowIdx, p - 1);
        sort0(array, p + 1, highIdx);
    }

    public int partition(final Object arr, final int low, final int high) {
        swap(arr, low, low + random.nextInt(high - low + 1));
        final Object pivot = Array.get(arr, low);

        // [areaP:p][areaLP:<p][areaGP:>=p][areaUR:unread]
        // 指向areaGP区的最低位指针
        int areaGtLow = low + 1;

        // i表示当前未进入分区的待排元素
        for (int i = low + 1; i <= high; ++i) {
            if (compare(Array.get(arr, i), pivot) < 0) {
                swap(arr, areaGtLow, i);
                areaGtLow++;
            }
        }

        // pivot需要和areaLP区的最高位互换，areaLP区的最高位是p的最终位置
        swap(arr, low, areaGtLow - 1);

        return areaGtLow - 1;
    }
}
