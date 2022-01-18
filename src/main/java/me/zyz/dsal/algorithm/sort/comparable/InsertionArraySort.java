package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;
import me.zyz.dsal.algorithm.sort.SortedIndex;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;

public class InsertionArraySort extends AbstractComparableArraySort implements ArraySort, SortedIndex {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        for (int i = startIdx + 1; i <= endIdx; ++i) {
            final Object currentValue = Array.get(array, i);

            int j = i;
            while (j > 0) {
                final Object previousValueObject = Array.get(array, j - 1);
                if (compare(currentValue, previousValueObject) >= 0) {
                    break;
                }

                Array.set(array, j--, previousValueObject);
            }

            Array.set(array, j, currentValue);
        }
    }

    @Override
    public Iterator<Integer> sortedIndexItr(final int[] arr) {
        final int[] indexArr = IntStream.range(0, arr.length).toArray();

        for (int i = 1; i < arr.length; ++i) {
            final Object currentValue = arr[indexArr[i]];

            int j = i;
            while (j > 0) {
                final Object previousValueObject = arr[indexArr[j - 1]];
                if (compare(currentValue, previousValueObject) >= 0) {
                    break;
                }

                indexArr[j] = indexArr[j - 1];
                j--;
            }
            indexArr[j] = i;
        }

        return Arrays.stream(indexArr).iterator();
    }
}
