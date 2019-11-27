package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
public abstract class AbstractBinaryNode<K, V, N extends AbstractBinaryNode<K, V, N>> implements BinarySearchTree.BinaryNode<K, V, N> {
    private K key;
    private V value;
    private N left;
    private N right;

    AbstractBinaryNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K key() {
        return this.key;
    }

    @Override
    public V value() {
        return this.value;
    }

    @Override
    public N left() {
        return this.left;
    }

    @Override
    public N right() {
        return this.right;
    }

    @Override
    public void setLeft(N node) {
        this.left = node;
    }

    @Override
    public void setRight(N node) {
        this.right = node;
    }

    @Override
    public N clearLeft() {
        N left = this.left;

        this.left = null;

        return left;
    }

    @Override
    public N clearRight() {
        N right = this.right;

        this.right = null;

        return right;
    }

    @Override
    public boolean hasLeft() {
        return left != null;
    }

    @Override
    public boolean hasRight() {
        return right != null;
    }
}