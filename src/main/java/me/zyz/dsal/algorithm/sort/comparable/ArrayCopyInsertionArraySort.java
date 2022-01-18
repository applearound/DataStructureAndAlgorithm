package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.AbstractComparableArraySort;
import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;

/**
 * @author yezhou
 */
public class ArrayCopyInsertionArraySort extends AbstractComparableArraySort implements ArraySort {
    @Override
    protected void sort0(final Object array, final int startIdx, final int endIdx) {
        for (int idxForInserting = startIdx + 1; idxForInserting <= endIdx; ++idxForInserting) {
            final Object objForInserting = Array.get(array, idxForInserting);

            int possibleIdx = idxForInserting;
            for (; possibleIdx > startIdx && compare(objForInserting, Array.get(array, possibleIdx - 1)) < 0; --possibleIdx) {
            }

            final int realInsertIdx = possibleIdx;
            if (realInsertIdx != idxForInserting) {
                System.arraycopy(array, realInsertIdx, array, realInsertIdx + 1, idxForInserting - realInsertIdx);
                Array.set(array, realInsertIdx, objForInserting);
            }
        }
    }
}
