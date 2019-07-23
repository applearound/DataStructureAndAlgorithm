package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public final class MaxHeap<E extends Comparable<E>> implements Heap<E> {
    private final Object[] innerArray;
    private final int capacity;
    private static final int TOP_INDEX = 1;
    private int size;

    public MaxHeap(int capacity) {
        this.innerArray = new Object[capacity + 1];
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public void enter(E e) {
        if (size() == capacity()) {
            throw new IllegalStateException("Heap is full");
        }

        innerArray[size + 1] = e;
        size++;
        shiftUp(size);
    }

    @Override
    public E quit() {
        if (size() == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        E value = (E) innerArray[TOP_INDEX];
        swap(TOP_INDEX, size);

        size--;

        diveDown(TOP_INDEX);
        return value;
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    private void shiftUp(int pos) {
        for (; pos > 1; pos >>= 1) {
            E currentValue = (E) innerArray[pos];
            E parentValue = (E) innerArray[pos >> 1];
            if (currentValue.compareTo(parentValue) > 0) {
                swap(pos, pos >> 1);
            } else {
                break;
            }
        }
    }

    private void diveDown(int pos) {
        while (pos <= (size >> 1)) {
            E parentValue = (E) innerArray[pos];

            int leftChildPos = pos * 2;
            int rightChildPos = pos * 2 + 1;

            int exchangePos = leftChildPos;

            if (rightChildPos <= size) {
                E leftChildValue = (E) innerArray[leftChildPos];
                E rightChildValue = (E) innerArray[rightChildPos];
                if (rightChildValue.compareTo(leftChildValue) > 0) {
                    exchangePos = rightChildPos;
                }
            }

            if (parentValue.compareTo((E) innerArray[exchangePos]) < 0) {
                swap(pos, exchangePos);
                pos = exchangePos;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        Object o = innerArray[i];

        innerArray[i] = innerArray[j];
        innerArray[j] = o;
    }
}
