package me.zyz.dsal.algorithm.sort;

import java.util.Iterator;

public interface SortedIndex {
    default Iterable<Integer> sortedIndex(final int[] arr) {
        return () -> sortedIndexItr(arr);
    }

    Iterator<Integer> sortedIndexItr(int[] arr);
}
