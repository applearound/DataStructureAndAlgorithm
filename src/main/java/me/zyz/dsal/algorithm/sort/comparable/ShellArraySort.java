package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yezhou
 */
public class ShellArraySort extends AbstractComparableArraySort implements ArraySort {
    private static final int[] SEQUENCE = new int[]{1750, 701, 301, 132, 57, 23, 10, 4, 1};

    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        for (int step : SEQUENCE) {
            for (int i = startIdx + step; i <= endIdx; ++i) {
                final Object value = Array.get(array, i);

                int j = i;
                while (j >= step) {
                    final Object o = Array.get(array, j - step);

                    if (compare(value, o) >= 0) {
                        break;
                    }

                    Array.set(array, j, o);
                    j -= step;
                }

                Array.set(array, j, value);
            }
        }
    }
}
