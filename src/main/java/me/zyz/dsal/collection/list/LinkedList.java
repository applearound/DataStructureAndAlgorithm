package me.zyz.dsal.collection.list;

import java.util.NoSuchElementException;

/**
 * @author yezhou
 */
public class LinkedList<E> implements List<E>, Stack<E>, Queue<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this.head = this.tail = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        addLast(e);
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

        if (index == size) {
            addLast(e);
        } else {
            Node<E> indexCurrentNode = node(index);
            Node<E> previousNode = indexCurrentNode.previous;

            Node<E> newNode = new Node<>(e, indexCurrentNode.previous, indexCurrentNode);

            indexCurrentNode.previous = newNode;
            if (previousNode == null) {
                head = newNode;
            } else {
                previousNode.next = newNode;
            }

            size++;
        }
    }

    public void addFirst(E e) {
        // 临时保存头节点
        Node<E> h = head;
        // 生成新的节点
        Node<E> newNode = new Node<>(e, null, head);

        // 因为是addFirst，所以头节点必然会被更改为新生成的节点
        head = newNode;
        // h保存了原来老的头节点
        // 如果之前LinkedList中没有任何元素，那么老的头节点必然为null，这两者是等价的，既然没有元素，tail也需要变
        // 如果老头节点不为null，那么只需要更改老头节点的prev，变成新头节点就行
        if (h == null) {
            tail = newNode;
        } else {
            h.previous = newNode;
        }
        // LinkedList的size增加
        size++;
    }

    public void addLast(E e) {
        Node<E> t = tail;
        Node<E> newNode = new Node<>(e, tail, null);
        tail = newNode;
        if (t == null) {
            head = newNode;
        } else {
            t.next = newNode;
        }
        size++;
    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        Node<E> indexCurrentNode = node(index);
        E oldValue = indexCurrentNode.value;
        indexCurrentNode.value = e;

        return oldValue;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return node(index).value;
    }

    @Override
    public boolean remove(E e) {
        Node<E> currentNode = head;

        if (e == null) {
            while (currentNode != null) {
                if (currentNode.value == null) {
                    Node<E> pvs = currentNode.previous;
                    Node<E> nxt = currentNode.next;

                    if (pvs == null) {
                        head = nxt;
                    } else {
                        pvs.next = nxt;
                        currentNode.previous = null;
                    }

                    if (nxt == null) {
                        tail = pvs;
                    } else {
                        nxt.previous = pvs;
                        currentNode.next = null;
                    }

                    currentNode.value = null;
                    size--;

                    return true;
                }

                currentNode = currentNode.next;
            }
        } else {
            while (currentNode != null) {
                if (e.equals(currentNode.value)) {
                    Node<E> pvs = currentNode.previous;
                    Node<E> nxt = currentNode.next;

                    if (pvs == null) {
                        head = nxt;
                    } else {
                        pvs.next = nxt;
                        currentNode.previous = null;
                    }

                    if (nxt == null) {
                        tail = pvs;
                    } else {
                        nxt.previous = pvs;
                        currentNode.next = null;
                    }

                    currentNode.value = null;
                    size--;

                    return true;
                }

                currentNode = currentNode.next;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        Node<E> node = node(index);

        E element = node.value;

        Node<E> pvs = node.previous;
        Node<E> nxt = node.next;

        if (pvs == null) {
            head = nxt;
        } else {
            pvs.next = nxt;
            // 消除引用
            node.previous = null;
        }

        if (nxt == null) {
            tail = pvs;
        } else {
            nxt.previous = pvs;
            // 消除引用
            node.next = null;
        }

        // 消除引用
        node.value = null;
        size--;

        return element;
    }

    public E removeFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }

        E value = head.value;
        Node<E> next = head.next;

        head.next = null;
        head.value = null;

        head = next;
        if (next == null) {
            tail = null;
        } else {
            next.previous = null;
        }

        size--;

        return value;
    }

    public E removeLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }

        E value = tail.value;
        Node<E> previous = tail.previous;

        tail.next = null;
        tail.value = null;

        tail = previous;
        if (previous == null) {
            head = null;
        } else {
            previous.next = null;
        }
        size--;

        return value;
    }

    @Override
    public void clear() {
        for (Node<E> x = head; x != null; ) {
            Node<E> next = x.next;
            x.previous = null;
            x.next = null;
            x.value = null;

            x = next;
        }

        head = tail = null;
        size = 0;
    }

    @Override
    public int indexOf(E e) {
        Node<E> currentNode = head;
        int index = 0;

        if (e == null) {
            while (currentNode != null) {
                if (currentNode.value == null) {
                    return index;
                }

                currentNode = currentNode.next;
                index++;
            }
        } else {
            while (currentNode != null) {
                if (e.equals(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.next;
                index++;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> currentNode = tail;
        int index = size - 1;

        if (e == null) {
            while (currentNode != null) {
                if (currentNode.value == null) {
                    return index;
                }

                currentNode = currentNode.previous;
                index--;
            }
        } else {
            while (currentNode != null) {
                if (e.equals(currentNode.value)) {
                    return index;
                }

                currentNode = currentNode.previous;
                index--;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private Node<E> node(int index) {
        Node<E> indexCurrentNode;
        // size = 6  012|345
        // size = 7  012|3456
        if (index < (size >> 1)) {
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
    public void enter(E e) {
        addLast(e);
    }

    @Override
    public E quit() {
        return removeFirst();
    }

    @Override
    public void push(E e) {
        addFirst(e);
    }

    @Override
    public E pop() {
        return removeFirst();
    }

    private static class Node<E> {
        private E value;
        private Node<E> previous;
        private Node<E> next;

        Node(E value, Node<E> previous, Node<E> next) {
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
    }
}
