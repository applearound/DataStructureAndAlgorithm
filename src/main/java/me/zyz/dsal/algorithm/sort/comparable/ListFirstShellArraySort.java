package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public class ListFirstShellArraySort extends AbstractComparableArraySort implements ArraySort {
    private static final int[] SEQUENCE = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};

    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        int length = endIdx - startIdx + 1;

        for (int step : SEQUENCE) {
            for (int i = 0; i < step; ++i) {
                for (int j = i + step; j < length; j += step) {
                    final Object value = Array.get(array, j);
                    int k = j;
                    while (k > i) {
                        final Object o = Array.get(array, k - step);
                        if (compare(value, o) >= 0) {
                            break;
                        }

                        Array.set(array, k, o);

                        k -= step;
                    }
                    Array.set(array, k, value);
                }
            }
        }
    }
}
