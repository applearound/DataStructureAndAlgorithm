package me.zyz.dsal.collection.tree;

import java.util.*;

/**
 * @author yz
 */
public class DefaultLinkedBinarySearchTree<K, V> extends AbstractLinkedBinarySearchTree<K, V> {
//    private DefaultBinaryNode<K, V> root;
    private int size;

    @Override
    DefaultBinaryNode<K, V> root() {
        return this.root;
    }

    public DefaultLinkedBinarySearchTree() {
        this(null);
    }

    private DefaultLinkedBinarySearchTree(Comparator<K> comparator) {
        super(comparator);
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
            root.left = add0(root.left, addingNode);
        } else if (compareNumber > 0) {
            root.right = add0(root.right, addingNode);
        }

        return root;
    }

    private void add1(DefaultBinaryNode<K, V> root, DefaultBinaryNode<K, V> addingNode) {
        int compareNumber = compare(addingNode.key(), root.key());

        if (compareNumber < 0) {
            if (((AbstractBinaryNode<K, V>) root).left() == null) {
                root.left = addingNode;
                return;
            }

            add1(root.left, addingNode);
        } else if (compareNumber > 0) {
            if (((AbstractBinaryNode<K, V>) root).right() == null) {
                root.right = addingNode;
                return;
            }

            add1(root.right, addingNode);
        }
    }

    public K removeMin() {
        K ret = minimum();

        removeMin0(root);
        size--;

        return ret;
    }

    private DefaultBinaryNode<K, V> removeMin0(DefaultBinaryNode<K, V> node) {
        if (((AbstractBinaryNode<K, V>) node).left() == null) {
            DefaultBinaryNode<K, V> rightNode = node.right;
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

    private DefaultBinaryNode<K, V> removeMax0(DefaultBinaryNode<K, V> node) {
        if (((AbstractBinaryNode<K, V>) node).right() == null) {
            DefaultBinaryNode<K, V> leftNode = node.left;
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

    private DefaultBinaryNode<K, V> remove0(DefaultBinaryNode<K, V> node, K k) {
        // 空树返回空
        if (node == null) {
            return null;
        }

        int compareNumber = compare(k, node.key());

        if (compareNumber < 0) {
            node.left = remove0(node.left, k);
            return node;
        } else if (compareNumber > 0) {
            node.right = remove0(node.right, k);
            return node;
        } else {
            if (((AbstractBinaryNode<K, V>) node).left() == null) {
                DefaultBinaryNode<K, V> rightNode = node.right;
                node.right = null;
                return rightNode;
            }

            if (((AbstractBinaryNode<K, V>) node).right() == null) {
                DefaultBinaryNode<K, V> leftNode = node.left;
                node.left = null;
                return leftNode;
            }

            DefaultBinaryNode<K, V> successor = minimumNode(node.right);
            successor.right = removeMin0(node.right());
            successor.left = node.left;

            node.left = null;
            node.right = null;

            return successor;
        }
    }



    private static class DefaultBinaryNode<K, V> extends AbstractBinaryNode<K, V, DefaultBinaryNode<K, V>> {
        DefaultBinaryNode(K key, V value) {
            super(key, value);
        }
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
