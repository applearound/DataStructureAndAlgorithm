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
    private final RbNode<K, V> NIL;

    {
        NIL = new RbNode<>(null, null);
        NIL.setColor(BLACK);
    }

    public RbTree() {
        root = NIL;
    }

    @Override
    public void add(K key, V value) {
        add0(new RbNode<>(validateKey(key), value));
        size++;
    }

    private void add0(RbNode<K, V> node) {
        RbNode<K, V> trueParent = NIL;
        RbNode<K, V> tempNode = root;
        while (tempNode != NIL) {
            trueParent = tempNode;
            if (compareKey(node.key(), tempNode.key()) < 0) {
                tempNode = tempNode.left();
            } else {
                tempNode = tempNode.right();
            }
        }

        node.setParent(trueParent);
        if (trueParent == NIL) {
            root = node;
        } else if (compareKey(node.key(), trueParent.key()) < 0) {
            trueParent.setLeft(node);
        } else {
            trueParent.setRight(node);
        }

        node.setLeft(NIL);
        node.setRight(NIL);
        node.setColor(RED);

        fix(node);
    }

    private void fix(RbNode<K, V> node) {
        while (node.parent().isRed()) {
            if (node.parent() == node.parent().parent().left()) {
                RbNode<K, V> y = node.parent().parent().right();
                if (y.isRed()) {
                    node.parent().setColor(BLACK);
                    y.setColor(BLACK);
                    node.parent().parent().setColor(RED);
                    node = node.parent().parent();
                } else if (node == node.parent().right()) {
                    node = node.parent();
                    leftRotate(node);
                } else {
                    node.parent().setColor(BLACK);
                    node.parent().parent().setColor(RED);
                    rightRotate(node.parent().parent());
                }
            } else {
                RbNode<K, V> y = node.parent().parent().left();
                if (y.isRed()) {
                    node.parent().setColor(BLACK);
                    y.setColor(BLACK);
                    node.parent().parent().setColor(RED);
                    node = node.parent().parent();
                } else if (node == node.parent().left()) {
                    node = node.parent();
                    rightRotate(node);
                } else {
                    node.parent().setColor(BLACK);
                    node.parent().parent().setColor(RED);
                    leftRotate(node.parent().parent());
                }
            }
        }
        root.setColor(BLACK);
    }

    private void leftRotate(RbNode<K, V> node) {
        RbNode<K, V> nodeRightChild = node.right();
        node.setRight(nodeRightChild.left());

        if (nodeRightChild.hasLeft()) {
            nodeRightChild.left().setParent(node);
        }

        nodeRightChild.setParent(node.parent());
        if (node.parent() == NIL) {
            root = nodeRightChild;
        } else if (node == node.parent().left()) {
            node.parent().setLeft(nodeRightChild);
        } else {
            node.parent().setRight(nodeRightChild);
        }
        nodeRightChild.setLeft(node);
        node.setParent(nodeRightChild);
    }

    private void rightRotate(RbNode<K, V> node) {
        RbNode<K, V> nodeLeftChild = node.left();
        node.setLeft(nodeLeftChild.right());

        if (nodeLeftChild.hasRight()) {
            nodeLeftChild.setParent(node);
        }

        nodeLeftChild.setParent(node.parent());
        if (node.parent() == NIL) {
            root = nodeLeftChild;
        } else if (node == node.parent().left()) {
            node.parent().setLeft(nodeLeftChild);
        } else {
            node.parent().setRight(nodeLeftChild);
        }

        nodeLeftChild.setRight(node);
        node.setParent(nodeLeftChild);
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
