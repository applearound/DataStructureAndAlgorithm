package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class LinkedBinarySearchTree<K, V> implements BinarySearchTree<K, V> {
    private final Comparator<K> comparator;

    private LinkedBinaryNode root;
    private int size;

    public LinkedBinarySearchTree() {
        this(null);
    }

    public LinkedBinarySearchTree(Comparator<K> comparator) {
        this.root = null;
        this.size = 0;
        this.comparator = comparator;
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
        validateKeyValue(key, value);
        LinkedBinaryNode newNode = new LinkedBinaryNode(key, value);
        if (root == null) {
            root = newNode;
        } else {
            add1(root, newNode);
        }
        size++;
    }

    public void anotherAdd(K key, V value) {
        validateKeyValue(key, value);
        root = add0(root, new LinkedBinaryNode(key, value));
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

    private LinkedBinaryNode add0(LinkedBinaryNode root, LinkedBinaryNode addingNode) {
        if (root == null) {
            return addingNode;
        }

        int compareNumber = compare(addingNode.key, root.key);

        if (compareNumber < 0) {
            root.left = add0(root.left, addingNode);
        } else if (compareNumber > 0) {
            root.right = add0(root.right, addingNode);
        }

        return root;
    }

    private void add1(LinkedBinaryNode root, LinkedBinaryNode addingNode) {
        int compareNumber = compare(addingNode.key, root.key);

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

    private boolean contains0(LinkedBinaryNode root, K k) {
        if (root == null) {
            return false;
        }

        int compareNumber = compare(k, root.key);
        if (compareNumber < 0) {
            return contains0(root.left, k);
        } else if (compareNumber > 0) {
            return contains0(root.right, k);
        } else {
            return true;
        }
    }

    private V get0(LinkedBinaryNode root, K k) {
        if (root == null) {
            return null;
        }

        int compareNumber = compare(k, root.key);
        if (compareNumber < 0) {
            return get0(root.left, k);
        } else if (compareNumber > 0) {
            return get0(root.right, k);
        } else {
            return root.value;
        }
    }

    public void preOrder() {
        preOrder0(root);
    }

    private void preOrder0(LinkedBinaryNode root) {
        if (root == null) {
            return;
        }

        System.out.print(root.value + " ");
        preOrder0(root.left);
        preOrder0(root.right);
    }

    public void preOrderNoRecursion() {
        Deque<LinkedBinaryNode> preOrderStack = new LinkedList<>();
        preOrderStack.push(root);
        while (!preOrderStack.isEmpty()) {
            LinkedBinaryNode currentNode = preOrderStack.pop();

            if (currentNode.right != null) {
                preOrderStack.push(currentNode.right);
            }

            if (currentNode.left != null) {
                preOrderStack.push(currentNode.left);
            }

            System.out.print(currentNode.value + " ");
        }
    }


    public void inOrder() {
        inOrder0(root);
    }

    private void inOrder0(LinkedBinaryNode root) {
        if (root == null) {
            return;
        }

        inOrder0(root.left);
        System.out.print(root.value + " ");
        inOrder0(root.right);
    }

    public void inOrderNoRecursion() {
        Deque<LinkedBinaryNode> inOrderStack = new LinkedList<>();

        LinkedBinaryNode currentNode = root;
        while (!inOrderStack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                inOrderStack.push(currentNode);
                currentNode = currentNode.left;
            } else {
                LinkedBinaryNode node = inOrderStack.pop();
                currentNode = node.right;

                System.out.print(node.value + " ");
            }
        }
    }

    public void postOrder() {
        postOrder0(root);
    }

    private void postOrder0(LinkedBinaryNode root) {
        if (root == null) {
            return;
        }

        postOrder0(root.left);
        postOrder0(root.right);
        System.out.print(root.value + " ");
    }

    public void postOrderNoRecursion() {
        Deque<LinkedBinaryNode> postOrderStack = new LinkedList<>();
        LinkedBinaryNode cur;
        LinkedBinaryNode pre = null;

        postOrderStack.push(root);
        while (!postOrderStack.isEmpty()) {
            cur = postOrderStack.peek();
            if ((cur.left == null && cur.right == null) ||
                    (pre != null && (pre == cur.left || pre == cur.right))) {
                postOrderStack.pop();
                pre = cur;

                System.out.print(cur.value + " ");
            } else {
                if (cur.right != null) {
                    postOrderStack.push(cur.right);
                }
                if (cur.left != null) {
                    postOrderStack.push(cur.left);
                }
            }
        }
    }

    private void anyOrderNoRecursionBase(LinkedBinaryNode node) {
        Deque<LinkedBinaryNode> stack = new LinkedList<>();
        Set<LinkedBinaryNode> set = new HashSet<>();
        Set<LinkedBinaryNode> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            LinkedBinaryNode head = stack.peek();

            // pre
//            if (accessed.add(head)) {
//            }

            if (head.left != null && set.add(head.left)) {
                stack.push(head.left);
                continue;
            }

            // in
//            if (accessed.add(head)) {
//            }

            if (head.right != null && set.add(head.right)) {
                stack.push(head.right);
                continue;
            }

            // post
//            if (accessed.add(head)) {
//            }

            stack.pop();
        }
    }

    public void levelOrder() {
        Queue<LinkedBinaryNode> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            LinkedBinaryNode current = levelOrderQueue.remove();
            System.out.print(current.key + " ");

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

    private LinkedBinaryNode minimumNode(LinkedBinaryNode node) {
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

    private LinkedBinaryNode maximumNode(LinkedBinaryNode node) {
        if (node.right == null) {
            return node;
        }

        return maximumNode(node.right);
    }

    public K removeMin() {
        K ret = minimum();

        removeMin0(root);
        size--;

        return ret;
    }

    private LinkedBinaryNode removeMin0(LinkedBinaryNode node) {
        if (node.left == null) {
            LinkedBinaryNode rightNode = node.right;
            node.right = null;
            return rightNode;
        }

        node.left = removeMin0(node.left);
        return node;
    }

    public K removeMax() {
        K ret = maximum();

        removeMax0(root);
        size--;

        return ret;
    }

    private LinkedBinaryNode removeMax0(LinkedBinaryNode node) {
        if (node.right == null) {
            LinkedBinaryNode leftNode = node.left;
            node.left = null;
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
        size--;
    }

    private LinkedBinaryNode remove0(LinkedBinaryNode node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = compare(k, node.key);

        if (compareNumber < 0) {
            node.left = remove0(node.left, k);
            return node;
        } else if (compareNumber > 0) {
            node.right = remove0(node.right, k);
            return node;
        } else {
            if (node.left == null) {
                LinkedBinaryNode rightNode = node.right;
                node.right = null;
                return rightNode;
            }

            if (node.right == null) {
                LinkedBinaryNode leftNode = node.left;
                node.left = null;
                return leftNode;
            }

            LinkedBinaryNode successor = minimumNode(node.right);
            successor.right = removeMin0(node.right);
            successor.left = node.left;

            node.left = null;
            node.right = null;

            return successor;
        }
    }

    private void validateKeyValue(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key cannot be null");
        }
    }

    private int compare(K k1, K k2) {
        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
                comparator.compare(k1, k2);
    }

    private class LinkedBinaryNode {
        K key;
        V value;
        LinkedBinaryNode left;
        LinkedBinaryNode right;

        private LinkedBinaryNode(K key, V value) {
            this(key, value, null, null);
        }

        private LinkedBinaryNode(K key, V value, LinkedBinaryNode left, LinkedBinaryNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }


    public static void main(String[] args) {
        LinkedBinarySearchTree<Integer, Integer> linkedBinarySearchTree = new LinkedBinarySearchTree<>();
        linkedBinarySearchTree.add(2, 2);
        linkedBinarySearchTree.add(1, 1);
        linkedBinarySearchTree.add(3, 3);
        linkedBinarySearchTree.add(10, 10);
        linkedBinarySearchTree.add(5, 5);
        linkedBinarySearchTree.add(4, 4);

        linkedBinarySearchTree.preOrderNoRecursion();
        System.out.println();
        linkedBinarySearchTree.inOrderNoRecursion();
        System.out.println();
        linkedBinarySearchTree.postOrderNoRecursion();
        System.out.println();
        linkedBinarySearchTree.levelOrder();
    }
}
