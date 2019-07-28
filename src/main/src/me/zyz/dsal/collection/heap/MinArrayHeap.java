package me.zyz.dsal.collection.heap;

/**
 * @author yz
 */
public class MinArrayHeap<E extends Comparable<E>> extends AbstractArrayHeap<E> {
    MinArrayHeap(int capacity) {
        super(capacity);
    }

    MinArrayHeap(E[] arr) {
        super(arr);
    }

    @Override
    void shiftUp(int pos) {
        while (pos > 0) {
            int parentPos = parentPos(pos);

            E currentValue = peek(pos);
            E parentValue = peek(parentPos);
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
        while (pos <= lastNonLeafPos(size())) {
            E parentValue = peek(pos);

            int exchangePos = leftChildPos(pos);
            E exchangeValue = peek(exchangePos);

            int rightChildPos = rightChildPos(pos);
            if (rightChildPos != -1) {
                E rightChildValue = peek(rightChildPos);
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
