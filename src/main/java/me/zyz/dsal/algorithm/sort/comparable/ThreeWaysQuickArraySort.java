package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;
import java.util.Random;

public class ThreeWaysQuickArraySort extends AbstractQuickArraySort implements ArraySort {
    private final Random random = new Random(System.nanoTime());

    @Override
    protected void sort0(final Object arr, final int lowIdx, final int highIdx) {
        if (highIdx - lowIdx < 16) {
            insertionSort(arr, lowIdx, highIdx);
            return;
        }

        final long p = partition(arr, lowIdx, highIdx);

        sort0(arr, lowIdx, ((int) (p >> 32)) - 1);
        sort0(arr, ((int) p) + 1, highIdx);
    }

    public long partition(final Object arr, final int low, final int high) {
        swap(arr, low, low + random.nextInt(high - low + 1));
        final Object pivot = Array.get(arr, low);

        // [p][areaLP:<p][areaEQ:=p][i][areaU:wqR:unread][areaGP:>p]
        int ltHigh = low;
        int gtLow = high + 1;

        int i = low + 1;
        while (i < gtLow) {
            if (compare(Array.get(arr, i), pivot) < 0) {
                swap(arr, i++, ++ltHigh);
            } else if (compare(Array.get(arr, i), pivot) > 0) {
                swap(arr, i, --gtLow);
            } else {
                i++;
            }
        }
        swap(arr, low, ltHigh--);

        return (((long) (ltHigh + 1)) << 32) | (gtLow - 1);
    }
}
