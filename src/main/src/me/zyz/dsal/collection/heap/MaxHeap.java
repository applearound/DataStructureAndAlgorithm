package me.zyz.dsal.collection.heap;

/**
 * @author yezhou
 */
public final class MaxHeap<E extends Comparable<E>> extends AbstractHeap<E> {
    private static final int TOP_INDEX = 0;

    public MaxHeap(int capacity) {
        super(capacity);
    }

    public static <E extends Comparable<E>> MaxHeap<E> heapify(E[] arr) {
        MaxHeap<E> maxHeap = new MaxHeap<>(arr.length);
        for (int i = 0; i < arr.length; i++) {
            maxHeap.innerArray[i] = arr[i];
        }
        maxHeap.size = arr.length;

        for (int i = maxHeap.lastNonLeafPos(); i >= TOP_INDEX; --i) {
            maxHeap.diveDown(i);
        }

        return maxHeap;
    }

    @Override
    void shiftUp(int pos) {
        while (pos > 0) {
            int parentPos = parentPos(pos);

            E currentValue = (E) innerArray[pos];
            E parentValue = (E) innerArray[parentPos];
            if (currentValue.compareTo(parentValue) > 0) {
                swap(pos, parentPos);
                pos = parentPos;
            } else {
                break;
            }
        }
    }

    @Override
    void diveDown(int pos) {
        int lastNonLeafPos = lastNonLeafPos();
        while (pos <= lastNonLeafPos) {
            E parentValue = (E) innerArray[pos];

            int exchangePos = leftChildPos(pos);
            E exchangeValue = (E) innerArray[exchangePos];

            int rightChildPos = rightChildPos(pos);
            if (rightChildPos != -1) {
                E rightChildValue = (E) innerArray[rightChildPos];
                if (rightChildValue.compareTo(exchangeValue) > 0) {
                    exchangePos = rightChildPos;
                    exchangeValue = rightChildValue;
                }
            }

            if (parentValue.compareTo(exchangeValue) < 0) {
                swap(pos, exchangePos);
                pos = exchangePos;
            } else {
                break;
            }
        }
    }
}
