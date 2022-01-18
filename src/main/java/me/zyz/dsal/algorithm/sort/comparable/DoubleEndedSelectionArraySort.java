package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

public class DoubleEndedSelectionArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        outer:
        for (int indexForMin = startIdx, indexForMax = endIdx; indexForMin < indexForMax; ++indexForMin, --indexForMax) {
            int minIndex = indexForMin;
            int maxIndex = indexForMin;

            Object minObjectValue = Array.get(array, minIndex);
            Object maxObjectValue = Array.get(array, maxIndex);

            for (int j = indexForMin + 1; j <= indexForMax; ++j) {
                final Object currentValue = Array.get(array, j);

                if (compare(currentValue, minObjectValue) < 0) {
                    minIndex = j;
                    minObjectValue = currentValue;
                }
                if (compare(currentValue, maxObjectValue) > 0) {
                    maxIndex = j;
                    maxObjectValue = currentValue;
                }
            }

            if (minIndex == maxIndex) {
                break outer;
            }

            if (minIndex == indexForMax && maxIndex == indexForMin) {
                Array.set(array, indexForMin, minObjectValue);
                Array.set(array, indexForMax, maxObjectValue);
            } else if (minIndex == indexForMax) {
                Array.set(array, maxIndex, Array.get(array, indexForMin));

                Array.set(array, indexForMin, minObjectValue);
                Array.set(array, indexForMax, maxObjectValue);
            } else if (maxIndex == indexForMin) {
                Array.set(array, minIndex, Array.get(array, indexForMax));

                Array.set(array, indexForMin, minObjectValue);
                Array.set(array, indexForMax, maxObjectValue);
            } else {
                if (minIndex != indexForMin) {
                    Array.set(array, minIndex, Array.get(array, indexForMin));
                    Array.set(array, indexForMin, minObjectValue);
                }

                if (maxIndex != indexForMax) {
                    Array.set(array, maxIndex, Array.get(array, indexForMax));
                    Array.set(array, indexForMax, maxObjectValue);
                }
            }
        }
    }
}
