package me.zyz.dsal.collection.set;

import java.util.*;

/**
 * 1. 每个节点不是红节点就是黑节点
 * 2. 根节点是黑节点
 * 3. 叶子节点（空节点）是黑节点
 * 4. 如果一个节点是红节点，那它的孩子一定是黑节点
 * 5. 从任一节点出发通向任意叶子节点的简单路径，经过的黑节点数量相同
 * <p>
 * 参照算法导论实现
 *
 * @author yz
 */
public class RbTreeSet<E> implements Set<E> {
    private static final Comparator DEFAULT_COMPARATOR = (final Object k1, final Object k2) -> ((Comparable) k1).compareTo(k2);

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private final Comparator<E> comparator;
    private final RbTreeSetNode<E> nil;

    private RbTreeSetNode<E> root;
    private int size;

    public RbTreeSet() {
        this(DEFAULT_COMPARATOR);
    }

    public RbTreeSet(Comparator<E> comparator) {
        final RbTreeSetNode<E> sentinel = new RbTreeSetNode<>(null);
        sentinel.color = BLACK;

        this.nil = sentinel;
        this.root = sentinel;

        this.comparator = comparator;
    }

    private int compareKey(final E e1, final E e2) {
        return comparator.compare(e1, e2);
    }

    /**
     * 创建对应元素的红色孤立新结点
     *
     * @param element 元素，保证不为null
     * @return 新结点
     */
    private RbTreeSetNode<E> newNode(final E element) {
        final RbTreeSetNode<E> newNode = new RbTreeSetNode<>(element);
        newNode.parent = nil;
        newNode.left = nil;
        newNode.right = nil;

        return newNode;
    }

    private boolean isNilNode(final RbTreeSetNode<E> node) {
        return node == nil;
    }

    private boolean isLeftChild(final RbTreeSetNode<E> parent, final RbTreeSetNode<E> node) {
        return parent.left == node;
    }

    private boolean isLeftChild(final RbTreeSetNode<E> node) {
        return isLeftChild(node.parent, node);
    }

    private boolean isRightChild(final RbTreeSetNode<E> parent, final RbTreeSetNode<E> node) {
        return parent.right == node;
    }

    private boolean isRightChild(final RbTreeSetNode<E> node) {
        return isRightChild(node.parent, node);
    }

    private boolean isBlack(final RbTreeSetNode<E> node) {
        return isNilNode(node) || node.isBlack();
    }

    private boolean isRed(final RbTreeSetNode<E> node) {
        return !isNilNode(node) && node.isRed();
    }

    /**
     * 子树的最小值节点
     *
     * @param root 根，保证不为nil
     * @return 最小值节点
     */
    private RbTreeSetNode<E> minimumNode(final RbTreeSetNode<E> root) {
        RbTreeSetNode<E> minNode = nil;

        RbTreeSetNode<E> n = root;
        while (!isNilNode(n)) {
            minNode = n;
            n = n.left;
        }

        return minNode;
    }

    /**
     * 子树的最大值节点
     *
     * @param root 根，保证不为nil
     * @return 最小值节点
     */
    private RbTreeSetNode<E> maximumNode(final RbTreeSetNode<E> root) {
        RbTreeSetNode<E> maxNode = nil;

        RbTreeSetNode<E> n = root;
        while (!isNilNode(n)) {
            maxNode = n;
            n = n.right;
        }

        return maxNode;
    }

    private RbTreeSetNode<E> searchNode(final RbTreeSetNode<E> root, final E key) {
        RbTreeSetNode<E> tempNode = root;
        while (!isNilNode(tempNode)) {
            final int cmp = compareKey(key, tempNode.key);
            if (cmp < 0) {
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                tempNode = tempNode.right;
            } else {
                return tempNode;
            }
        }
        return nil;
    }

    /**
     * <pre>
     *   x              y
     *  / \            / \
     * a   y   ---->  x   c
     *    / \        / \
     *   b   c      a   b
     * </pre>
     * <p>
     * x is the node to left rotate
     *
     * @param nodeRotate 左旋的结点，保证不为nil
     */
    private void leftRotate(final RbTreeSetNode<E> nodeRotate) {
        // 保存当前左旋节点的右子树y
        final RbTreeSetNode<E> rightChild = nodeRotate.right;

        // 原右子树的左孩子b，变为旋转结点的右孩子
        final RbTreeSetNode<E> rightLeftChild = rightChild.left;

        nodeRotate.right = rightLeftChild;

        if (!isNilNode(rightLeftChild)) {
            rightLeftChild.parent = nodeRotate;
        }
        // 以上，旋转结点和b结点的父子关系处理完毕

        // 处理y和旋转结点父结点的关系
        final RbTreeSetNode<E> parent = nodeRotate.parent;

        rightChild.parent = parent;
        if (isNilNode(parent)) {
            // 如果parent为空，则y结点就是新的root
            root = rightChild;
        } else if (isLeftChild(parent, nodeRotate)) {
            // 否则，y结点是父结点的左或右孩子
            parent.left = rightChild;
        } else {
            parent.right = rightChild;
        }
        // 处理y节点和旋转结点的父子关系
        rightChild.left = nodeRotate;
        nodeRotate.parent = rightChild;
    }

