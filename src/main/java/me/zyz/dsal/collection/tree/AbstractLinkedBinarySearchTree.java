//package me.zyz.dsal.collection.tree;
//
//import java.util.*;
//
//public abstract class AbstractLinkedBinarySearchTree<K, N extends AbstractLinkedBinarySearchTree<K, N>.AbstractBinaryNode> {
//    private static final Comparator DEFAULT_COMPARATOR = (final Object k1, final Object k2) -> ((Comparable) k1).compareTo(k2);
//
//    private final Comparator<K> comparator;
//
//    final N nil;
//
//    N root;
//    int size;
//
//    AbstractLinkedBinarySearchTree() {
//        this(null);
//    }
//
//    AbstractLinkedBinarySearchTree(final Comparator<K> comparator) {
//        this.comparator = comparator != null ? comparator : DEFAULT_COMPARATOR;
//
//        this.nil = null;
//
//        this.root = nil;
//        this.size = 0;
//    }
//
//    private void preOrderStr0(final N root, final StringBuilder sb) {
//        if (isNilNode(root)) {
//            return;
//        }
//
//        sb.append(String.format("%s, ", root));
//        preOrderStr0(root.left, sb);
//        preOrderStr0(root.right, sb);
//    }
//
//    private void inOrderStr0(final N root, final StringBuilder sb) {
//        if (isNilNode(root)) {
//            return;
//        }
//
//        inOrderStr0(root.left, sb);
//        sb.append(String.format("%s, ", root));
//        inOrderStr0(root.right, sb);
//    }
//
//    private void postOrderStr0(final N root, final StringBuilder sb) {
//        if (isNilNode(root)) {
//            return;
//        }
//
//        postOrderStr0(root.left, sb);
//        postOrderStr0(root.right, sb);
//        sb.append(String.format("%s, ", root));
//    }
//
//    N minimumNode(final N root) {
//        N minNode = nil;
//
//        N n = root;
//        while (!isNilNode(n)) {
//            minNode = n;
//            n = n.left;
//        }
//
//        return minNode;
//    }
//
//    N maximumNode(final N root) {
//        N maxNode = nil;
//
//        N n = root;
//        while (!isNilNode(n)) {
//            maxNode = n;
//            n = n.right;
//        }
//
//        return maxNode;
//    }
//
//    N searchNode(final N root, final K k) {
//        if (isNilNode(root)) {
//            return nil;
//        }
//
//        int cmp = compareKey(k, root.key);
//        if (cmp < 0) {
//            return searchNode(root.left, k);
//        } else if (cmp > 0) {
//            return searchNode(root.right, k);
//        } else {
//            return root;
//        }
//    }
//
//    int compareKey(final K k1, final K k2) {
//        return comparator.compare(k1, k2);
//    }
//
//    protected boolean isNilNode(final N node) {
//        return node == nil;
//    }
//
//    protected N searchNode(final K key) {
//        return searchNode(root, key);
//    }
//
//    public int size() {
//        return size;
//    }
//
//    public boolean contains(final K key) {
//        return !isNilNode(searchNode(root, key));
//    }
//
//    public Optional<K> minimumKey() {
//        final N minimumNode = minimumNode(root);
//
//        if (isNilNode(minimumNode)) {
//            return Optional.empty();
//        } else {
//            return Optional.of(minimumNode.key);
//        }
//    }
//
//    public Optional<K> maximumKey() {
//        final N maximumNode = maximumNode(root);
//
//        if (isNilNode(maximumNode)) {
//            return Optional.empty();
//        } else {
//            return Optional.of(maximumNode.key);
//        }
//    }
//
//    public Iterable<N> preOrder() {
//        final Deque<N> preOrderStack = new LinkedList<>();
//        preOrderStack.push(root);
//
//        return () -> new Iterator<>() {
//            private final Deque<N> stack = preOrderStack;
//
//            @Override
//            public boolean hasNext() {
//                return !stack.isEmpty();
//            }
//
//            @Override
//            public N next() {
//                final N currentNode = stack.pop();
//
//                if (!isNilNode(currentNode.right)) {
//                    stack.push(currentNode.right);
//                }
//
//                if (!isNilNode(currentNode.left)) {
//                    stack.push(currentNode.left);
//                }
//
//                return currentNode;
//            }
//        };
//    }
//
//    public Iterable<N> inOrder() {
//        return () -> new Iterator<>() {
//            private final Deque<N> stack = new LinkedList<>();
//            private N currentNode = root;
//
//            @Override
//            public boolean hasNext() {
//                return !isNilNode(currentNode) || !stack.isEmpty();
//            }
//
//            @Override
//            public N next() {
//                while (!isNilNode(currentNode)) {
//                    stack.push(currentNode);
//                    currentNode = currentNode.left;
//                }
//
//                final N node = stack.pop();
//
//                currentNode = node.right;
//
//                return node;
//            }
//        };
//    }
//
//    public Iterable<N> postOrder() {
//        final Deque<N> stack0 = new LinkedList<>();
//        stack0.push(root);
//
//        return () -> new Iterator<>() {
//            private final Deque<N> stack = stack0;
//            private N cur;
//            private N pre = nil;
//
//            @Override
//            public boolean hasNext() {
//                return !stack.isEmpty();
//            }
//
//            @Override
//            public N next() {
//                while (!stack.isEmpty()) {
//                    cur = stack.peek();
//                    while ((!isNilNode(cur.left) || !isNilNode(cur.right)) && (isNilNode(pre) || (pre != cur.left && pre != cur.right))) {
//                        if (!isNilNode(cur.right)) {
//                            stack.push(cur.right);
//                        }
//                        if (!isNilNode(cur.left)) {
//                            stack.push(cur.left);
//                        }
//                    }
//                }
//
//                stack.pop();
//                pre = cur;
//
//                return cur;
//            }
//        };
//    }
//
//    public Iterable<N> levelOrder() {
//        final Queue<N> levelOrderQueue = new LinkedList<>();
//        levelOrderQueue.add(root);
//
//        return () -> new Iterator<>() {
//            private final Queue<N> queue = levelOrderQueue;
//
//            @Override
//            public boolean hasNext() {
//                return !queue.isEmpty();
//            }
//
//            @Override
//            public N next() {
//                final N currentNode = queue.remove();
//
//                if (!isNilNode(currentNode.left)) {
//                    queue.add(currentNode.left);
//                }
//
//                if (!isNilNode(currentNode.right)) {
//                    queue.add(currentNode.right);
//                }
//
//                return currentNode;
//            }
//        };
//    }
//
//    public String preOrderString() {
//        final StringBuilder sb = new StringBuilder("{");
//        preOrderStr0(root, sb);
//        if (sb.length() > 1) {
//            sb.delete(sb.length() - 2, sb.length());
//        }
//        sb.append('}');
//        return sb.toString();
//    }
//
//    public String inOrderString() {
//        final StringBuilder sb = new StringBuilder("{");
//        inOrderStr0(root, sb);
//        if (sb.length() > 1) {
//            sb.delete(sb.length() - 2, sb.length());
//        }
//        sb.append('}');
//        return sb.toString();
//    }
//
//    public String postOrderString() {
//        final StringBuilder sb = new StringBuilder("{");
//        postOrderStr0(root, sb);
//        if (sb.length() > 1) {
//            sb.delete(sb.length() - 2, sb.length());
//        }
//        sb.append('}');
//        return sb.toString();
//    }
//
//    abstract class AbstractBinaryNode {
//        final K key;
//        N left;
//        N right;
//
//        AbstractBinaryNode(final K key) {
//            this.key = key;
//            this.left = nil;
//            this.right = nil;
//        }
//
//        @Override
//        public String toString() {
//            return String.format("{Key: %s}", key);
//        }
//    }
//
////    private void anyOrderNoRecursionBase(final N node) {
////        final Deque<N> stack = new LinkedList<>();
////        final Set<N> set = new HashSet<>();
////        final Set<N> accessed = new HashSet<>();
////
////        stack.push(node);
////
////        while (!stack.isEmpty()) {
////            N head = stack.peek();
////
//////            pre
////            if (accessed.add(head)) {
////            }
////
////            if (!isNilNode(head.left) && set.add(head.left)) {
////                stack.push(head.left);
////                continue;
////            }
////
//////            in
////            if (accessed.add(head)) {
////            }
////
////            if (!isNilNode(head.right) && set.add(head.right)) {
////                stack.push(head.right);
////                continue;
////            }
////
//////            post
////            if (accessed.add(head)) {
////            }
////
////            stack.pop();
////        }
////    }
//}
