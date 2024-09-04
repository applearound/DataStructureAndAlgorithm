package me.zyz.dsal.collection.set;

import java.util.*;

/**
 * @author yz
 */
public class BstSet<E> implements Set<E> {
    private static final Comparator DEFAULT_COMPARATOR = (final Object k1, final Object k2) -> ((Comparable) k1).compareTo(k2);

    private final Comparator comparator;

    private Node<E> root;
    private int size;

    public BstSet() {
        this(DEFAULT_COMPARATOR);
    }

    public BstSet(final Comparator<E> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
    }

    private int compare(final Object e1, final Object e2) {
        return comparator.compare(e1, e2);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return Objects.isNull(root);
    }

    @Override
    public boolean contains(final Object o) {
        if (Objects.isNull(o)) {
            throw new IllegalArgumentException("BST Set never accept null value.");
        }

        Node<E> current = root;
        while (Objects.nonNull(current)) {
            final int cmp = compare(o, current.key);

            if (cmp == 0) {
                return true;
            }

            if (cmp > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            private final Deque<Node<E>> stack = new LinkedList<>();
            private Node<E> currentNode = root;

            @Override
            public boolean hasNext() {
                return Objects.nonNull(currentNode) || !stack.isEmpty();
            }

            @Override
            public E next() {
                while (Objects.nonNull(currentNode)) {
                    stack.push(currentNode);
                    currentNode = currentNode.left;
                }

                final Node<E> node = stack.pop();

                currentNode = node.right;

                return node.key;
            }
        };
    }

    @Override
    public Object[] toArray() {
        final Object[] arr = new Object[size()];

        final Iterator<E> iterator = iterator();

        int idx = 0;
        while (iterator.hasNext()) {
            arr[idx++] = iterator.next();
        }

        return arr;
    }

    @Override
    public <T> T[] toArray(final T[] arr) {
        Objects.requireNonNull(arr);

        if (arr.length < size()) {
            return (T[]) toArray();
        }

        final Iterator<E> iterator = iterator();

        int idx = 0;
        while (iterator.hasNext()) {
            arr[idx++] = (T) iterator.next();
        }

        arr[idx] = null;

        return arr;
    }

    @Override
    public boolean add(final E elem) {
        if (Objects.isNull(elem)) {
            throw new IllegalArgumentException("Element could not be null.");
        }

        final boolean isAdd = add0(elem);

        if (isAdd) {
            size += 1;
        }

        return isAdd;
    }

    @Override
    public boolean remove(final Object o) {
        if (Objects.isNull(o)) {
            throw new IllegalArgumentException("BST Set never accept null value.");
        }

        final boolean isRemoved = remove0(o);
        if (isRemoved) {
            size -= 1;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        Objects.requireNonNull(c, "Collection could not be null.");

        for (final Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends E> c) {
        Objects.requireNonNull(c, "Collection could not be null.");

        boolean isAdded = false;
        for (final E o : c) {
            if (add(o)) {
                isAdded = true;
            }
        }

        return isAdded;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        Objects.requireNonNull(c, "Collection could not be null.");

        boolean isChanged = false;
        for (final E e : this) {
            if (!c.contains(e)) {
                remove(c);
                isChanged = true;
            }
        }

        return isChanged;
    }

    @Override
    public boolean removeAll(final Collection<?> c) {
        Objects.requireNonNull(c, "Collection could not be null.");

        boolean isRemoved = false;
        for (final Object o : c) {
            if (remove(o)) {
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    private boolean add0(final E elem) {
        if (isEmpty()) {
            root = new Node<>(elem, null, null);
            return true;
        }

        Node<E> current = root;

        for (; ; ) {
            final int cmp = compare(elem, current.key);

            if (cmp == 0) {
                return false;
            }

            if (cmp > 0) {
                if (Objects.isNull(current.right)) {
                    current.right = new Node<>(elem);
                    return true;
                } else {
                    current = current.right;
                }
            } else {
                if (Objects.isNull(current.left)) {
                    current.left = new Node<>(elem);
                    return true;
                } else {
                    current = current.left;
                }
            }
        }
    }

    private boolean remove0(final Object e) {
        Node<E> parent = null;
        Node<E> current = root;

        while (Objects.nonNull(current)) {
            final int cmp = compare(e, current.key);

            if (cmp == 0) {
                if (Objects.isNull(current.left)) {
                    if (Objects.isNull(parent)) {
                        root = current.right;
                    } else if (parent.left == current) {
                        parent.left = current.right;
                    } else {
                        parent.right = current.right;
                    }
                } else if (Objects.isNull(current.right)) {
                    if (Objects.isNull(parent)) {
                        root = current.left;
                    } else if (parent.left == current) {
                        parent.left = current.left;
                    } else {
                        parent.right = current.left;
                    }
                } else {
                    Node<E> maximumParent = current;
                    Node<E> maximumNode = current.left;
                    while (Objects.nonNull(maximumNode.right)) {
                        maximumParent = maximumNode;
                        maximumNode = maximumNode.right;
                    }

                    current.key = maximumNode.key;
                    maximumParent.right = maximumNode.left;
                }

                return true;
            }

            parent = current;
            if (cmp > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    static class Node<K> {
        private K key;
        private Node<K> left;
        private Node<K> right;

        Node(final K key) {
            this(key, null, null);
        }

        Node(final K key, final Node<K> left, final Node<K> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }
}
