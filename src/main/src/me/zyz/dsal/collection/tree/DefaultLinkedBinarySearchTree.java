package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class DefaultLinkedBinarySearchTree<K, V> extends AbstractLinkedBinarySearchTree<K, V> {
    private int size;

    public DefaultLinkedBinarySearchTree() {
        this(null);
    }

    public DefaultLinkedBinarySearchTree(Comparator<K> comparator) {
        super(null, comparator);
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
        validateKey(key);
        DefaultBinaryNode<K, V> newNode = new DefaultBinaryNode<>(key, value);
        if (root == null) {
            root = newNode;
        } else {
            add1(root, newNode);
        }
        size++;
    }

    public void anotherAdd(K key, V value) {
        validateKey(key);
        root = add0(root, new DefaultBinaryNode<>(key, value));
        size++;
    }

    private DefaultBinaryNode<K, V> add0(DefaultBinaryNode<K, V> root, DefaultBinaryNode<K, V> addingNode) {
        if (root == null) {
            return addingNode;
        }

        int compareNumber = compare(addingNode.key(), root.key());
        if (compareNumber < 0) {
            setLeft(root, add0(leftNode(root), addingNode));
        } else if (compareNumber > 0) {
            setRight(root, add0(rightNode(root), addingNode));
        }

        return root;
    }

    private void add1(DefaultBinaryNode<K, V> root, DefaultBinaryNode<K, V> addingNode) {
        int compareNumber = compare(addingNode.key(), root.key());

        if (compareNumber < 0) {
            if (leftNode(root) == null) {
                setLeft(root, addingNode);
                return;
            }

            add1(leftNode(root), addingNode);
        } else if (compareNumber > 0) {
            if (rightNode(root) == null) {
                setRight(root, addingNode);
                return;
            }

            add1(rightNode(root), addingNode);
        }
    }

    public K removeMin() {
        K ret = minimum();

        removeMin0(root);
        size--;

        return ret;
    }

    private DefaultBinaryNode<K, V> removeMin0(DefaultBinaryNode<K, V> node) {
        if (leftNode(node) == null) {
            DefaultBinaryNode<K, V> rightNode = rightNode(node);
            setRight(node, null);
            return rightNode;
        }

        setLeft(node, removeMin0(leftNode(node)));
        return node;
    }

    public K removeMax() {
        K ret = maximum();

        removeMax0(root);
        size--;

        return ret;
    }

    private DefaultBinaryNode<K, V> removeMax0(DefaultBinaryNode<K, V> node) {
        if (rightNode(node) == null) {
            DefaultBinaryNode<K, V> leftNode = leftNode(node);
            setLeft(node, null);
            return leftNode;
        }

        setRight(node, removeMax0(rightNode(node)));
        return node;
    }

    public void remove(K k) {
        if (k == null) {
            throw new IllegalArgumentException("null value is not acceptable.");
        }
        root = remove0(root, k);
        size--;
    }

    private DefaultBinaryNode<K, V> remove0(DefaultBinaryNode<K, V> node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = compare(k, node.key());

        if (compareNumber < 0) {
            setLeft(node, remove0(leftNode(node), k));
            return node;
        } else if (compareNumber > 0) {
            setRight(node, remove0(rightNode(node), k));
            return node;
        } else {
            if (leftNode(node) == null) {
                DefaultBinaryNode<K, V> rightNode = rightNode(node);
                setRight(node, null);
                return rightNode;
            }

            if (rightNode(node) == null) {
                DefaultBinaryNode<K, V> leftNode = leftNode(node);
                setLeft(node, null);
                return leftNode;
            }

            DefaultBinaryNode<K, V> successor = minimumNode(rightNode(node));
            setRight(successor, removeMin0(rightNode(node)));
            setLeft(successor, leftNode(node));

            setLeft(node, null);
            setRight(node, null);

            return successor;
        }
    }

    private K validateKey(K key) {
        return Objects.requireNonNull(key, "Key cannot be null");
    }

    public static void main(String[] args) {
        DefaultLinkedBinarySearchTree<Integer, Integer> defaultLinkedBinarySearchTree = new DefaultLinkedBinarySearchTree<>();
        defaultLinkedBinarySearchTree.add(2, 2);
        defaultLinkedBinarySearchTree.add(1, 1);
        defaultLinkedBinarySearchTree.add(3, 3);
        defaultLinkedBinarySearchTree.add(10, 10);
        defaultLinkedBinarySearchTree.add(5, 5);
        defaultLinkedBinarySearchTree.add(4, 4);

        defaultLinkedBinarySearchTree.preOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.inOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.postOrderNR();
        System.out.println();
        defaultLinkedBinarySearchTree.levelOrder();
    }
}
