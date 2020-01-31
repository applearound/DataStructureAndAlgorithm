package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public class AvlTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, AvlTree.AvlNode<K, V>> {

    public AvlTree() {
        root = null;
    }

    @Override
    public void add(K key, V value) {
        add0(validateKey(key), value);
        size++;
    }

    private void add0(K key, V value) {
        AvlNode<K, V> trueParent = null;
        AvlNode<K, V> tempNode = root;
        while (tempNode != null) {
            trueParent = tempNode;
            int compareNum = compareKey(key, tempNode.key());
            if (compareNum < 0) {
                tempNode = tempNode.left();
            } else if (compareNum > 0) {
                tempNode = tempNode.right();
            } else {
                tempNode.setValue(value);
                return;
            }
        }

        AvlNode<K, V> newNode = new AvlNode<>(key, value);
        newNode.setParent(trueParent);
        if (trueParent == null) {
            root = newNode;
        } else if (compareKey(newNode.key(), trueParent.key()) < 0) {
            trueParent.setLeft(newNode);
        } else {
            trueParent.setRight(newNode);
        }

        newNode.setLeft(null);
        newNode.setRight(null);
        newNode.setHeight(1);

        AvlNode<K, V> parent = newNode.parent();
        while (parent != null) {
            if (!parent.updateHeight()) {
                break;
            }

            int balanceFactor = parent.balanceFactor();
            if (balanceFactor == 2) {
                if (parent.left().balanceFactor() == -1) {
                    leftRotate(parent.left());
                }
                rightRotate(parent);
                break;
            } else if (balanceFactor == -2) {
                if (parent.right().balanceFactor() == 1) {
                    rightRotate(parent.right());
                }
                leftRotate(parent);
                break;
            }

            parent = parent.parent();
        }
    }

    private void rightRotate(AvlNode<K, V> oldRoot) {
        AvlNode<K, V> newRoot = oldRoot.left();
        oldRoot.setLeft(newRoot.right());
        if (newRoot.hasRight()) {
            newRoot.right().setParent(oldRoot);
        }

        AvlNode<K, V> parent = oldRoot.parent();
        newRoot.setParent(parent);
        if (parent == null) {
            root = newRoot;
        } else if (oldRoot == parent.left()) {
            parent.setLeft(newRoot);
        } else {
            parent.setRight(newRoot);
        }

        newRoot.setRight(oldRoot);
        oldRoot.setParent(newRoot);

        oldRoot.updateHeight();
        newRoot.updateHeight();
    }

    private void leftRotate(AvlNode<K, V> oldRoot) {
        AvlNode<K, V> newRoot = oldRoot.right();
        oldRoot.setRight(newRoot.left());
        if (newRoot.hasLeft()) {
            newRoot.left().setParent(oldRoot);
        }

        AvlNode<K, V> parent = oldRoot.parent();
        newRoot.setParent(parent);
        if (parent == null) {
            root = newRoot;
        } else if (oldRoot == parent.left()) {
            parent.setLeft(newRoot);
        } else {
            parent.setRight(newRoot);
        }

        newRoot.setLeft(oldRoot);
        oldRoot.setParent(newRoot);

        oldRoot.updateHeight();
        newRoot.updateHeight();
    }

    static class AvlNode<K, V> extends AbstractBinaryNode<K, V, AvlTree.AvlNode<K, V>> {
        private int height;

        AvlNode(K key, V value) {
            super(key, value);
            this.height = 1;
        }

        int height() {
            return this.height;
        }

        void setHeight(int height) {
            this.height = height;
        }

        int balanceFactor() {
            int leftHeight = left() == null ? 0 : left().height();
            int rightHeight = right() == null ? 0 : right().height();
            return leftHeight - rightHeight;
        }

        boolean updateHeight() {
            int oldHeight = height;
            height = Math.max(left() == null ? 0 : left().height(), right() == null ? 0 : right().height()) + 1;

            return oldHeight != height;
        }
    }
}
