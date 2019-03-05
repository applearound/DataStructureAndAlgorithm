package me.zyz.dsal.tree;


import java.util.Stack;

/**
 * @author zyz
 */
public class BstTree<E extends Comparable<E>> {
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
        return size == 0;
    }

    public void add(E e) {
//        if (root == null) {
//            root = new Node(e);
//        } else {
//            _add1(root, e);
//        }
        root = _add2(root, e);
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
}
