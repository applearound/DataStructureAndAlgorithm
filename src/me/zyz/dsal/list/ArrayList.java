package me.zyz.dsal.list;

import java.util.Arrays;

/**
 * @author yezhou
 */
public class ArrayList<E> {
    private static final int INITIAL_CAPACITY = 5;

    private Object[] innerArray;
    private int size;

    public ArrayList() {
        this(INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.innerArray = new Object[capacity];
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return innerArray.length;
    }

    public void add(E e) {
        if (getSize() == getCapacity()) {
            justifyCapacity((int) (getCapacity() * 1.5));
        }

        innerArray[size] = e;
        size++;
    }

    public E get(int index) {
        if (index >= getSize() || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return (E) innerArray[index];
    }

    private void justifyCapacity(int capacity) {
        innerArray = Arrays.copyOf(innerArray, capacity);
    }
}
