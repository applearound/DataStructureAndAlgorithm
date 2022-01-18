package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public class GnomeArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        int pos = 0;
        while (pos <= endIdx) {
            if (pos == startIdx || ((Comparable) Array.get(array, pos)).compareTo(Array.get(array, pos - 1)) >= 0) {
                ++pos;
            } else {
                final Object posPreviousValueObject = Array.get(array, pos - 1);
                final Object posValueObject = Array.get(array, pos);

                Array.set(array, pos - 1, posValueObject);
                Array.set(array, pos, posPreviousValueObject);

                --pos;
            }
        }
    }
}
