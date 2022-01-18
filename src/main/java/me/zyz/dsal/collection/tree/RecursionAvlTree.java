//package me.zyz.dsal.collection.tree;
//
///**
// * @author yz
// */
//public final class RecursionAvlTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, RecursionAvlTree.RecursionAvlNode<K, V>> {
//
//    private int height(RecursionAvlNode<K, V> node) {
//        if (node == null) {
//            return 0;
//        }
//
//        return node.height();
//    }
//
//    private int balanceFactor(RecursionAvlNode<K, V> node) {
//        if (node == null) {
//            return 0;
//        }
//
//        return height(node.left) - height(node.right);
//    }
//
//    @Override
//    public void add(K key, V value) {
//        root = add(root, throwIfNullKey(key), value);
//        size++;
//    }
//
//    private RecursionAvlNode<K, V> add(RecursionAvlNode<K, V> root, K key, V value) {
//        if (root == null) {
//            return new RecursionAvlNode<>(key, value);
//        }
//
//        int comparedNum = compareKey(key, root.key);
//
//        if (comparedNum < 0) {
//            root.left = add(root.left, key, value);
//        } else if (comparedNum > 0) {
//            root.right = add(root.right, key, value);
//        } else {
//            root.value = value;
//            return root;
//        }
//
//        int balanceFactor = balanceFactor(root);
//
//        if (balanceFactor == 2) {
//            int leftBalanceFactor = balanceFactor(root.left);
//
//            if (leftBalanceFactor == -1) {
//                root.left = leftRotate(root.left);
//            }
//            return rightRotate(root);
//        } else if (balanceFactor == -2) {
//            int rightBalanceFactor = balanceFactor(root.right);
//
//            if (rightBalanceFactor == 1) {
//                root.right = rightRotate(root.right);
//            }
//            return leftRotate(root);
//        } else {
//            root.setHeight(Math.max(height(root.left), height(root.right)) + 1);
//        }
//
//        return root;
//    }
//
//    private RecursionAvlNode<K, V> rightRotate(RecursionAvlNode<K, V> oldRoot) {
//        RecursionAvlNode<K, V> newRoot = oldRoot.left;
//        RecursionAvlNode<K, V> tree = newRoot.right;
//
//        newRoot.right = oldRoot;
//        oldRoot.left = tree;
//
//        oldRoot.setHeight(Math.max(height(oldRoot.left), height(oldRoot.right)) + 1);
//        newRoot.setHeight(Math.max(height(newRoot.left), height(newRoot.right)) + 1);
//
//        return newRoot;
//    }
//
//    private RecursionAvlNode<K, V> leftRotate(RecursionAvlNode<K, V> oldRoot) {
//        RecursionAvlNode<K, V> newRoot = oldRoot.right;
//        RecursionAvlNode<K, V> tree = newRoot.left;
//
//        newRoot.left = oldRoot;
//        oldRoot.right = tree;
//
//        oldRoot.setHeight(Math.max(height(oldRoot.left), height(oldRoot.right)) + 1);
//        newRoot.setHeight(Math.max(height(newRoot.left), height(newRoot.right)) + 1);
//
//        return newRoot;
//    }
//
//    static class RecursionAvlNode<K, V> extends AbstractBinaryNode<K, V, RecursionAvlNode<K, V>> {
//        private int height;
//
//        RecursionAvlNode(K key, V value) {
//            super(key, value);
//            this.height = 1;
//        }
//
//        int height() {
//            return this.height;
//        }
//
//        void setHeight(int height) {
//            this.height = height;
//        }
//    }
//}
