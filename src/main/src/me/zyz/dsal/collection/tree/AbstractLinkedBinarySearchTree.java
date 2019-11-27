package me.zyz.dsal.collection.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author yezhou
 */
@Slf4j
public abstract class AbstractLinkedBinarySearchTree<K, V, N extends AbstractLinkedBinarySearchTree.AbstractBinaryNode<K, V, N>> implements BinarySearchTree<K, V, N> {
    private final Comparator<K> comparator;
    N root;

    AbstractLinkedBinarySearchTree() {
        this(null, null);
    }

    AbstractLinkedBinarySearchTree(Comparator<K> comparator) {
        this(null, comparator);
    }

    AbstractLinkedBinarySearchTree(N root) {
        this(root, null);
    }

    AbstractLinkedBinarySearchTree(N root, Comparator<K> comparator) {
        this.root = root;
        this.comparator = comparator;
    }

    K validateKey(K key) {
        return Objects.requireNonNull(key, "Key cannot be null");
    }

    void preOrder() {
        preOrder0(root);
    }

    private void preOrder0(N root) {
        if (root == null) {
            return;
        }

        log.info("Key: {}, Value: {}", root.key(), root.value());
        preOrder0(root.left());
        preOrder0(root.right());
    }

    void inOrder() {
        inOrder0(root);
    }

    private void inOrder0(N root) {
        if (root == null) {
            return;
        }

        inOrder0(root.left());
        log.info("Key: {}, Value: {}", root.key(), root.value());
        inOrder0(root.right());
    }

    void postOrder() {
        postOrder0(root);
    }

    private void postOrder0(N root) {
        if (root == null) {
            return;
        }

        postOrder0(root.left());
        postOrder0(root.right());
        log.info("Key: {}, Value: {}", root.key(), root.value());
    }

    void levelOrder() {
        levelOrder0(root);
    }

    private void levelOrder0(N root) {
        Queue<N> levelOrderQueue = new LinkedList<>();
        levelOrderQueue.add(root);
        while (!levelOrderQueue.isEmpty()) {
            N currentNode = levelOrderQueue.remove();
            log.info("Key: {}, Value: {}", currentNode.key(), currentNode.value());

            if (currentNode.left() != null) {
                levelOrderQueue.add(currentNode.left());
            }
            if (currentNode.right() != null) {
                levelOrderQueue.add(currentNode.right());
            }
        }
    }

    void preOrderNR() {
        preOrderNR0(root);
    }

    private void preOrderNR0(N root) {
        Deque<N> preOrderStack = new LinkedList<>();
        preOrderStack.push(root);
        while (!preOrderStack.isEmpty()) {
            N currentNode = preOrderStack.pop();

            if (currentNode.right() != null) {
                preOrderStack.push(currentNode.right());
            }

            if (currentNode.left() != null) {
                preOrderStack.push(currentNode.left());
            }

            log.info("Key: {}, Value: {}", currentNode.key(), currentNode.value());
        }
    }

    void inOrderNR() {
        inOrderNR0(root);
    }

    private void inOrderNR0(N root) {
        Deque<N> inOrderStack = new LinkedList<>();

        N currentNode = root;
        while (!inOrderStack.isEmpty() || currentNode != null) {
            if (currentNode != null) {
                inOrderStack.push(currentNode);
                currentNode = currentNode.left();
            } else {
                N node = inOrderStack.pop();
                log.info("Key: {}, Value: {}", node.key(), node.value());

                currentNode = node.right();
            }
        }
    }

    void postOrderNR() {
        postOrderNR0(root);
    }

    private void postOrderNR0(N root) {
        Deque<N> postOrderStack = new LinkedList<>();
        N cur;
        N pre = null;

        postOrderStack.push(root);
        while (!postOrderStack.isEmpty()) {
            cur = postOrderStack.peek();
            if ((cur.left() == null && cur.right() == null) ||
                    (pre != null && (pre == cur.left() || pre == cur.right()))) {
                postOrderStack.pop();

                log.info("Key: {}, Value: {}", cur.key(), cur.value());

                pre = cur;
            } else {
                if (cur.right() != null) {
                    postOrderStack.push(cur.right());
                }
                if (cur.left() != null) {
                    postOrderStack.push(cur.left());
                }
            }
        }
    }

    private void anyOrderNoRecursionBase(N node) {
        Deque<N> stack = new LinkedList<>();
        Set<N> set = new HashSet<>();
        Set<N> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            N head = stack.peek();

//            pre
//            if (accessed.add(head)) {
//            }

            if (head.left() != null && set.add(head.left())) {
                stack.push(head.left());
                continue;
            }

//            in
//            if (accessed.add(head)) {
//            }

            if (head.right() != null && set.add(head.right())) {
                stack.push(head.right());
                continue;
            }

//            post
//            if (accessed.add(head)) {
//            }

            stack.pop();
        }
    }

    public K minimum() {
        N minimumNode = minimumNode(root);

        if (minimumNode == null) {
            throw new NoSuchElementException();
        }

        return minimumNode.key();
    }

    public K maximum() {
        N maximumNode = maximumNode(root);

        if (maximumNode == null) {
            throw new NoSuchElementException();
        }

        return maximumNode.key();
    }

    N minimumNode(N node) {
        if (node == null) {
            return null;
        }

        if (node.left() == null) {
            return node;
        }

        return minimumNode(node.left());
    }

    N maximumNode(N node) {
        if (node == null) {
            return null;
        }

        if (node.right() == null) {
            return node;
        }

        return maximumNode(node.right());
    }

    @Override
    public V findValue(K key) {
        N node = getNode(root, key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.value();
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    private N getNode(N root, K k) {
        if (root == null) {
            return null;
        }

        int compareNumber = compare(k, root.key());
        if (compareNumber < 0) {
            return getNode(root.left(), k);
        } else if (compareNumber > 0) {
            return getNode(root.right(), k);
        } else {
            return root;
        }
    }

    int compare(K k1, K k2) {
        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
                comparator.compare(k1, k2);
    }

    abstract static class AbstractBinaryNode<K, V, N extends AbstractBinaryNode<K, V, N>> implements BinaryNode<K, V, N> {
        private K key;
        private V value;
        private N left;
        private N right;

        AbstractBinaryNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K key() {
            return this.key;
        }

        @Override
        public V value() {
            return this.value;
        }

        @Override
        public N left() {
            return this.left;
        }

        @Override
        public N right() {
            return this.right;
        }

        @Override
        public void setLeft(N node) {
            this.left = node;
        }

        @Override
        public void setRight(N node) {
            this.right = node;
        }
    }
}
