package me.zyz.dsal.tree;


/**
 * @author zyz
 */
public class AvlTree<K extends Comparable<K>, V> {
    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;
        public int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node root;
    private int size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value) {
        if (node == null) {
            ++size;
            return new Node(key, value);
        }

        int compareNum = key.compareTo(node.key);
        if (compareNum < 0) {
            node.left = add(node.left, key, value);
        } else if (compareNum > 0) {
            node.right = add(node.right, key, value);
        } else {
            node.value = value;
        }

        node.height = Math.max(getHeight(node.left), getHeight(node.right));

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }

        return node;
    }

    private Node getNode(Node node, K key) {
        if (node == null) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        }

        int compareNum = key.compareTo(node.key);
        if (compareNum < 0) {
            return getNode(node.left, key);
        } else if (compareNum > 0) {
            return getNode(node.right, key);
        } else {
            return node;
        }
    }
}
