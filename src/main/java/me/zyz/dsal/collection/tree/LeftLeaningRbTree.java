//package me.zyz.dsal.collection.tree;
//
///**
// * 1. 每个节点不是红节点就是黑节点
// * 2. 根节点是黑节点
// * 3. 叶子节点（空节点）是黑节点
// * 4. 如果一个节点是红节点，那它的孩子一定是黑节点
// * 5. 从任一节点出发通向任意叶子节点的简单路径，经过的黑节点数量相同
// *
// * @author zyz
// */
//public class LeftLeaningRbTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, LeftLeaningRbTree.RbNode<K, V>> {
//    public static final boolean RED = true;
//    public static final boolean BLACK = false;
//
//    @Override
//    public void add(K key, V value) {
//        root = add0(root, throwIfNullKey(key), value);
//        root.color = BLACK;
//        size++;
//    }
//
//    private RbNode<K, V> add0(RbNode<K, V> root, K key, V value) {
//        if (root == null) {
//            return new RbNode<>(key, value);
//        }
//
//        int i = compareKey(key, root.key);
//        if (i < 0) {
//            root.left = add0(root.left, key, value);
//        } else if (i > 0) {
//            root.right = add0(root.right, key, value);
//        } else {
//            root.value = value;
//        }
//
//        if (isRed(root.right) && isBlack(root.left)) {
//            root = leftRotate(root);
//        }
//        if (isRed(root.left) && isRed(root.left.left)) {
//            root = rightRotate(root);
//        }
//        if (isRed(root.left) && isRed(root.right)) {
//            flipColors(root);
//        }
//
//        return root;
//    }
//
//    private RbNode<K, V> leftRotate(RbNode<K, V> root) {
//        RbNode<K, V> x = root.right;
//
//        root.right = x.left;
//        x.left = root;
//
//        x.color = root.color;
//        root.color = RED;
//
//        return x;
//    }
//
//    private RbNode<K, V> rightRotate(RbNode<K, V> root) {
//        RbNode<K, V> x = root.left;
//
//        root.left = x.right;
//        x.right = root;
//
//        x.color = root.color;
//        root.color = RED;
//
//        return x;
//    }
//
//    private void flipColors(RbNode<K, V> node) {
//        node.color = RED;
//        node.left.color = BLACK;
//        node.right.color = BLACK;
//    }
//
//    private boolean isRed(RbNode node) {
//        return node != null && node.isRed();
//    }
//
//    private boolean isBlack(RbNode node) {
//        return node == null || node.isBlack();
//    }
//
//    static class RbNode<K, V> extends AbstractBinaryNode<K, V, RbNode<K, V>> {
//        private boolean color;
//
//        RbNode(K key, V value) {
//            super(key, value);
//            this.color = RED;
//        }
//
//        boolean isRed() {
//            return color == RED;
//        }
//
//        boolean isBlack() {
//            return color == BLACK;
//        }
//    }
//}
