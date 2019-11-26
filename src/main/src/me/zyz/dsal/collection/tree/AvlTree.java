package me.zyz.dsal.collection.tree;

import java.util.Comparator;
import java.util.Objects;

/**
 * @author yz
 */
public class AvlTree<K, V> {
    private final Comparator<? super K> comparator;

    private Node root;
    private int size;

    public AvlTree() {
        this.root = null;
        this.size = 0;
        this.comparator = null;
    }

    public int size() {
        return size;
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }

    private int balanceFactor(Node node) {
        if (node == null) {
            return 0;
        }

        return height(node.left) - height(node.right);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void add(K key, V value) {
        root = add(root, key, value);
        size++;
    }

    public V get(K key) {
        Objects.requireNonNull(key);

        return node(root, key).value;
    }

    public boolean contains(K key) {
        Objects.requireNonNull(key);

        return node(root, key) != null;
    }

    private Node add(Node root, K key, V value) {
        if (root == null) {
            return new Node(key, value);
        }

        int compareNum = compare(key, root.key);
        if (compareNum < 0) {
            root.left = add(root.left, key, value);
        } else if (compareNum > 0) {
            root.right = add(root.right, key, value);
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balanceFactor = balanceFactor(root);

        if (balanceFactor > 1) {
            int leftBalanceFactor = balanceFactor(root.left);

            if (leftBalanceFactor >= 0) {
                // LL 旋转
                return rightRotate(root);
            } else {
                // LR 旋转
                root.left = leftRotate(root.left);
                return rightRotate(root);
            }
        }

        if (balanceFactor < -1) {
            int rightBalanceFactor = balanceFactor(root.right);

            if (rightBalanceFactor <= 0) {
                // RR 旋转
                return leftRotate(root);
            } else {
                // RL 旋转
                root.right = rightRotate(root.right);
                return leftRotate(root);
            }
        }

        return root;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        Node tree = newRoot.right;

        newRoot.right = root;
        root.left = tree;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        Node tree = newRoot.left;

        newRoot.left = root;
        root.right = tree;

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        newRoot.height = Math.max(height(newRoot.left), height(newRoot.right)) + 1;

        return newRoot;
    }

    private Node node(Node node, K key) {
        if (node == null) {
            return null;
        }

        int compareNum = compare(key, node.key);
        if (compareNum < 0) {
            return node(node.left, key);
        } else if (compareNum > 0) {
            return node(node.right, key);
        } else {
            return node;
        }
    }

    private int compare(K key1, K key2) {
        return comparator == null ? ((Comparable<K>) key1).compareTo(key2) : comparator.compare(key1, key2);
    }

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        private Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    public static void main(String[] args) {
        AvlTree<Integer, Integer> avlTree = new AvlTree<>();
        for (int i = 0; i < 5; i++) {
            avlTree.add(i, i);
        }
        System.out.println(avlTree.root.key);
    }
}
