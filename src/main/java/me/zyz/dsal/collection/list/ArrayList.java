package me.zyz.dsal.collection.list;

import java.util.*;

public class ArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 8;

    private int size;
    private Object[] arr;

    public ArrayList() {
        this(DEFAULT_CAPACITY);
    }

    public ArrayList(final int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }

        this.arr = new Object[capacity];
        this.size = 0;
    }

    private int capacity() {
        return arr.length;
    }

    private void checkIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void checkIndexForAdd(final int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void grow() {
        final int oldCapacity = capacity();
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity < 0) {
            newCapacity = Integer.MAX_VALUE;
        }

        if (newCapacity == oldCapacity) {
            throw new OutOfMemoryError();
        }

        arr = Arrays.copyOf(arr, newCapacity);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(arr[i])) {
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    public <T> T[] toArray(final T[] a) {
        Objects.requireNonNull(a);

        if (a.length < size) {
            return (T[]) Arrays.copyOf(arr, size, a.getClass());
        }

        System.arraycopy(arr, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(final E e) {
        if (capacity() == size) {
            grow();
        }

        arr[size++] = e;

        return true;
    }

    @Override
    public boolean remove(final Object o) {
        if (Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(arr[i])) {
                    System.arraycopy(arr, i + 1, arr, i, size - i - 1);
                    size -= 1;
                    return true;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    System.arraycopy(arr, i + 1, arr, i, size - i - 1);
                    size -= 1;
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        Objects.requireNonNull(c);

        for (final Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        for (final E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean addAll(final int index, final Collection<? extends E> c) {
        checkIndexForAdd(index);
        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        //TODO
        for (final E e : c) {
            add(e);
        }

        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;
        for (final Object o : c) {
            if (remove(o)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        Objects.requireNonNull(c);

        if (c.isEmpty()) {
            return false;
        }

        int idx = 0;
        int i = 0;
        while (i < size) {
            if (c.contains(arr[i])) {
                arr[idx++] = arr[i];
            }
            i += 1;
        }

        for (int j = idx; j < size; j++) {
            arr[j] = null;
        }

        size = idx;

        return idx != i;
    }

    @Override
    public void clear() {
        arr = new Object[0];
        size = 0;
    }

    @Override
    public E get(final int index) {
        checkIndex(index);

        return (E) arr[index];
    }

    @Override
    public E set(final int index, final E element) {
        checkIndex(index);

        final E oldValue = (E) arr[index];

        arr[index] = element;

        return oldValue;
    }

    @Override
    public void add(final int index, final E element) {
        checkIndexForAdd(index);

        if (size == capacity()) {
            grow();
        }

        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = element;
        size += 1;
    }

    @Override
    public E remove(final int index) {
        checkIndex(index);

        final E oldValue = (E) arr[index];

        System.arraycopy(arr, index + 1, arr, index, size - index - 1);

        size -= 1;

        return oldValue;
    }

    @Override
    public int indexOf(final Object o) {
        if (Objects.isNull(o)) {
            for (int i = 0; i < size; i++) {
                if (Objects.isNull(arr[i])) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (Objects.isNull(o)) {
            for (int i = size - 1; i >= 0; i--) {
                if (Objects.isNull(arr[i])) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (o.equals(arr[i])) {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return null;
    }
}
