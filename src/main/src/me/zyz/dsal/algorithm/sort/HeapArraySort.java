package me.zyz.dsal.algorithm.sort;

/**
 * @author yz
 */
public class HeapArraySort<E extends Comparable<E>> extends AbstractComparableArraySort<E> {

    @Override
    public void sort(E[] arr) {
        inPlaceSort(arr);
    }

    private void inPlaceSort(E[] arr) {
        heapify(arr, arr.length);

        int currentSize = arr.length;
        while (currentSize >= 2) {
            swap(arr, 0, --currentSize);
            diveDown(arr, 0, currentSize, (currentSize - 2) >> 1);
        }
    }

    private void heapify(E[] arr, int size) {
        int lastNonLeafPos = (size - 2) >> 1;
        for (int currentAdjustPos = lastNonLeafPos; currentAdjustPos >= 0; --currentAdjustPos) {
            diveDown(arr, currentAdjustPos, size, lastNonLeafPos);
        }
    }

    private void diveDown(E[] arr, int pos, int size, int lastNonLeafPos) {
        while (pos <= lastNonLeafPos) {
            E parentValue = arr[pos];

            int exchangePos = (pos << 1) + 1;
            E exchangeValue = arr[exchangePos];

            int rightChildPos = (pos << 1) + 2;
            if (rightChildPos < size) {
                E rightChildValue = arr[rightChildPos];
                if (rightChildValue.compareTo(exchangeValue) > 0) {
                    exchangePos = rightChildPos;
                    exchangeValue = rightChildValue;
                }
            }

            if (parentValue.compareTo(exchangeValue) < 0) {
                swap(arr, pos, exchangePos);
                pos = exchangePos;
            } else {
                break;
            }
        }
    }
}
