package me.zyz.dsal.list;

/**
 * @author yezhou
 */
public class LinkedList<E> {
    private class Node {
        private E value;
        private Node previous;
        private Node next;

        public Node(E value) {
            this.value = value;
            this.previous = null;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public LinkedList() {
        this.head = this.tail = new Node(null);
        this.size = 0;
    }

    public void add(E e) {
        Node newNode = new Node(e);
        newNode.previous = this.tail;

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    public E get(int index) {
        if (index >= getSize() || index < 0) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        Node cur = this.head;
        for (int i = 0; i <= index; ++i) {
            cur = cur.next;
        }
        return cur.value;
    }

    public int getSize() {
        return size;
    }
}
