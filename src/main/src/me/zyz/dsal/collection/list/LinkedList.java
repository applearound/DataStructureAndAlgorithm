package me.zyz.dsal.collection.list;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(E e) {
        Node<E> oldTail = tail;
        Node<E> newNode = new Node<>(e, oldTail, null);
        tail = newNode;

        if (oldTail == null) {
            head = newNode;
        } else {
            oldTail.next = newNode;
        }
        size++;
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }

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