    /**
     * <pre>
     *     y           x
     *    / \         / \
     *   x   c ----> a   y
     *  / \             / \
     * a   b           b   c
     * </pre>
     * <p>
     * y is the node to right rotate
     *
     * @param nodeRotate 右旋的结点
     */
    private void rightRotate(final RbTreeSetNode<E> nodeRotate) {
        final RbTreeSetNode<E> leftChild = nodeRotate.left;

        final RbTreeSetNode<E> leftRightChild = leftChild.right;

        nodeRotate.left = leftRightChild;

        if (!isNilNode(leftRightChild)) {
            leftRightChild.parent = nodeRotate;
        }

        final RbTreeSetNode<E> parent = nodeRotate.parent;

        leftChild.parent = parent;
        if (isNilNode(parent)) {
            root = leftChild;
        } else if (isLeftChild(parent, nodeRotate)) {
            parent.left = leftChild;
        } else {
            parent.right = leftChild;
        }

        leftChild.right = nodeRotate;
        nodeRotate.parent = leftChild;
    }

    /**
     * 用将新结点移植到老结点的位置，仅处理与老结点parent的关系
     *
     * @param toRemove     删除的结点，保证不为nil
     * @param toTransplant 移植的结点，可以为nil
     */
    private void transplant(final RbTreeSetNode<E> toRemove, final RbTreeSetNode<E> toTransplant) {
        if (isNilNode(toRemove.parent)) {
            root = toTransplant;
        } else if (isLeftChild(toRemove)) {
            toRemove.parent.left = toTransplant;
        } else {
            toRemove.parent.right = toTransplant;
        }
        toTransplant.parent = toRemove.parent;
    }

    /**
     * 插入一个结点只可能破坏性质2或4之一
     *
     * @param fixNode
     */
    private void insertFix(final RbTreeSetNode<E> fixNode) {
        RbTreeSetNode<E> node = fixNode;

        RbTreeSetNode<E> parent = node.parent;
        while (isRed(parent)) {
            // 若parent为红，则grandParent一定不为nil
            final RbTreeSetNode<E> grandParent = parent.parent;

            if (isLeftChild(parent)) {
                final RbTreeSetNode<E> uncle = grandParent.right;

                if (isRed(uncle)) {
                    // uncle是红必不为nil
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    grandParent.color = RED;

                    node = grandParent;
                } else if (isRightChild(node)) {
                    node = parent;

                    leftRotate(node);
                } else {
                    parent.color = BLACK;
                    grandParent.color = RED;

                    rightRotate(grandParent);
                }
            } else {
                final RbTreeSetNode<E> uncle = grandParent.left;

                if (isRed(uncle)) {
                    uncle.color = BLACK;
                    parent.color = BLACK;
                    grandParent.color = RED;

                    node = grandParent;
                } else if (isLeftChild(node)) {
                    node = parent;

                    rightRotate(node);
                } else {
                    parent.color = BLACK;
                    grandParent.color = RED;

                    leftRotate(grandParent);
                }
            }

            parent = node.parent;
        }

        root.color = BLACK;
    }

    private void deleteFix(final RbTreeSetNode<E> replaceNode) {
        RbTreeSetNode<E> x = replaceNode;
        while (x != root && isBlack(x)) {
            // x不为root，x.parent必然不是nil
            if (isLeftChild(x)) {
                RbTreeSetNode<E> siblingNode = x.parent.right;

                // 如果兄弟是红结点，则必不为nil
                if (isRed(siblingNode)) {
                    x.parent.color = RED;
                    siblingNode.color = BLACK;

                    leftRotate(x.parent);
                    siblingNode = x.parent.right;
                }

                // 第一次进入时，兄弟节点必然存在
                if (isBlack(siblingNode.left) && isBlack(siblingNode.right)) {
                    siblingNode.color = RED;
                    x = x.parent;
                } else {
                    if (isBlack(siblingNode.right)) {
                        siblingNode.left.color = BLACK;
                        siblingNode.color = RED;

                        rightRotate(siblingNode);
                        siblingNode = x.parent.right;
                    }

                    x.parent.color = BLACK;
                    siblingNode.color = x.parent.color;
                    siblingNode.right.color = BLACK;

                    leftRotate(x.parent);

                    x = root;
                }
            } else {
                RbTreeSetNode<E> botherNode = x.parent.left;

                if (isRed(botherNode)) {
                    x.parent.color = RED;
                    botherNode.color = BLACK;

                    rightRotate(x.parent);
                    botherNode = x.parent.left;
                }

                if (isBlack(botherNode.left) && isBlack(botherNode.right)) {
                    botherNode.color = RED;
                    x = x.parent;
                } else {
                    if (isBlack(botherNode.left)) {
                        botherNode.right.color = BLACK;
                        botherNode.color = RED;

                        leftRotate(botherNode);
                        botherNode = x.parent.left;
                    }

                    x.parent.color = BLACK;
                    botherNode.color = x.parent.color;
                    botherNode.left.color = BLACK;

                    rightRotate(x.parent);

                    x = root;
                }
            }
        }
        if (!isNilNode(x)) {
            x.color = BLACK;
        }
    }

