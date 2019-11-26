package me.zyz.dsal.collection.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author yezhou
 */
@Slf4j
public abstract class AbstractLinkedBinarySearchTree<K, V> implements LinkedBinarySearchTree<K, V> {
    private final Comparator<K> comparator;

    DefaultBinaryNode<K, V> root;

    AbstractLinkedBinarySearchTree(Comparator<K> comparator) {
        this(null, comparator);
    }

    AbstractLinkedBinarySearchTree(DefaultBinaryNode<K, V> root) {
        this(root, null);
    }

    AbstractLinkedBinarySearchTree(DefaultBinaryNode<K, V> root, Comparator<K> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    DefaultBinaryNode<K, V> leftNode(DefaultBinaryNode<K, V> node) {
        return node.left();
    }

    void setLeft(DefaultBinaryNode<K, V> node, DefaultBinaryNode<K, V> newLeft) {
        node.left(newLeft);
    }

    DefaultBinaryNode<K, V> rightNode(DefaultBinaryNode<K, V> node) {
        return node.right();
    }

    void setRight(DefaultBinaryNode<K, V> node, DefaultBinaryNode<K, V> newRight) {
        node.right(newRight);
    }

    void preOrder() {
        preOrder0(root);
    }

    void preOrder0(DefaultBinaryNode<K, V> root) {
        if (root == null) {
            return;
        }

        log.info("Key: {}, Value: {}", root.key(), root.value());
        preOrder0(leftNode(root));
        preOrder0(rightNode(root));
    }

    void inOrder() {
        inOrder0(root);
    }

    void inOrder0(DefaultBinaryNode<K, V> root) {
        if (root == null) {
            return;
        }

        inOrder0(leftNode(root));
        log.info("Key: {}, Value: {}", root.key(), root.value());
        inOrder0(rightNode(root));
    }

    void postOrder() {
        postOrder0(root);
    }

    void postOrder0(DefaultBinaryNode<K, V> root) {
        if (root == null) {
            return;
        }

        postOrder0(leftNode(root));
        postOrder0(rightNode(root));
        log.info("Key: {}, Value: {}", root.key(), root.value());
    }

    void levelOrder() {
        levelOrder0(root);
    }

    void levelOrder0(DefaultBinaryNode<K, V> root) {
        Queue<DefaultBinaryNode<K, V>> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            DefaultBinaryNode<K, V> currentNode = levelOrderQueue.remove();
            log.info("Key: {}, Value: {}", root.key(), root.value());

            if (leftNode(currentNode) != null) {
                levelOrderQueue.add(leftNode(currentNode));
            }
            if (rightNode(currentNode) != null) {
                levelOrderQueue.add(rightNode(currentNode));
            }
        }
    }

    void preOrderNR() {
        preOrderNR0(root);
    }

    void preOrderNR0(DefaultBinaryNode<K, V> root) {
        Deque<DefaultBinaryNode<K, V>> preOrderStack = new LinkedList<>();
        preOrderStack.push(root);
        while (!preOrderStack.isEmpty()) {
            DefaultBinaryNode<K, V> currentNode = preOrderStack.pop();

            if (rightNode(currentNode) != null) {
                preOrderStack.push(rightNode(currentNode));
            }

            if (leftNode(currentNode) != null) {
                preOrderStack.push(leftNode(currentNode));
            }

            log.info("Key: {}, Value: {}", currentNode.key(), currentNode.value());
        }
    }

    void inOrderNR() {
        inOrderNR0(root);
    }

