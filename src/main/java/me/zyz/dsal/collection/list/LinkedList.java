package me.zyz.dsal.collection.list;

import java.util.*;

/**
 * @author yezhou
 */
public class LinkedList<E> implements List<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    private static <E> void removeNode(final Node<E> node) {
        final Node<E> previousNode = node.previous;

        if (Objects.nonNull(previousNode)) {
            previousNode.next = node.next;
            previousNode.next.previous = previousNode;
        }

        node.previous = null;
        node.next = null;
        node.value = null;
    }

    private int checkIndex(final int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        return index;
    }

    private Node<E> getNode(final int index) {
        Node<E> indexCurrentNode;

        if (index <= (size >> 1)) {
            indexCurrentNode = head;
            for (int i = 0; i < index; ++i) {
                indexCurrentNode = indexCurrentNode.next;
            }
        } else {
            indexCurrentNode = tail;
            for (int i = size - 1; i > index; --i) {
                indexCurrentNode = indexCurrentNode.previous;
            }
        }

        return indexCurrentNode;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(head);
    }

    @Override
    public boolean contains(final Object o) {
        Node<E> currentNode = head;

        if (Objects.isNull(o)) {
            while (Objects.nonNull(currentNode)) {
                if (Objects.isNull(currentNode.value)) {
                    return true;
                }

                currentNode = currentNode.next;
            }
        } else {
            while (Objects.nonNull(currentNode)) {
                if (o.equals(currentNode.value)) {
                    return true;
                }

                currentNode = currentNode.next;
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
        final Object[] array = new Object[size];

        Node<E> current = head;
        int counter = 0;
        while (Objects.nonNull(current)) {
            array[counter++] = current.value;

            current = current.next;
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(final E e) {
        final Node<E> oldTail = tail;

        Node<E> newNode = new Node<>(e, oldTail, null);

        tail = newNode;

        if (Objects.isNull(oldTail)) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }

        size += 1;

        return true;
    }

    @Override
    public boolean remove(final Object o) {
        Node<E> current = head;

        if (Objects.isNull(o)) {
            while (Objects.nonNull(current)) {
                if (Objects.isNull(current.value)) {
                    removeNode(current);

                    size -= 1;

                    return true;
                }

                current = current.next;
            }
        } else {
            while (Objects.nonNull(current)) {
                if (o.equals(current.value)) {
                    removeNode(current);

                    size -= 1;

                    return true;
                }

                current = current.next;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
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
        head = tail = null;
        size = 0;
    }

    @Override
    public E get(final int index) {
        return getNode(checkIndex(index)).value;
    }

    @Override
    public E set(final int index, final E element) {
        final Node<E> indexCurrentNode = getNode(checkIndex(index));

        final E oldValue = indexCurrentNode.value;

        indexCurrentNode.value = element;

        return oldValue;
    }

    @Override
    public void add(final int index, final E element) {

    }

    @Override
    public E remove(final int index) {
        Node<E> target = getNode(checkIndex(index));

        removeNode(target);

        return null;
    }

    @Override
    public int indexOf(final Object o) {
        Node<E> currentNode = head;
        int index = 0;

        if (Objects.isNull(o)) {
            while (Objects.nonNull(currentNode)) {
                if (Objects.isNull(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.next;
                index += 1;
            }
        } else {
            while (Objects.nonNull(currentNode)) {
                if (o.equals(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.next;
                index += 1;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(final Object o) {
        Node<E> currentNode = tail;
        int index = size - 1;

        if (Objects.isNull(o)) {
            while (Objects.nonNull(currentNode)) {
                if (Objects.isNull(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.previous;
                index -= 1;
            }
        } else {
            while (Objects.nonNull(currentNode)) {
                if (o.equals(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.previous;
                index -= 1;
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

    static class Node<E> {
        private E value;
        private Node<E> previous;
        private Node<E> next;

        Node(final E value) {
            this(value, null, null);
        }

        Node(final E value, final Node<E> previous, final Node<E> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
