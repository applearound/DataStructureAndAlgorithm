package me.zyz.dsal.algorithm.sort;

import java.util.Comparator;

/**
 * @author yezhou
 */
public class TimArraySort<E extends Comparable<E>> extends AbstractArraySort<E> {
    private static final int MIN_MERGE = 32;

    private Comparator<E> comparator;

    @Override
    public void sort(Comparable[] arr) {

    }

    private void sort0(E[] arr, int low, int high) {
        int intervalLength = high - low;

        // 长度为 0 和 1 的区间永远有序
        if (intervalLength < 2) {
            return;
        }

        // If array is small, do a "mini-TimSort" with no merges
        if (intervalLength < MIN_MERGE) {
            int initRunLen = countRunAndMakeAscending(arr, low, high, comparator);
            binarySort(arr, low, high, low + initRunLen, comparator);
            return;
        }

        /**
         * March over the array once, left to right, finding natural runs,
         * extending short natural runs to minRun elements, and merging runs
         * to maintain stack invariant.
         */
//        TimSort<T> ts = new TimSort<>(a, c, work, workBase, workLen);
        int minRun = minRunLength(intervalLength);
        do {
            // Identify next run
            int runLen = countRunAndMakeAscending(arr, low, high, comparator);

            // If run is short, extend to min(minRun, intervalLength)
            if (runLen < minRun) {
                int force = intervalLength <= minRun ? intervalLength : minRun;
                binarySort(arr, low, low + force, low + runLen, comparator);
                runLen = force;
            }

            // Push run onto pending-run stack, and maybe merge
//            ts.pushRun(lo, runLen);
//            ts.mergeCollapse();

            // Advance to find next run
            low += runLen;
            intervalLength -= runLen;
        } while (intervalLength != 0);

        // Merge all remaining runs to complete sort
//        ts.mergeForceCollapse();
    }

    private void rangeCheck(int length, int low, int high) {

    }

    private int countRunAndMakeAscending(E[] arr, int low, int high, Comparator<E> comparator) {
        return 0;
    }

    private void binarySort(E[] arr, int low, int high, int start, Comparator<E> comparator) {
        if (start == low) {
            start++;
        }

        for ( ; start < high; start++) {
            E pivot = arr[start];

            // Set left (and right) to the index where a[start] (pivot) belongs
            int left = low;
            int right = start;
            assert left <= right;
            /*
             * Invariants:
             *   pivot >= all in [lo, left).
             *   pivot <  all in [right, start).
             */
            while (left < right) {
                int mid = (left + right) >>> 1;
                if (comparator.compare(pivot, arr[mid]) < 0) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            assert left == right;

            /*
             * The invariants still hold: pivot >= all in [lo, left) and
             * pivot < all in [left, start), so pivot belongs at left.  Note
             * that if there are elements equal to pivot, left points to the
             * first slot after them -- that's why this sort is stable.
             * Slide elements over to make room for pivot.
             */
            int n = start - left;  // The number of elements to move
            // Switch is just an optimization for arraycopy in default case
            switch (n) {
                case 2:  arr[left + 2] = arr[left + 1];
                case 1:  arr[left + 1] = arr[left];
                    break;
                default: System.arraycopy(arr, left, arr, left + 1, n);
            }
            arr[left] = pivot;
        }
    }

    private int minRunLength(int length) {
        return 0;
    }
}
