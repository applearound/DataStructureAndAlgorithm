package me.zyz.dsal.collection.list;

/**
 * @author yz
 */
public class ArrayDeque<E> {
    private Object[] innerArray;
    private int head;
    private int tail;

    public ArrayDeque(int capacity) {
        this.innerArray = new Object[capacity];
        this.head = this.tail = 0;
    }

    public int size() {
        int size = tail - head;
        if (size < 0) {
            size += capacity();
        }

        return size;
    }

    public int capacity() {
        return innerArray.length;
    }

    public boolean isEmpty() {
        return head == tail;
    }

    public void addFirst(E e) {
        head--;
        if (head < 0) {
            head += capacity();
        }

        innerArray[head] = e;
        if (head == tail) {
            grow();
        }
    }

    public void addLast(E e) {
        innerArray[tail] = e;
        tail += 1;
        if (tail >= capacity()) {
            tail -= capacity();
        }
        if (head == tail) {
            grow();
        }
    }

    private void grow() {
        Object[] newInnerArray = new Object[capacity() + capacity() >> 1];
    }
}
