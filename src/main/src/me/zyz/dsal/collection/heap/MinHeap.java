package me.zyz.dsal.collection.heap;

/**
 * @author yz
 */
public class MinHeap<E extends Comparable<E>> extends AbstractHeap<E> {
    public MinHeap(int capacity) {
        super(capacity);
    }

    public static <E extends Comparable<E>> MinHeap<E> heapify(E[] arr) {
        MinHeap<E> minHeap = new MinHeap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            minHeap.innerArray[i] = arr[i];
        }
        minHeap.size = arr.length;

        for (int i = minHeap.lastNonLeafPos(); i >= TOP_INDEX; --i) {
            minHeap.diveDown(i);
        }

        return minHeap;
    }

    @Override
    void shiftUp(int pos) {
        while (pos > 0) {
            int parentPos = parentPos(pos);

            E currentValue = (E) innerArray[pos];
            E parentValue = (E) innerArray[parentPos];
            if (currentValue.compareTo(parentValue) < 0) {
                swap(pos, parentPos);
                pos = parentPos;
            } else {
                break;
            }
        }
    }

    @Override
    void diveDown(int pos) {
        while (pos <= lastNonLeafPos()) {
            E parentValue = (E) innerArray[pos];

            int exchangePos = leftChildPos(pos);
            E exchangeValue = (E) innerArray[exchangePos];

            int rightChildPos = rightChildPos(pos);
            if (rightChildPos != -1) {
                E rightChildValue = (E) innerArray[rightChildPos];
                if (rightChildValue.compareTo(exchangeValue) < 0) {
                    exchangePos = rightChildPos;
                    exchangeValue = rightChildValue;
                }
            }

            if (parentValue.compareTo(exchangeValue) > 0) {
                swap(pos, exchangePos);
                pos = exchangePos;
            } else {
                break;
            }
        }
    }
}
