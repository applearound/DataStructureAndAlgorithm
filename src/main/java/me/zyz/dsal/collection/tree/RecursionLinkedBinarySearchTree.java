//package me.zyz.dsal.collection.tree;
//
//import java.util.Objects;
//import java.util.Optional;
//
///**
// * @author yz
// */
//public abstract class RecursionLinkedBinarySearchTree<K, N extends RecursionLinkedBinarySearchTree<K, N>.DefaultBinaryNode<K, N>> extends AbstractLinkedBinarySearchTree<K, N> {
//    public N delete(final K key) {
//        Objects.requireNonNull(key, "key");
//
//        root = remove0(root, key);
//        size--;
//
//        return root;
//    }
//
//    public Optional<N> removeMin() {
//        final Wrapper minWrapper = new Wrapper();
//
//        root = removeMin0(root, minWrapper);
//
//        if (isNilNode(minWrapper.wrapped)) {
//            return Optional.empty();
//        } else {
//            return Optional.of(minWrapper.wrapped);
//        }
//    }
//
//    public Optional<N> removeMax() {
//        final Wrapper maxWrapper = new Wrapper();
//
//        root = removeMax0(root, maxWrapper);
//
//        if (isNilNode(maxWrapper.wrapped)) {
//            return Optional.empty();
//        } else {
//            return Optional.of(maxWrapper.wrapped);
//        }
//    }
//
//    protected void add(final N node) {
//        Objects.requireNonNull(node, "node");
//
//        if (isNilNode(root)) {
//            root = node;
//        } else {
//            add0(root, node);
//        }
//
//        size++;
//    }
//
//    private void add0(final N root, final N newNode) {
//        final int cmp = compareKey(newNode.key, root.key);
//
//        if (cmp < 0) {
//            if (isNilNode(root.left)) {
//                root.left = newNode;
//                return;
//            }
//
//            add0(root.left, newNode);
//        } else if (cmp > 0) {
//            if (isNilNode(root.right)) {
//                root.right = newNode;
//                return;
//            }
//
//            add0(root.right, newNode);
//        }
//    }
//
//    private N removeMin0(final N root, final Wrapper minWrapper) {
//        if (isNilNode(root.left)) {
//            if (minWrapper != null) {
//                minWrapper.wrapped = root;
//            }
//            return root.right;
//        }
//
//        root.left = removeMin0(root.left, minWrapper);
//
//        return root;
//    }
//
//    private N removeMax0(final N root, final Wrapper maxWrapper) {
//        if (isNilNode(root.right)) {
//            if (maxWrapper != null) {
//                maxWrapper.wrapped = root;
//            }
//            return root.left;
//        }
//
//        root.right = removeMax0(root.right, maxWrapper);
//
//        return root;
//    }
//
//    private N remove0(final N node, final K k) {
//        if (isNilNode(node)) {
//            return null;
//        }
//
//        int compareNumber = compareKey(k, node.key);
//
//        if (compareNumber < 0) {
//            node.left = remove0(node.left, k);
//            return node;
//        } else if (compareNumber > 0) {
//            node.right = remove0(node.right, k);
//            return node;
//        } else {
//            if (isNilNode(node.left)) {
//                final N rightNode = node.right;
//                node.right = null;
//                return rightNode;
//            }
//
//            if (isNilNode(node.right)) {
//                final N leftNode = node.left;
//                node.left = null;
//                return leftNode;
//            }
//
//            final N successor = minimumNode(node.right);
//            successor.right = removeMin0(node.right, null);
//            successor.left = node.left;
//
//            return successor;
//        }
//    }
//
//    protected abstract class DefaultBinaryNode<K, N extends DefaultBinaryNode<K, N>> extends AbstractBinaryNode<K, N> {
//        protected DefaultBinaryNode(final K key) {
//            super(key);
//        }
//    }
//
//    private class Wrapper {
//        private N wrapped;
//    }
//}
