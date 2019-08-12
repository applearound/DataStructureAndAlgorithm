package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class BstTree<K extends Comparable<K>, V> implements Tree<K, V> {
    private Node<K, V> root;
    private int size;

    public BstTree() {
        this.root = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(K key, V value) {
        root = _add2(root, key);
    }

    @Override
    public V get(K key) {
        return null;
    }

    @Override
    public boolean contains(K k) {
        return _contains(root, k);
    }

    public void addLessAssign(K k) {
        if (root == null) {
            root = new Node(k, null, null, null);
        } else {
            _add1(root, k);
        }
    }

    private void _add1(Node<K, V> node, K k) {
        int compareNumber = k.compareTo(node.key);

        if (compareNumber == 0) {
            return;
        }

        if (compareNumber < 0) {
            if (node.left == null) {
                node.left = new Node<>(k, null, null, null);
                size++;
                return;
            }

            _add1(node.left, k);
        } else {
            if (node.right == null) {
                node.right = new Node<>(k, null, null, null);
                size++;
                return;
            }

            _add1(node.right, k);
        }
    }

    private Node<K, V> _add2(Node<K, V> node, K k) {
        if (node == null) {
            size++;
            return new Node<>(k, null, null, null);
        }

        int compareNumber = k.compareTo(node.key);

        if (compareNumber < 0) {
            node.left = _add2(node.left, k);
        } else if (compareNumber > 0) {
            node.right = _add2(node.right, k);
        }

        return node;
    }

    private boolean _contains(Node<K, V> node, K k) {
        if (node == null) {
            return false;
        }

        int compareNumber = k.compareTo(node.key);
        if (compareNumber == 0) {
            return true;
        } else if (compareNumber < 0) {
            return _contains(node.left, k);
        } else {
            return _contains(node.right, k);
        }
    }

    public void inOrderNoRecursive() {
        _anyOrderNoRecursiveBase(root);
    }

    public void preOrder() {
        _preOrder(root);
    }

    public void preOrderNoRecursive() {
        Stack<Node> preOrderStack = new Stack<>();
        preOrderStack.push(root);
        while (!preOrderStack.empty()) {
            Node current = preOrderStack.pop();
            System.out.println(current.key);

            if (current.right != null) {
                preOrderStack.push(current.right);
            }

            if (current.left != null) {
                preOrderStack.push(current.left);
            }
        }
    }

    private void _preOrder(Node<K, V> node) {
        if (node == null) {
            return;
        }

        System.out.println(node.key);

        _preOrder(node.left);
        _preOrder(node.right);
    }

    public void inOrder() {
        inOrder0(root);
    }

    private void inOrder0(Node node) {
        if (node == null) {
            return;
        }

        inOrder0(node.left);
        System.out.println(node.key);
        inOrder0(node.right);
    }

    public void postOrder() {
        _postOrder(root);
    }

    private void _postOrder(Node node) {
        if (node == null) {
            return;
        }

        _postOrder(node.left);
        _postOrder(node.right);

        System.out.println(node.key);
    }

    public void levelOrder() {
        Queue<Node> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            Node current = levelOrderQueue.remove();
            System.out.println(current.key);

            if (current.left != null) {
                levelOrderQueue.add(current.left);
            }
            if (current.right != null) {
                levelOrderQueue.add(current.right);
            }
        }
    }

    public K minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return _minimumNode(root).key;
    }

    private Node<K, V> _minimumNode(Node node) {
        if (node.left == null) {
            return node;
        }

        return _minimumNode(node.left);
    }

    public K maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return _maximumNode(root).key;
    }

    private Node<K, V> _maximumNode(Node node) {
        if (node.right == null) {
            return node;
        }

        return _maximumNode(node.right);
    }

    public K removeMin() {
        K ret = minimum();

        _removeMin(root);

        return ret;
    }

    private Node _removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = _removeMin(node.left);
        return node;
    }

    public K removeMax() {
        K ret = maximum();

        _removeMax(root);

        return ret;
    }

    private Node _removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = _removeMax(node.right);
        return node;
    }

    public void remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null value is not acceptable.");
        }
        root = _remove(root, k);
    }

    private Node<K, V> _remove(Node<K, V> node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = k.compareTo(node.key);

        if (compareNumber < 0) {
            node.left = _remove(node.left, k);
            return node;
        } else if (compareNumber > 0) {
            node.right = _remove(node.right, k);
            return node;
        } else {
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            Node successor = _minimumNode(node.right);
            successor.right = _removeMin(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;

            return successor;
        }
    }


    static class Node<K extends Comparable<K>, V> implements BinaryTree.BinaryNode<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        public Node(K k, V v, Node<K, V> left, Node<K, V> right) {
            this.key = k;
            this.value = v;
            this.left = left;
            this.right = right;
        }

        @Override
        public BinaryTree.BinaryNode<K, V> left() {
            return left;
        }

        @Override
        public BinaryTree.BinaryNode<K, V> right() {
            return right;
        }

        @Override
        public K key() {
            return key;
        }

        @Override
        public V value() {
            return value;
        }
    }
}
