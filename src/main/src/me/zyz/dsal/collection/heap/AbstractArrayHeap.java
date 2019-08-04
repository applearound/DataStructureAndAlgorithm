package me.zyz.dsal.collection.heap;

import java.util.Arrays;

/**
 * @author yz
 */
public abstract class AbstractArrayHeap<E extends Comparable<E>> implements Heap<E> {
    private final Object[] innerArray;
    private final int capacity;
    private int size;

    AbstractArrayHeap(int capacity) {
        this.innerArray = new Object[capacity];
        this.capacity = capacity;
        this.size = 0;
    }

    AbstractArrayHeap(E[] arr) {
        this.innerArray = Arrays.copyOf(arr, arr.length);
        this.capacity = this.size = this.innerArray.length;

        for (int i = lastNonLeafPos(this.size); i >= 0; --i) {
            diveDown(i);
        }
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

        E value = (E) innerArray[0];
        swap(0, --size);
        diveDown(0);

        return value;
    }

    @Override
    public E peek(int i) {
        return (E) innerArray[i];
    }

    static int parentPos(int childPos) {
        return (childPos - 1) >> 1;
    }

    static int leftChildPos(int parentPos) {
        return (parentPos << 1) + 1;
    }

    static int rightChildPos(int parentPos) {
        return (parentPos << 1) + 2;
    }

    static int lastNonLeafPos(int size) {
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

    /**
     * 将pos位置的元素上浮
     *
     * @param pos 目标元素的下标
     */
    abstract void shiftUp(int pos);

    /**
     * 将pos位置的元素下沉
     *
     * @param pos 目标元素的下标
     */
    abstract void diveDown(int pos);
}
