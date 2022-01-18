//package me.zyz.dsal.collection.tree;
//
///**
// * @author yezhou
// */
//public class AvlTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, AvlTree.AvlNode<K, V>> {
//
//    public AvlTree() {
//        root = null;
//    }
//
//    public void add(K key, V value) {
//        add0(throwIfNullKey(key), value);
//        size++;
//    }
//
//    private void add0(K key, V value) {
//        AvlNode<K, V> trueParent = null;
//        AvlNode<K, V> tempNode = root;
//        while (tempNode != null) {
//            trueParent = tempNode;
//            int compareNum = compareKey(key, tempNode.key);
//            if (compareNum < 0) {
//                tempNode = tempNode.left;
//            } else if (compareNum > 0) {
//                tempNode = tempNode.right;
//            } else {
//                tempNode.value = value;
//                return;
//            }
//        }
//
//        AvlNode<K, V> newNode = new AvlNode<>(key, value);
//        newNode.parent = trueParent;
//        if (trueParent == null) {
//            root = newNode;
//        } else if (compareKey(newNode.key, trueParent.key) < 0) {
//            trueParent.left = newNode;
//        } else {
//            trueParent.right = newNode;
//        }
//
//        newNode.left = null;
//        newNode.right = null;
//        newNode.height = 1;
//
//        AvlNode<K, V> parent = newNode.parent;
//        while (parent != null) {
//            if (!parent.updateHeight()) {
//                break;
//            }
//
//            int balanceFactor = parent.balanceFactor();
//            if (balanceFactor == 2) {
//                if (parent.left.balanceFactor() == -1) {
//                    leftRotate(parent.left);
//                }
//                rightRotate(parent);
//                break;
//            } else if (balanceFactor == -2) {
//                if (parent.right.balanceFactor() == 1) {
//                    rightRotate(parent.right);
//                }
//                leftRotate(parent);
//                break;
//            }
//
//            parent = parent.parent;
//        }
//    }
//
//    private void rightRotate(AvlNode<K, V> oldRoot) {
//        AvlNode<K, V> newRoot = oldRoot.left;
//        oldRoot.left = newRoot.right;
//        if (newRoot.hasRight()) {
//            newRoot.right.parent = oldRoot;
//        }
//
//        AvlNode<K, V> parent = oldRoot.parent;
//        newRoot.parent = parent;
//        if (parent == null) {
//            root = newRoot;
//        } else if (oldRoot == parent.left) {
//            parent.left = newRoot;
//        } else {
//            parent.right = newRoot;
//        }
//
//        newRoot.right = oldRoot;
//        oldRoot.parent = newRoot;
//
//        oldRoot.updateHeight();
//        newRoot.updateHeight();
//    }
//
//    private void leftRotate(AvlNode<K, V> oldRoot) {
//        AvlNode<K, V> newRoot = oldRoot.right;
//        oldRoot.right = newRoot.left;
//        if (newRoot.hasLeft()) {
//            newRoot.left.parent = oldRoot;
//        }
//
//        AvlNode<K, V> parent = oldRoot.parent;
//        newRoot.parent = parent;
//        if (parent == null) {
//            root = newRoot;
//        } else if (oldRoot == parent.left) {
//            parent.left = newRoot;
//        } else {
//            parent.right = newRoot;
//        }
//
//        newRoot.left = oldRoot;
//        oldRoot.parent = newRoot;
//
//        oldRoot.updateHeight();
//        newRoot.updateHeight();
//    }
//
//    static class AvlNode<K, V> extends AbstractBinaryNode<K, V, AvlTree.AvlNode<K, V>> {
//        private int height;
//        private AvlNode<K, V> parent;
//
//        AvlNode(K key, V value) {
//            super(key, value);
//            this.height = 1;
//        }
//
//        int balanceFactor() {
//            int leftHeight = left == null ? 0 : left.height;
//            int rightHeight = right == null ? 0 : right.height;
//            return leftHeight - rightHeight;
//        }
//
//        boolean updateHeight() {
//            int oldHeight = height;
//            height = Math.max(left == null ? 0 : left.height, right == null ? 0 : right.height) + 1;
//
//            return oldHeight != height;
//        }
//    }
//}
