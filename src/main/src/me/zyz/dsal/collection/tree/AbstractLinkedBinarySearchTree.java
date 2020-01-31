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
    protected final N nil = null;

    protected AbstractLinkedBinarySearchTree() {
        this(null);
    }

    protected AbstractLinkedBinarySearchTree(Comparator<K> comparator) {
        this.root = nil();
        this.size = 0;

        this.comparator = comparator;
    }

    K validateKey(K key) {
        return Objects.requireNonNull(key, "key");
    }

    N nil() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V findValue(K key) {
        N node = searchNode(root, key);

        return node == nil() ? null : node.value();
    }

    @Override
    public V getValue(K key) {
        N node = searchNode(root, key);
        if (node == nil()) {
            throw new NoSuchElementException();
        }
        return node.value();
    }

    @Override
    public boolean contains(K key) {
        return contains0(root, key);
    }

    private boolean contains0(N root, K key) {
        if (root == nil()) {
            return false;
        }

        int compareNumber = compareKey(key, root.key());
        if (compareNumber < 0) {
            return contains0(root.left(), key);
        } else if (compareNumber > 0) {
            return contains0(root.right(), key);
        } else {
            return true;
        }
    }

    void preOrder() {
        preOrder0(root);
    }

    private void preOrder0(N root) {
        if (root == nil()) {
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
        if (root == nil()) {
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
        if (root == nil()) {
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

    void preOrderNr() {
        preOrderNr0(root);
    }

    private void preOrderNr0(N root) {
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

    void inOrderNr() {
        inOrderNr0(root);
    }

    private void inOrderNr0(N root) {
        Deque<N> inOrderStack = new LinkedList<>();

        N currentNode = root;
        while (!inOrderStack.isEmpty() || currentNode != nil()) {
            if (currentNode != nil()) {
                inOrderStack.push(currentNode);
                currentNode = currentNode.left();
            } else {
                N node = inOrderStack.pop();
                log.info("Key: {}, Value: {}", node.key(), node.value());

                currentNode = node.right();
            }
        }
    }

    void postOrderNr() {
        postOrderNr0(root);
    }

    private void postOrderNr0(N root) {
        Deque<N> postOrderStack = new LinkedList<>();
        N cur;
        N pre = nil();

        postOrderStack.push(root);
        while (!postOrderStack.isEmpty()) {
            cur = postOrderStack.peek();
            if ((cur.noLeft() && cur.noRight()) ||
                    (pre != nil() && (pre == cur.left() || pre == cur.right()))) {
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

        return minimumNode == nil() ? null : minimumNode.key();
    }

    public K maximumKey() {
        N maximumNode = maximumNode(root);
        return maximumNode == nil() ? null : maximumNode.key();
    }

    N minimumNode(N root) {
        N minNode = nil();

        N n = root;
        while (n != nil()) {
            minNode = n;
            n = n.left();
        }

        return minNode;
    }

    N maximumNode(N root) {
        N maxNode = nil();

        N n = root;
        while (n != nil()) {
            maxNode = n;
            n = n.right();
        }

        return maxNode;
    }

    int compareKey(K k1, K k2) {
        return comparator == null ? ((Comparable<K>) k1).compareTo(k2) :
                comparator.compare(k1, k2);
    }

    private N searchNode(N root, K k) {
        if (root == nil()) {
            return nil();
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
