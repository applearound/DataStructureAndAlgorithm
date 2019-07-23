package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public final class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private static final int TOP_INDEX = 0;

    private final Object[] innerArray;
    private final int capacity;
    private int size;

    public MaxHeap(int capacity) {
        this.innerArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
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
    public void enter(E e) {
        if (size() == capacity()) {
            throw new IllegalStateException("Heap is full");
        }

        innerArray[size] = e;
        size++;
        shiftUp(size - 1);
    }

    @Override
    public E quit() {
        if (size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        E value = (E) innerArray[TOP_INDEX];

        size--;

        swap(TOP_INDEX, size);
        diveDown(TOP_INDEX);

        return value;
    }

    private void shiftUp(int pos) {
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

    private void diveDown(int pos) {
        while (pos <= lastNonLeafPos()) {
            E parentValue = (E) innerArray[pos];

            int exchangePos = leftChildPos(pos);
            E exchangeValue = (E) innerArray[exchangePos];

            int rightChildPos = rightChildPos(pos);
            if (outOfBounds(rightChildPos)) {
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

    private int parentPos(int childPos) {
        return (childPos - 1) >> 1;
    }

    private int leftChildPos(int parentPos) {
        return (parentPos << 1) + 1;
    }

    private int rightChildPos(int parentPos) {
        return (parentPos << 1) + 2;
    }

    private int lastNonLeafPos() {
        return (size - 2) >> 1;
    }

    private boolean outOfBounds(int pos) {
        return pos < size();
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    private void swap(int i, int j) {
        Object o = innerArray[i];

        innerArray[i] = innerArray[j];
        innerArray[j] = o;
    }
}
