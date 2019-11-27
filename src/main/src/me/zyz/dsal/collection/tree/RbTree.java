package me.zyz.dsal.collection.tree;

/**
 * 1. 每个节点不是红节点就是黑节点
 * 2. 根节点是黑节点
 * 3. 叶子节点（空节点）是黑节点
 * 4. 如果一个节点是红节点，那它的孩子一定是黑节点
 * 5. 从任一节点出发通向任意叶子节点的简单路径，经过的黑节点数量相同
 *
 * @author zyz
 */
public class RbTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, RbTree.RbNode<K, V>> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;

    public void add(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key");
        }
        root = add0(root, key, value);
        root.color = BLACK;
        size++;
    }

    private RbNode<K, V> add0(RbNode<K, V> root, K key, V value) {
        if (root == null) {
            return new RbNode<>(key, value);
        }

        int i = compareKey(key, root.key());
        if (i < 0) {
            root.setLeft(add0(root.left(), key, value));
        } else if (i > 0) {
            root.setRight(add0(root.right(), key, value));
        }

        if (isRed(root.right()) && isBlack(root.left())) {
            root = leftRotate(root);
        }
        if (isRed(root.left()) && isRed(root.left().left())) {
            root = rightRotate(root);
        }
        if (isRed(root.left()) && isRed(root.right())) {
            flipColors(root);
        }

        return root;
    }

    private RbNode<K, V> leftRotate(RbNode<K, V> root) {
        RbNode<K, V> x = root.right();

        root.setRight(x.left());
        x.setLeft(root);

        x.color = root.color;
        root.color = RED;

        return x;
    }

    private RbNode<K, V> rightRotate(RbNode<K, V> root) {
        RbNode<K, V> x = root.left();

        root.setLeft(x.right());
        x.setRight(root);

        x.color = root.color;
        root.color = RED;

        return x;
    }

    private void flipColors(RbNode<K, V> node) {
        node.color = RED;
        node.left().setColor(BLACK);
        node.right().setColor(BLACK);
    }

    private boolean isRed(RbNode node) {
        return node != null && node.isRed();
    }

    private boolean isBlack(RbNode node) {
        return node == null || node.isBlack();
    }

    static class RbNode<K, V> extends AbstractBinaryNode<K, V, RbNode<K, V>> {
        private boolean color;

        RbNode(K key, V value) {
            super(key, value);
            this.color = RED;
        }

        boolean isRed() {
            return color == RED;
        }

        boolean isBlack() {
            return color == BLACK;
        }

        void setColor(boolean color) {
            this.color = color;
        }
    }
}