    private boolean add0(final E element) {
        if (isEmpty()) {
            root = newNode(element);
            root.color = BLACK;
            return true;
        }

        RbTreeSetNode<E> tempNode = root;
        while (true) {
            final int cmp = compareKey(element, tempNode.key);
            if (cmp < 0) {
                if (isNilNode(tempNode.left)) {
                    final RbTreeSetNode<E> newNode = newNode(element);
                    tempNode.left = newNode;
                    newNode.parent = tempNode;
                    insertFix(newNode);
                    return true;
                }
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                if (isNilNode(tempNode.right)) {
                    final RbTreeSetNode<E> newNode = newNode(element);
                    tempNode.right = newNode;
                    newNode.parent = tempNode;
                    insertFix(newNode);
                    return true;
                }
                tempNode = tempNode.right;
            } else {
                return false;
            }
        }
    }

    /**
     * 删除一个结点，被删除的结点并不一定是真正删除的结点
     * 因为被删除结点的位置事实上会被它的后继（也可以是前驱）替代
     * 这种情况下，逻辑上真正被删除的应当是它的后继
     * 当然也有可能是nil
     *
     * @param preparedToRemove 想要删除的结点，保证不为nil
     */
    private void delete0(final RbTreeSetNode<E> preparedToRemove) {
        final RbTreeSetNode<E> realNodeToRemove;
        final RbTreeSetNode<E> toReplace;

        if (isNilNode(preparedToRemove.left)) {
            // 左子树为空，用右子树代替
            realNodeToRemove = preparedToRemove;

            toReplace = realNodeToRemove.right;
            transplant(realNodeToRemove, realNodeToRemove.right);
        } else if (isNilNode(preparedToRemove.right)) {
            // 右子树为空，用左子树代替
            realNodeToRemove = preparedToRemove;

            toReplace = realNodeToRemove.left;
            transplant(realNodeToRemove, realNodeToRemove.left);
        } else {
            // 左右子树都不为空，选择后继（右子树最小值）代替
            // 注意，这种情况下 preparedToRemove 的位置只是被后继完美替代，真正消失的是这个后继
            realNodeToRemove = minimumNode(preparedToRemove.right);

            // 后继的左孩子必为空，所以用它的右孩子代替它
            toReplace = realNodeToRemove.right;

            // 当后继不是删除结点的孩子时，需要先移植后继本身
            if (realNodeToRemove.parent != preparedToRemove) {
                transplant(realNodeToRemove, toReplace);

                preparedToRemove.right.parent = realNodeToRemove;
                realNodeToRemove.right = preparedToRemove.right;
            }

            transplant(preparedToRemove, realNodeToRemove);

            preparedToRemove.left.parent = realNodeToRemove;

            realNodeToRemove.left = preparedToRemove.left;
            realNodeToRemove.color = preparedToRemove.color;
        }

        if (realNodeToRemove.isBlack()) {
            deleteFix(toReplace);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return isNilNode(root);
    }

    @Override
    public boolean contains(final Object element) {
        final E e = (E) Objects.requireNonNull(element);

        RbTreeSetNode<E> tempNode = root;
        while (!isNilNode(tempNode)) {
            final int cmp = compareKey(e, tempNode.key);
            if (cmp < 0) {
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                tempNode = tempNode.right;
            } else {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(final E e) {
        if (add0(Objects.requireNonNull(e, "element"))) {
            size += 1;
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(final Object element) {
        final E e = (E) Objects.requireNonNull(element);

        final RbTreeSetNode<E> nodeDeleting = searchNode(root, e);
        if (isNilNode(nodeDeleting)) {
            return false;
        }

        delete0(nodeDeleting);

        size -= 1;

        return true;
    }

    @Override
    public boolean containsAll(final Collection<?> collection) {
        return Objects.requireNonNull(collection, "collection").stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(final Collection<? extends E> collection) {
        return Objects.requireNonNull(collection, "collection").stream().map(this::add).filter(t -> t).count() > 0;
    }

    @Override
    public boolean retainAll(final Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(final Collection<?> collection) {
        return Objects.requireNonNull(collection, "collection").stream().map(this::remove).filter(t -> t).count() > 0;
    }

    @Override
    public void clear() {
        this.root = nil;
        this.size = 0;
    }

    static final class RbTreeSetNode<K> {
        private RbTreeSetNode<K> parent;
        private RbTreeSetNode<K> left;
        private RbTreeSetNode<K> right;
        private final K key;
        private boolean color;

        private RbTreeSetNode(final K key) {
            this.key = key;
            this.color = RED;
        }

        boolean isRed() {
            return color == RED;
        }

        boolean isBlack() {
            return color == BLACK;
        }
    }
}
