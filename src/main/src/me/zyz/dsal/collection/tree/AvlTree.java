package me.zyz.dsal.collection.tree;


/**
 * @author yz
 */
public class AvlTree<K extends Comparable<K>, V> {
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
        return getSize() == 0;
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

        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;

        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalanced: " + balanceFactor);
        }

        // LL 旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR 旋转
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR 旋转
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL 旋转
        if (balanceFactor > 1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node root) {
        Node newRoot = root.left;
        Node tree = newRoot.right;

        newRoot.right = root;
        root.left = tree;

        return newRoot;
    }

    private Node leftRotate(Node root) {
        Node newRoot = root.right;
        Node tree = newRoot.left;

        newRoot.left = root;
        root.right = tree;

        return newRoot;
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

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;
        private int height;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

}
