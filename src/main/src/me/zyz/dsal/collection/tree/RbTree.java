package me.zyz.dsal.collection.tree;


/**
 * 1. 每个节点不是红节点就是黑节点
 * 2. 根节点是黑节点
 * 3. 叶子节点（空节点）是黑节点
 * 4. 如果一个节点是红节点，那它的孩子一定是黑节点
 * 5. 从任一节点出发通向任意叶子节点的简单路径，经过的黑节点数量相同
 *
 * @author yz
 */
public final class RbTree<K, V> extends AbstractLinkedBinarySearchTree<K, V, RbTree.RbNode<K, V>> {
    public static final boolean RED = true;
    public static final boolean BLACK = false;
    private final RbNode<K, V> nil;

    public RbTree() {
        nil = new RbNode<>(null, null);
        nil.setLeft(nil);
        nil.setRight(nil);
        nil.setParent(nil);
        nil.setColor(BLACK);

        root = nil;
    }

    @Override
    public void add(K key, V value) {
        add0(validateKey(key), value);
        size++;
    }

    private void add0(K key, V value) {
        RbNode<K, V> trueParent = nil;
        RbNode<K, V> tempNode = root;
        while (tempNode != nil) {
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

        RbNode<K, V> newNode = new RbNode<>(key, value);
        newNode.setParent(trueParent);
        if (trueParent == nil) {
            root = newNode;
        } else if (compareKey(newNode.key(), trueParent.key()) < 0) {
            trueParent.setLeft(newNode);
        } else {
            trueParent.setRight(newNode);
        }

        newNode.setLeft(nil);
        newNode.setRight(nil);
        newNode.setColor(RED);

        insertFix(newNode);
    }

    private void delete0(RbNode<K, V> z) {
        RbNode<K, V> y = z;
        boolean yOriginalColor = y.color();

        RbNode<K, V> x;
        if (z.left() == nil) {
            x = z.right();
            transplant(z, z.right());
        } else if (z.right() == nil) {
            x = z.left();
            transplant(z, z.left());
        } else {
            y = minimumNode(z.right());
            x = y.right();
            if (y.parent() == z) {
                x.setParent(y);
            } else {
                transplant(y, y.right());
                y.setRight(z.right());
                y.right().setParent(y);
            }
            transplant(z, y);
            y.setLeft(z.left());
            y.left().setParent(y);
            y.setColor(z.color());
        }
        if (yOriginalColor == BLACK) {
            deleteFix(x);
        }
    }

    private void deleteFix(RbNode<K, V> x) {

    }

    private void transplant(RbNode<K, V> u, RbNode<K, V> v) {
        if (u.parent() == nil) {
            root = v;
        } else if (u == u.parent().left()) {
            u.parent().setLeft(v);
        } else {
            u.parent().setRight(v);
        }
        v.setParent(u.parent());
    }

    private void insertFix(RbNode<K, V> node) {
        while (node.parent().isRed()) {
            RbNode<K, V> parent = node.parent();
            RbNode<K, V> grandParent = parent.parent();
            if (parent == grandParent.left()) {
                if (grandParent.right().isRed()) {
                    parent.setColor(BLACK);
                    grandParent.right().setColor(BLACK);
                    grandParent.setColor(RED);
                    node = grandParent;
                } else if (node == parent.right()) {
                    node = parent;
                    leftRotate(node);
                } else {
                    parent.setColor(BLACK);
                    grandParent.setColor(RED);
                    rightRotate(grandParent);
                }
            } else {
                if (grandParent.left().isRed()) {
                    parent.setColor(BLACK);
                    grandParent.left().setColor(BLACK);
                    grandParent.setColor(RED);
                    node = grandParent;
                } else if (node == parent.left()) {
                    node = parent;
                    rightRotate(node);
                } else {
                    parent.setColor(BLACK);
                    grandParent.setColor(RED);
                    leftRotate(grandParent);
                }
            }
        }
        root.setColor(BLACK);
    }

    private void leftRotate(RbNode<K, V> node) {
        RbNode<K, V> rightChildNode = node.right();
        node.setRight(rightChildNode.left());

        if (rightChildNode.hasLeft()) {
            rightChildNode.left().setParent(node);
        }

        rightChildNode.setParent(node.parent());
        if (node.parent() == nil) {
            root = rightChildNode;
        } else if (node == node.parent().left()) {
            node.parent().setLeft(rightChildNode);
        } else {
            node.parent().setRight(rightChildNode);
        }
        rightChildNode.setLeft(node);
        node.setParent(rightChildNode);
    }

    private void rightRotate(RbNode<K, V> node) {
        RbNode<K, V> leftChildNode = node.left();
        node.setLeft(leftChildNode.right());

        if (leftChildNode.hasRight()) {
            leftChildNode.right().setParent(node);
        }

        leftChildNode.setParent(node.parent());
        if (node.parent() == nil) {
            root = leftChildNode;
        } else if (node == node.parent().left()) {
            node.parent().setLeft(leftChildNode);
        } else {
            node.parent().setRight(leftChildNode);
        }

        leftChildNode.setRight(node);
        node.setParent(leftChildNode);
    }

    @Override
    RbNode<K, V> nil() {
        return nil;
    }

    static class RbNode<K, V> extends AbstractBinaryNode<K, V, RbNode<K, V>> {
        private boolean color;

        RbNode(K key, V value) {
            super(key, value);
            this.color = RED;
        }

        boolean color() {
            return color;
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
