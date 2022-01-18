package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yezhou
 */
public class SelectionArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        for (int insertIndexForMin = startIdx; insertIndexForMin <= endIdx; ++insertIndexForMin) {
            int tempMinIndex = insertIndexForMin;
            Object tempMinValueObject = Array.get(array, insertIndexForMin);

            for (int i = insertIndexForMin + 1; i <= endIdx; ++i) {
                final Object currentValue = Array.get(array, i);
                if (compare(currentValue, tempMinValueObject) < 0) {
                    tempMinIndex = i;
                    tempMinValueObject = currentValue;
                }
            }

            if (tempMinIndex != insertIndexForMin) {
                Array.set(array, tempMinIndex, Array.get(array, insertIndexForMin));
                Array.set(array, insertIndexForMin, tempMinValueObject);
            }
        }
    }
}
