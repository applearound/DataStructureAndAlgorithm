package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
public final class AvlTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, AvlTree.AvlNode<K, V>> {

    private int height(AvlNode<K, V> node) {
        if (node == null) {
            return 0;
        }

        return node.height();
    }

    private int balanceFactor(AvlNode<K, V> node) {
        if (node == null) {
            return 0;
        }

        return height(node.left()) - height(node.right());
    }

    @Override
    public void add(K key, V value) {
        root = add(root, key, value);
        size++;
    }

    private AvlNode<K, V> add(AvlNode<K, V> root, K key, V value) {
        if (root == null) {
            return new AvlNode<>(key, value);
        }

        int compareNum = compareKey(key, root.key());
        if (compareNum < 0) {
            root.setLeft(add(root.left(), key, value));
        } else if (compareNum > 0) {
            root.setRight(add(root.right(), key, value));
        }

        root.setHeight(Math.max(height(root.left()), height(root.right())) + 1);

        int balanceFactor = balanceFactor(root);

        if (balanceFactor > 1) {
            int leftBalanceFactor = balanceFactor(root.left());

            if (leftBalanceFactor >= 0) {
                // LL 旋转
                return rightRotate(root);
            } else {
                // LR 旋转
                root.setLeft(leftRotate(root.left()));
                return rightRotate(root);
            }
        }

        if (balanceFactor < -1) {
            int rightBalanceFactor = balanceFactor(root.right());

            if (rightBalanceFactor <= 0) {
                // RR 旋转
                return leftRotate(root);
            } else {
                // RL 旋转
                root.setRight(rightRotate(root.right()));
                return leftRotate(root);
            }
        }

        return root;
    }

    private AvlNode<K, V> rightRotate(AvlNode<K, V> root) {
        AvlNode<K, V> newRoot = root.left();
        AvlNode<K, V> tree = newRoot.right();

        newRoot.setRight(root);
        root.setLeft(tree);

        root.height = Math.max(height(root.left()), height(root.right())) + 1;
        newRoot.height = Math.max(height(newRoot.left()), height(newRoot.right())) + 1;

        return newRoot;
    }

    private AvlNode<K, V> leftRotate(AvlNode<K, V> root) {
        AvlNode<K, V> newRoot = root.right();
        AvlNode<K, V> tree = newRoot.left();

        newRoot.setLeft(root);
        root.setRight(tree);

        root.height = Math.max(height(root.left()), height(root.right())) + 1;
        newRoot.height = Math.max(height(newRoot.left()), height(newRoot.right())) + 1;

        return newRoot;
    }

    static class AvlNode<K, V> extends AbstractBinaryNode<K, V, AvlNode<K, V>> {
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
    }
}
