package me.zyz.dsal.collection.list;

import java.util.Arrays;

/**
 * @author yezhou
 */
public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_INITIAL_CAPACITY = 5;

    private Object[] innerArray;
    private int size;

    public ArrayList() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    public ArrayList(int capacity) {
        this.innerArray = new Object[capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity() {
        return innerArray.length;
    }

    @Override
    public void add(E e) {
        if (size == capacity()) {
            grow();
        }

        innerArray[size] = e;
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        if (size == capacity()) {
            grow();
        }

        System.arraycopy(innerArray, index, innerArray, index + 1, size - index);
        innerArray[index] = e;
        size++;
    }

    @Override
    public E set(int index, E e) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        E oldValue = element(index);
        innerArray[index] = e;
        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index >= size()) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        return element(index);
    }

    @Override
    public boolean remove(E e) {
        if (e == null) {
            for (int i = 0; i < size; ++i) {
                if (innerArray[i] == null) {
                    int movingCount = size - i - 1;
                    if (movingCount > 0) {
                        System.arraycopy(innerArray, i + 1, innerArray, i, movingCount);
                    }
                    // 消除引用
                    innerArray[--size] = null;

                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; ++i) {
                if (e.equals(innerArray[i])) {
                    int movingCount = size - i - 1;
                    if (movingCount > 0) {
                        System.arraycopy(innerArray, i + 1, innerArray, i, movingCount);
                    }
                    // 消除引用
                    innerArray[--size] = null;

                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void clear() {
        for (int to = size, i = size = 0; i < to; i++) {
            innerArray[i] = null;
        }
    }

    @Override
    public E remove(int index) {
        if (index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        E removingElement = element(index);
        int movingCount = size - index - 1;

        // 减少 arraycopy 的调用，比如删除的正好是最后一个元素
        if (movingCount > 0) {
            System.arraycopy(innerArray, index + 1, innerArray, index, movingCount);
        }
        // 消除引用
        innerArray[--size] = null;

        return removingElement;
    }

    @Override
    public int indexOf(E e) {
        if (e == null) {
            for (int i = 0; i < size; ++i) {
                if (innerArray[i] == null) {
                    return i;
                }
             }
        } else {
            for (int i = 0; i < size; ++i) {
                if (e.equals(innerArray[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        if (e == null) {
            for (int i = size - 1; i >= 0; --i) {
                if (innerArray[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; --i) {
                if (e.equals(innerArray[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {
        //TODO 数据溢出
        int oldCapacity = innerArray.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        innerArray = Arrays.copyOf(innerArray, newCapacity);
    }

    private E element(int index) {
        return (E) innerArray[index];
    }
}
