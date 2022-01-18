package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yezhou
 */
public class CockTailArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        boolean swapped;
        int p;
        Object current;

        int minValueLocatedIdx = startIdx;
        int maxValueLocatedIdx = endIdx;
        while (minValueLocatedIdx < maxValueLocatedIdx) {
            swapped = false;
            current = Array.get(array, minValueLocatedIdx);
            for (p = minValueLocatedIdx + 1; p <= maxValueLocatedIdx; ++p) {
                final Object next = Array.get(array, p);
                if (compare(current, next) > 0) {
                    Array.set(array, p - 1, next);
                    Array.set(array, p, current);
                    swapped = true;
                } else {
                    current = next;
                }
            }
            --maxValueLocatedIdx;
            if (!swapped) {
                break;
            }

            swapped = false;
            current = Array.get(array, maxValueLocatedIdx);
            for (p = maxValueLocatedIdx - 1; p >= minValueLocatedIdx; --p) {
                final Object previous = Array.get(array, p);
                if (compare(current, previous) < 0) {
                    Array.set(array, p, current);
                    Array.set(array, p + 1, previous);
                    swapped = true;
                } else {
                    current = previous;
                }
            }
            ++minValueLocatedIdx;
            if (!swapped) {
                break;
            }
        }
    }
}
