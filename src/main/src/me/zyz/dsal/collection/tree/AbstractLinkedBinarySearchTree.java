package me.zyz.dsal.collection.tree;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author yezhou
 */
@Slf4j
public abstract class AbstractLinkedBinarySearchTree<K, V, N extends AbstractBinaryNode<K, V, N>> implements BinarySearchTree<K, V, N> {
    private final Comparator<K> comparator;
    protected N root;
    protected int size;

    protected AbstractLinkedBinarySearchTree() {
        this(null);
    }

    protected AbstractLinkedBinarySearchTree(Comparator<K> comparator) {
        this.root = null;
        this.size = 0;

        this.comparator = comparator;
    }

    K validateKey(K key) {
        return Objects.requireNonNull(key, "Key cannot be null");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V findValue(K key) {
        N node = searchNode(root, key);
        if (node == null) {
            return null;
        } else {
            return node.value();
        }
    }

    @Override
    public V getValue(K key) {
        N node = searchNode(root, key);
        if (node == null) {
            throw new NoSuchElementException();
        }
        return node.value();
    }

    @Override
    public boolean contains(K key) {
        return searchNode(root, key) != null;
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

            if (currentNode.hasLeft()) {
                levelOrderQueue.add(currentNode.left());
            }
            if (currentNode.hasRight()) {
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

            if (currentNode.hasRight()) {
                preOrderStack.push(currentNode.right());
            }

            if (currentNode.hasLeft()) {
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
            if ((cur.noLeft() && cur.noRight()) ||
                    (pre != null && (pre == cur.left() || pre == cur.right()))) {
                postOrderStack.pop();

                log.info("Key: {}, Value: {}", cur.key(), cur.value());

                pre = cur;
            } else {
                if (cur.hasRight()) {
                    postOrderStack.push(cur.right());
                }
                if (cur.hasLeft()) {
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

            if (head.hasLeft() && set.add(head.left())) {
                stack.push(head.left());
                continue;
            }

//            in
//            if (accessed.add(head)) {
//            }

            if (head.hasRight() && set.add(head.right())) {
                stack.push(head.right());
                continue;
            }

//            post
//            if (accessed.add(head)) {
//            }

            stack.pop();
        }
    }

    public K minimumKey() {
        N minimumNode = minimumNode(root);

        if (minimumNode == null) {
            return null;
        } else {
            return minimumNode.key();
        }
    }

    public K maximumKey() {
        N maximumNode = maximumNode(root);

        if (maximumNode == null) {
            return null;
        } else {
            return maximumNode.key();
        }
    }

    N minimumNode(N root) {
        if (root == null) {
            return null;
        }

        if (root.noLeft()) {
            return root;
        }

        return minimumNode(root.left());
    }

    N maximumNode(N root) {
        if (root == null) {
            return null;
        }

        if (root.noRight()) {
            return root;
        }

        return maximumNode(root.right());
    }

    int compareKey(K k1, K k2) {
        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
                comparator.compare(k1, k2);
    }

    private N searchNode(N root, K k) {
        if (root == null) {
            return null;
        }

        int compareNumber = compareKey(k, root.key());
        if (compareNumber < 0) {
            return searchNode(root.left(), k);
        } else if (compareNumber > 0) {
            return searchNode(root.right(), k);
        } else {
            return root;
        }
    }
}
