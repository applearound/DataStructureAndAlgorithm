package me.zyz.dsal.collection.map;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.Set;

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
public class RbTreeMap<K, V> implements Map<K, V> {
    private static final Comparator DEFAULT_COMPARATOR = (final Object k1, final Object k2) -> ((Comparable) k1).compareTo(k2);

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private final Comparator<K> comparator;
    private final RbTreeMapNode<K, V> nil;

    private RbTreeMapNode<K, V> root;
    private int size;

    public RbTreeMap() {
        this(DEFAULT_COMPARATOR);
    }

    public RbTreeMap(Comparator<K> comparator) {
        final RbTreeMapNode<K, V> sentinel = new RbTreeMapNode<>(null, null);
        sentinel.color = BLACK;

        this.nil = sentinel;
        this.root = sentinel;

        this.comparator = comparator;
    }

    private int compareKey(final K k1, final K k2) {
        return comparator.compare(k1, k2);
    }

    /**
     * 创建对应元素的红色孤立新结点
     *
     * @param key   键，保证不为nil
     * @param value 值
     * @return 新结点
     */
    private RbTreeMapNode<K, V> newNode(final K key, final V value) {
        final RbTreeMapNode<K, V> newNode = new RbTreeMapNode<>(key, value);
        newNode.parent = nil;
        newNode.left = nil;
        newNode.right = nil;

        return newNode;
    }

    private boolean isNilNode(final RbTreeMapNode<K, V> node) {
        return node == nil;
    }

    private boolean isLeftChild(final RbTreeMapNode<K, V> parent, final RbTreeMapNode<K, V> node) {
        return parent.left == node;
    }

    private boolean isLeftChild(final RbTreeMapNode<K, V> node) {
        return isLeftChild(node.parent, node);
    }

    private boolean isRightChild(final RbTreeMapNode<K, V> parent, final RbTreeMapNode<K, V> node) {
        return parent.right == node;
    }

    private boolean isRightChild(final RbTreeMapNode<K, V> node) {
        return isRightChild(node.parent, node);
    }

    private boolean isBlack(final RbTreeMapNode<K, V> node) {
        return isNilNode(node) || node.isBlack();
    }

    private boolean isRed(final RbTreeMapNode<K, V> node) {
        return !isNilNode(node) && node.isRed();
    }

    /**
     * 子树的最小值节点
     *
     * @param root 根，保证不为nil
     * @return 最小值节点
     */
    private RbTreeMapNode<K, V> minimumNode(final RbTreeMapNode<K, V> root) {
        RbTreeMapNode<K, V> minNode = nil;

        RbTreeMapNode<K, V> n = root;
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
    private RbTreeMapNode<K, V> maximumNode(final RbTreeMapNode<K, V> root) {
        RbTreeMapNode<K, V> maxNode = nil;

        RbTreeMapNode<K, V> n = root;
        while (!isNilNode(n)) {
            maxNode = n;
            n = n.right;
        }

        return maxNode;
    }

    private RbTreeMapNode<K, V> searchNode(final RbTreeMapNode<K, V> root, final K key) {
        RbTreeMapNode<K, V> tempNode = root;
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
    private void leftRotate(final RbTreeMapNode<K, V> nodeRotate) {
        // 保存当前左旋节点的右子树y
        final RbTreeMapNode<K, V> rightChild = nodeRotate.right;

        // 原右子树的左孩子b，变为旋转结点的右孩子
        final RbTreeMapNode<K, V> rightLeftChild = rightChild.left;

        nodeRotate.right = rightLeftChild;

        if (!isNilNode(rightLeftChild)) {
            rightLeftChild.parent = nodeRotate;
        }
        // 以上，旋转结点和b结点的父子关系处理完毕

        // 处理y和旋转结点父结点的关系
        final RbTreeMapNode<K, V> parent = nodeRotate.parent;

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
    private void rightRotate(final RbTreeMapNode<K, V> nodeRotate) {
        final RbTreeMapNode<K, V> leftChild = nodeRotate.left;

        final RbTreeMapNode<K, V> leftRightChild = leftChild.right;

        nodeRotate.left = leftRightChild;

        if (!isNilNode(leftRightChild)) {
            leftRightChild.parent = nodeRotate;
        }

        final RbTreeMapNode<K, V> parent = nodeRotate.parent;

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
    private void transplant(final RbTreeMapNode<K, V> toRemove, final RbTreeMapNode<K, V> toTransplant) {
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
    private void insertFix(final RbTreeMapNode<K, V> fixNode) {
        RbTreeMapNode<K, V> node = fixNode;

        RbTreeMapNode<K, V> parent = node.parent;
        while (isRed(parent)) {
            // 若parent为红，则grandParent一定不为nil
            final RbTreeMapNode<K, V> grandParent = parent.parent;

            if (isLeftChild(parent)) {
                final RbTreeMapNode<K, V> uncle = grandParent.right;

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
                final RbTreeMapNode<K, V> uncle = grandParent.left;

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

    private void deleteFix(final RbTreeMapNode<K, V> replaceNode) {
        RbTreeMapNode<K, V> x = replaceNode;
        while (x != root && isBlack(x)) {
            // x不为root，x.parent必然不是nil
            if (isLeftChild(x)) {
                RbTreeMapNode<K, V> siblingNode = x.parent.right;

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
                RbTreeMapNode<K, V> botherNode = x.parent.left;

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

    private boolean add0(final K key, final V value) {
        if (isEmpty()) {
            root = newNode(key, value);
            root.color = BLACK;
            return true;
        }

        RbTreeMapNode<K, V> tempNode = root;
        while (true) {
            final int cmp = compareKey(key, tempNode.key);
            if (cmp < 0) {
                if (isNilNode(tempNode.left)) {
                    final RbTreeMapNode<K, V> newNode = newNode(key, value);
                    tempNode.left = newNode;
                    newNode.parent = tempNode;
                    insertFix(newNode);
                    return true;
                }
                tempNode = tempNode.left;
            } else if (cmp > 0) {
                if (isNilNode(tempNode.right)) {
                    final RbTreeMapNode<K, V> newNode = newNode(key, value);
                    tempNode.right = newNode;
                    newNode.parent = tempNode;
                    insertFix(newNode);
                    return true;
                }
                tempNode = tempNode.right;
            } else {
                tempNode.value = value;
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
    private void delete0(final RbTreeMapNode<K, V> preparedToRemove) {
        final RbTreeMapNode<K, V> realNodeToRemove;
        final RbTreeMapNode<K, V> toReplace;

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
    public boolean containsKey(Object key) {
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {
        this.root = nil;
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    static final class RbTreeMapNode<K, V> {
        private RbTreeMapNode<K, V> parent;
        private RbTreeMapNode<K, V> left;
        private RbTreeMapNode<K, V> right;
        private final K key;
        private V value;
        private boolean color;

        private RbTreeMapNode(final K key, final V value) {
            this.key = key;
            this.value = value;
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

