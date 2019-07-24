package me.zyz.dsal.algorithm.sort;

public abstract class AbstractHeap<E extends Comparable<E>> implements Heap<E> {
    static final int TOP_INDEX = 0;

    final Object[] innerArray;
    final int capacity;
    int size;

    AbstractHeap(int capacity) {
        this.innerArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    @Override
    public void enter(E e) {
        if (size == capacity) {
            throw new IllegalStateException("Heap is full");
        }

        innerArray[size] = e;
        shiftUp(size++);
    }

    @Override
    public E quit() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        E value = (E) innerArray[TOP_INDEX];
        swap(TOP_INDEX, --size);
        diveDown(TOP_INDEX);

        return value;
    }

    int parentPos(int childPos) {
        return (childPos - 1) >> 1;
    }

    int leftChildPos(int parentPos) {
        int leftChildPost = (parentPos << 1) + 1;
        return leftChildPost < size ? leftChildPost : -1;
    }

    int rightChildPos(int parentPos) {
        int rightChildPos = (parentPos << 1) + 2;
        return rightChildPos < size ? rightChildPos : -1;
    }

    int lastNonLeafPos() {
        return (size - 2) >> 1;
    }

    int size() {
        return size;
    }

    int capacity() {
        return capacity;
    }

    void swap(int i, int j) {
        Object o = innerArray[i];

        innerArray[i] = innerArray[j];
        innerArray[j] = o;
    }

    abstract void shiftUp(int pos);

    abstract void diveDown(int pos);
}
