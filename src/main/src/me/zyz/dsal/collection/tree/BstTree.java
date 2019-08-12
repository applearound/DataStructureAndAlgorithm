package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class BstTree<K extends Comparable<K>, V> implements Tree<K, V> {
    private final BinaryTree.BinaryNodeOperation<K, V> DEFAULT_OPERATION = node -> {
        System.out.println(node.value());
    };

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
        Node<K, V> node = new Node<>(key, value);
        if (root == null) {
            root = node;
        } else {
            add1(root, node);
        }
        size++;
    }

    public void addLessAssign(K key, V value) {
        root = add0(root, new Node<>(key, value));
        size++;
    }

    @Override
    public V get(K key) {
        return get0(root, key);
    }

    @Override
    public boolean contains(K key) {
        return contains0(root, key);
    }

    private Node<K, V> add0(Node<K, V> root, Node<K, V> addingNode) {
        if (root == null) {
            return addingNode;
        }

        int compareNumber = addingNode.key.compareTo(root.key);

        if (compareNumber < 0) {
            root.left = add0(root.left, addingNode);
        } else if (compareNumber > 0) {
            root.right = add0(root.right, addingNode);
        }

        return root;
    }

    private void add1(Node<K, V> root, Node<K, V> addingNode) {
        int compareNumber = addingNode.key.compareTo(root.key);

        if (compareNumber < 0) {
            if (root.left == null) {
                root.left = addingNode;
                return;
            }

            add1(root.left, addingNode);
        } else if (compareNumber > 0) {
            if (root.right == null) {
                root.right = addingNode;
                return;
            }

            add1(root.right, addingNode);
        }
    }

    private boolean contains0(Node<K, V> root, K k) {
        if (root == null) {
            return false;
        }

        int compareNumber = k.compareTo(root.key);
        if (compareNumber < 0) {
            return contains0(root.left, k);
        } else if (compareNumber > 0) {
            return contains0(root.right, k);
        } else {
            return true;
        }
    }

    private V get0(Node<K, V> root, K k) {
        if (root == null) {
            return null;
        }

        int compareNumber = k.compareTo(root.key);
        if (compareNumber < 0) {
            return get0(root.left, k);
        } else if (compareNumber > 0) {
            return get0(root.right, k);
        } else {
            return root.value;
        }
    }

    public void preOrder() {
        preOrder(DEFAULT_OPERATION);
    }

    public void preOrder(BinaryTree.BinaryNodeOperation<K, V> operation) {
        preOrder0(root, operation);
    }

    private void preOrder0(Node<K, V> root, BinaryTree.BinaryNodeOperation<K, V> operation) {
        if (root == null) {
            return;
        }

        operation.operate(root);
        preOrder0(root.left, operation);
        preOrder0(root.right, operation);
    }

    public void preOrderNoRecursion() {
        preOrderNoRecursion(DEFAULT_OPERATION);
    }

    public void preOrderNoRecursion(BinaryTree.BinaryNodeOperation<K, V> operation) {
        Deque<Node<K, V>> preOrderStack = new LinkedList<>();
        preOrderStack.push(root);
        while (!preOrderStack.isEmpty()) {
            Node<K, V> current = preOrderStack.pop();

            if (current.right != null) {
                preOrderStack.push(current.right);
            }

            if (current.left != null) {
                preOrderStack.push(current.left);
            }

            operation.operate(current);
        }
    }

    public void inOrder() {
        inOrder(DEFAULT_OPERATION);
    }

    public void inOrder(BinaryTree.BinaryNodeOperation<K, V> operation) {
        inOrder0(root, operation);
    }

    private void inOrder0(Node<K, V> root, BinaryTree.BinaryNodeOperation<K, V> operation) {
        if (root == null) {
            return;
        }

        inOrder0(this.root.left, operation);
        operation.operate(this.root);
        inOrder0(this.root.right, operation);
    }

    public void inOrderNoRecursion() {
        inOrderNoRecursion(DEFAULT_OPERATION);
    }

    public void inOrderNoRecursion(BinaryTree.BinaryNodeOperation<K, V> operation) {
        Deque<Node<K, V>> inOrderStack = new LinkedList<>();

        Node<K, V> currentNode = root;
        while (!inOrderStack.isEmpty() || currentNode != null) {
            while (currentNode != null) {
                inOrderStack.push(currentNode);
                currentNode = currentNode.left;
            }
            Node<K, V> popedNode = inOrderStack.pop();
            currentNode = popedNode.right;

            operation.operate(popedNode);
        }
    }

    public void postOrder() {
        postOrder(DEFAULT_OPERATION);
    }

    public void postOrder(BinaryTree.BinaryNodeOperation<K, V> operation) {
        postOrder0(root, operation);
    }

    private void postOrder0(Node<K, V> root, BinaryTree.BinaryNodeOperation<K, V> operation) {
        if (root == null) {
            return;
        }

        postOrder0(root.left, operation);
        postOrder0(root.right, operation);
        operation.operate(root);
    }

    public void postOrderNoRecursion() {

    }

    private void anyOrderNoRecursionBase(Node<K, V> node) {
        Stack<Node<K, V>> stack = new Stack<>();
        Set<Node<K, V>> set = new HashSet<>();
        Set<Node<K, V>> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.empty()) {
            Node<K, V> head = stack.peek();

            // pre

            if (head.left != null && set.add(head.left)) {
                stack.push(head.left);
                continue;
            }

            // in

            if (head.right != null && set.add(head.right)) {
                stack.push(head.right);
                continue;
            }

            // post

            stack.pop();
        }
    }

    public void levelOrder() {
        Queue<Node<K, V>> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            Node<K, V> current = levelOrderQueue.remove();
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

        return minimumNode(root).key;
    }

    private Node<K, V> minimumNode(Node<K, V> node) {
        if (node.left == null) {
            return node;
        }

        return minimumNode(node.left);
    }

    public K maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return maximumNode(root).key;
    }

    private Node<K, V> maximumNode(Node<K, V> node) {
        if (node.right == null) {
            return node;
        }

        return maximumNode(node.right);
    }

    public K removeMin() {
        K ret = minimum();

        removeMin0(root);

        return ret;
    }

    private Node removeMin0(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }

        node.left = removeMin0(node.left);
        return node;
    }

    public K removeMax() {
        K ret = maximum();

        removeMax0(root);

        return ret;
    }

    private Node removeMax0(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }

        node.right = removeMax0(node.right);
        return node;
    }

    public void remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null value is not acceptable.");
        }
        root = remove0(root, k);
    }

    private Node<K, V> remove0(Node<K, V> node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = k.compareTo(node.key);

        if (compareNumber < 0) {
            node.left = remove0(node.left, k);
            return node;
        } else if (compareNumber > 0) {
            node.right = remove0(node.right, k);
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

            Node successor = minimumNode(node.right);
            successor.right = removeMin0(node.right);
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

        Node(K key, V value) {
            this(key, value, null, null);
        }

        Node(K k, V v, Node<K, V> left, Node<K, V> right) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            return Objects.equals(key, ((Node<?, ?>) o).key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }

    public static void main(String[] args) {
        BstTree<Integer, Integer> bstTree = new BstTree<>();
        bstTree.add(2, 2);
        bstTree.add(1, 1);
        bstTree.add(3, 3);

        bstTree.preOrderNoRecursion();
        bstTree.inOrderNoRecursion();
    }
}
