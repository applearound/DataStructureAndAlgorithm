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
            swap(arr, 0, currentSize - 1);

            currentSize--;

            diveDown(arr, 0, currentSize, lastNonLeafPos(currentSize));
        }
    }

    private void heapify(E[] arr, int size) {
        int lastNonLeafPos = lastNonLeafPos(size);
        for (int currentAdjustPos = lastNonLeafPos; currentAdjustPos >= 0; --currentAdjustPos) {
            diveDown(arr, currentAdjustPos, size, lastNonLeafPos);
        }
    }

    private int leftChildPos(int parentPos) {
        return (parentPos << 1) + 1;
    }

    private int rightChildPos(int parentPos) {
        return (parentPos << 1) + 2;
    }

    private int lastNonLeafPos(int size) {
        return (size - 2) >> 1;
    }

    private void diveDown(E[] arr, int pos, int size, int lastNonLeafPos) {
        while (pos <= lastNonLeafPos) {
            E parentValue = arr[pos];

            int exchangePos = leftChildPos(pos);
            E exchangeValue = arr[exchangePos];

            int rightChildPos = rightChildPos(pos);
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
        Heap<E> heap = MaxHeap.heapify(arr);

        for (int i = arr.length - 1; i >= 0; --i) {
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
        Integer[] integers = {5, 4, 3, 2, 1};
        new HeapSort<Integer>().sort(integers);

        for (Integer integer : integers) {
            System.out.println(integer);
        }
    }
}
