package me.zyz.dsal.algorithm.sort.comparable;

import me.zyz.dsal.algorithm.sort.ArraySort;

import java.lang.reflect.Array;
import java.util.Random;

/**
 * @author yezhou
 */
public class QuickArraySort extends AbstractQuickArraySort implements ArraySort {
    private final Random random = new Random(System.nanoTime());

    @Override
    protected void sort0(final Object array, final int lowIdx, final int highIdx) {
        if (highIdx - lowIdx < 16) {
            insertionSort(array, lowIdx, highIdx);
            return;
        }

        int p = partition(array, lowIdx, highIdx);
        sort0(array, lowIdx, p - 1);
        sort0(array, p + 1, highIdx);
    }

    private int partition(final Object arr, final int low, final int high) {
        swap(arr, low, low + random.nextInt(high - low + 1));
        final Object pivotValue = Array.get(arr, low);

        // [p][areaLP:<=p][i][areaUR:unread][j][areaGP:>=p]
        int i = low;
        int j = high + 1;

        while (true) {
            while (compare(pivotValue, Array.get(arr, ++i)) >= 0 && i < high) {
            }

            while (compare(pivotValue, Array.get(arr, --j)) <= 0 && j > low) {
            }
            if (i >= j) {
                break;
            }
            swap(arr, i, j);
        }
        swap(arr, low, j);

        return j;
    }
}
