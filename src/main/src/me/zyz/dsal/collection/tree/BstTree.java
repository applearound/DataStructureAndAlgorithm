package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class BstTree<E extends Comparable<E>> {
    private Node root;
    private int size;

    public BstTree() {
        this.root = null;
        this.size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(E e) {
        root = _add2(root, e);
    }

    public void addLessAssign(E e) {
        if (root == null) {
            root = new Node(e);
        } else {
            _add1(root, e);
        }
    }

    private void _add1(Node node, E e) {
        int compareNumber = e.compareTo(node.e);

        if (compareNumber == 0) {
            return;
        }

        if (compareNumber < 0) {
            if (node.left == null) {
                node.left = new Node(e);
                size++;
                return;
            }

            _add1(node.left, e);
        } else {
            if (node.right == null) {
                node.right = new Node(e);
                size++;
                return;
            }

            _add1(node.right, e);
        }
    }

    private Node _add2(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        int compareNumber = e.compareTo(node.e);

        if (compareNumber < 0) {
            node.left = _add2(node.left, e);
        } else if (compareNumber > 0) {
            node.right = _add2(node.right, e);
        }

        return node;
    }

    public boolean contains(E e) {
        return _contains(root, e);
    }

    private boolean _contains(Node node, E e) {
        if (node == null) {
            return false;
        }

        int compareNumber = e.compareTo(node.e);
        if (compareNumber == 0) {
            return true;
        } else if (compareNumber < 0) {
            return _contains(node.left, e);
        } else {
            return _contains(node.right, e);
        }
    }

    private void _anyOrderNoRecursiveBase(Node node) {
        Stack<Node> stack = new Stack<>();
        Set<Node> set = new HashSet<>();
        Set<Node> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.empty()) {
            Node head = stack.peek();
            if (head.left != null && set.add(head.left)) {
                stack.push(head.left);
                continue;
            }

            if (accessed.add(head)) {
                System.out.println(head.e);
            }

            if (head.right != null && set.add(head.right)) {
                stack.push(head.right);
                continue;
            }

            stack.pop();
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
            System.out.println(current.e);

            if (current.right != null) {
                preOrderStack.push(current.right);
            }

             if (current.left != null) {
                preOrderStack.push(current.left);
            }
        }
    }

    private void _preOrder(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.e);

        _preOrder(node.left);
        _preOrder(node.right);
    }

    public void inOrder() {
        _inOrder(root);
    }

    private void _inOrder(Node node) {
        if (node == null) {
            return;
        }

        _inOrder(node.left);
        System.out.println(node.e);
        _inOrder(node.right);
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

        System.out.println(node.e);
    }

    public void levelOrder() {
        Queue<Node> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            Node current = levelOrderQueue.remove();
            System.out.println(current.e);

            if (current.left != null) {
                levelOrderQueue.add(current.left);
            }
            if (current.right != null) {
                levelOrderQueue.add(current.right);
            }
        }
    }

    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return _minimumNode(root).e;
    }

    private Node _minimumNode(Node node) {
        if (node.left == null) {
            return node;
        }

        return _minimumNode(node.left);
    }

    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("BST is empty");
        }

        return _maximumNode(root).e;
    }

    private Node _maximumNode(Node node) {
        if (node.right == null) {
            return node;
        }

        return _maximumNode(node.right);
    }

    public E removeMin() {
        E ret = minimum();

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

    public E removeMax() {
        E ret = maximum();

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

    public void remove(E e) {
        if (e == null) {
            throw new IllegalArgumentException("null value is not acceptable.");
        }
        root = _remove(root, e);
    }

    private Node _remove(Node node, E e) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = e.compareTo(node.e);

        if (compareNumber < 0) {
            node.left = _remove(node.left, e);
            return node;
        } else if (compareNumber > 0) {
            node.right = _remove(node.right, e);
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

    private class Node {
        private E e;
        private Node left;
        private Node right;

        public Node(E e) {
            this.e = e;
            this.left = null;
            this.right = null;
        }

        public E getE() {
            return e;
        }

        public void setE(E e) {
            this.e = e;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
