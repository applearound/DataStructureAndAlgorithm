package me.zyz.dsal.algorithm.sort;

/**
 * @author yz
 */
public class HeapSort<E extends Comparable<E>> extends AbstractArraySort<E> {

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

    private void heapifySort(E[] arr) {
        Heap<E> heap = MinHeap.heapify(arr);

        for (int i = 0; i < arr.length; ++i) {
            arr[i] = heap.quit();
        }
    }

    private void rawSort(E[] arr) {
        Heap<E> heap = new MaxHeap<>(arr.length);

        for (E e : arr) {
            heap.enter(e);
        }

        for (int i = arr.length - 1; i >= 0; --i) {
            arr[i] = heap.quit();
        }
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5};
        new HeapSort<Integer>().heapifySort(integers);

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