    void inOrderNR0(DefaultBinaryNode<K, V> root) {
        Deque<DefaultBinaryNode<K, V>> inOrderStack = new LinkedList<>();

        DefaultBinaryNode<K, V> currentNode = root;
        while (!inOrderStack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                inOrderStack.push(currentNode);
                currentNode = leftNode(currentNode);
            } else {
                DefaultBinaryNode<K, V> node = inOrderStack.pop();
                currentNode = rightNode(node);

                log.info("Key: {}, Value: {}", currentNode.key(), currentNode.value());
            }
        }
    }

    void postOrderNR() {
        preOrderNR0(root);
    }

    void postOrderNR0(DefaultBinaryNode<K, V> root) {
        Deque<DefaultBinaryNode<K, V>> postOrderStack = new LinkedList<>();
        DefaultBinaryNode<K, V> cur;
        DefaultBinaryNode<K, V> pre = null;

        postOrderStack.push(root);
        while (!postOrderStack.isEmpty()) {
            cur = postOrderStack.peek();
            if ((leftNode(cur) == null && rightNode(cur) == null) ||
                    (pre != null && (pre == leftNode(cur) || pre == rightNode(cur)))) {
                postOrderStack.pop();
                pre = cur;

                log.info("Key: {}, Value: {}", root.key(), root.value());
            } else {
                if (rightNode(cur) != null) {
                    postOrderStack.push(rightNode(cur));
                }
                if (leftNode(cur) != null) {
                    postOrderStack.push(leftNode(cur));
                }
            }
        }
    }

    private void anyOrderNoRecursionBase(DefaultBinaryNode<K, V> node) {
        Deque<DefaultBinaryNode<K, V>> stack = new LinkedList<>();
        Set<DefaultBinaryNode<K, V>> set = new HashSet<>();
        Set<DefaultBinaryNode<K, V>> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            DefaultBinaryNode<K, V> head = stack.peek();

//            pre
//            if (accessed.add(head)) {
//            }

            if (leftNode(head) != null && set.add(leftNode(head))) {
                stack.push(leftNode(head));
                continue;
            }

//            in
//            if (accessed.add(head)) {
//            }

            if (rightNode(head) != null && set.add(rightNode(head))) {
                stack.push(rightNode(head));
                continue;
            }

//            post
//            if (accessed.add(head)) {
//            }

            stack.pop();
        }
    }

    public K minimum() {
        DefaultBinaryNode<K, V> minimumNode = minimumNode(root);

        if (minimumNode == null) {
            throw new NoSuchElementException();
        }

        return minimumNode.key();
    }

    public K maximum() {
        DefaultBinaryNode<K, V> maximumNode = maximumNode(root);

        if (maximumNode == null) {
            throw new NoSuchElementException();
        }

        return maximumNode.key();
    }

    DefaultBinaryNode<K, V> minimumNode(DefaultBinaryNode<K, V> node) {
        if (node == null) {
            return null;
        }

        if (leftNode(node) == null) {
            return node;
        }

        return minimumNode(leftNode(node));
    }

    DefaultBinaryNode<K, V> maximumNode(DefaultBinaryNode<K, V> node) {
        if (node == null) {
            return null;
        }

        if (rightNode(node) == null) {
            return node;
        }

        return maximumNode(rightNode(node));
    }

    @Override
    public V get(K key) {
        DefaultBinaryNode<K, V> node = getNode(root, key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.value;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    private DefaultBinaryNode<K, V> getNode(DefaultBinaryNode<K, V> root, K k) {
        if (root == null) {
            return null;
        }

        int compareNumber = compare(k, root.key());
        if (compareNumber < 0) {
            return getNode(leftNode(root), k);
        } else if (compareNumber > 0) {
            return getNode(rightNode(root), k);
        } else {
            return root;
        }
    }

    int compare(K k1, K k2) {
        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
                comparator.compare(k1, k2);
    }

    static class DefaultBinaryNode<K, V> implements BinaryNode<K, V> {
        private K key;
        private V value;
        private DefaultBinaryNode<K, V> left;
        private DefaultBinaryNode<K, V> right;

        DefaultBinaryNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
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
        public DefaultBinaryNode<K, V> left() {
            return left;
        }

        @Override
        public DefaultBinaryNode<K, V> right() {
            return right;
        }

        public void left(DefaultBinaryNode<K, V> newLeft) {
            this.left = newLeft;
        }

        public void right(DefaultBinaryNode<K, V> newRight) {
            this.right = newRight;
        }
    }
}
