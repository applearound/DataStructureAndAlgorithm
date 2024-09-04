package me.zyz.dsal.collection.deque;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

/**
 * head >= 0
 * tail >= 0
 * <p>
 * init head = tail = 0
 * add -> tail += 1
 * addLeft -> head -= 1
 * <p>
 * Empty -> tail = head
 * Full -> tail = capacity - head + 1
 *
 * @author yz
 */
public class ArrayDeque<E> implements Deque<E> {
    private int head;
    private int tail;
    private Object[] inner;

    public ArrayDeque() {
        this(0);
    }

    public ArrayDeque(final int capacity) {
        this.inner = new Object[capacity];
        this.head = this.tail = 0;
    }

    private int capacity() {
        return inner.length;
    }

    private boolean isFull() {
        int former = head - 1;
        if (former < 0) {
            former += capacity();
        }

        return tail == former;
    }

    private int inc() {
        int newValue = tail + 1;
        if (newValue == capacity()) {
            newValue = 0;
        }

        return newValue;
    }

    @Override
    public void addFirst(final E e) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        inner[head] = e;
    }

    @Override
    public void addLast(E e) {
        if (isFull()) {
            throw new IllegalStateException();
        }

        inner[tail] = e;
    }

    @Override
    public boolean offerFirst(E e) {
        if (isFull()) {
            return false;
        }

        inner[head--] = e;

        return true;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    public E removeFirst() {
        return null;
    }

    @Override
    public E removeLast() {
        return null;
    }

    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    public E getFirst() {
        return null;
    }

    @Override
    public E getLast() {
        return null;
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean add(E e) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public int size() {
        if (tail >= head) {
            return tail - head;
        } else {
            return capacity() + tail - head;
        }
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
