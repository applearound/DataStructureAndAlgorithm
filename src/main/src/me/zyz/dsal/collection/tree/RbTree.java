package me.zyz.dsal.collection.tree;

import java.util.Comparator;
import java.util.Objects;

/**
 * 1. 每个节点不是红节点就是黑节点
 * 2. 根节点是黑节点
 * 3. 叶子节点（空节点）是黑节点
 * 4. 如果一个节点是红节点，那它的孩子一定是黑节点
 * 5. 从任一节点出发通向任意叶子节点的简单路径，经过的黑节点数量相同
 *
 * @author zyz
 */
public class RbTree<K, V> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Comparator<K> comparator;

    private Node<K, V> root;
    private int size;

    public RbTree() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        Objects.requireNonNull(key);

        return get0(root, key).value;
    }

    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }
        root = add0(root, key, value);
        root.color = BLACK;
        size++;
    }

    public boolean contains(K key) {
        Objects.requireNonNull(key);

        return get0(root, key) != null;
    }

    private Node<K, V> add0(Node<K, V> root, K key, V value) {
        if (root == null) {
            return new Node<>(key, value);
        }

        int i = compareKey(key, root.key);
        if (i < 0) {
            root.left = add0(root.left, key, value);
        } else if (i > 0) {
            root.right = add0(root.right, key, value);
        }

        if (isRed(root.right) && isBlack(root.left)) {
            root = leftRotate(root);
        }
        if (isRed(root.left) && isRed(root.left.left)) {
            root = rightRotate(root);
        }
        if (isRed(root.left) && isRed(root.right)) {
            flipColors(root);
        }

        return root;
    }

    private Node<K, V> get0(Node<K, V> root, K key) {
        if (root == null) {
            return null;
        }

        int i = compareKey(key, root.key);
        if (i < 0) {
            return get0(root.left, key);
        } else if (i > 0) {
            return get0(root.right, key);
        } else {
            return root;
        }
    }

    private Node<K, V> leftRotate(Node<K, V> root) {
        Node<K, V> x = root.right;

        root.right = x.left;
        x.left = root;

        x.color = root.color;
        root.color = RED;

        return x;
    }

    private Node<K, V> rightRotate(Node<K, V> root) {
        Node<K, V> x = root.left;

        root.left = x.right;
        x.right = root;

        x.color = root.color;
        root.color = RED;

        return x;
    }

    private void flipColors(Node<K, V> node) {
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }

    private boolean isRed(Node node) {
        if (node == null) {
            return false;
        } else {
            return node.isRed();
        }
    }

    private boolean isBlack(Node node) {
        return !isRed(node);
    }

    private int compareKey(K key1, K key2) {
        return comparator == null ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
    }

    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left, right;
        private boolean color;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = this.right = null;
            this.color = RED;
        }

        private boolean isRed() {
            return color == RED;
        }

        private boolean isBlack() {
            return !isRed();
        }
    }
}
