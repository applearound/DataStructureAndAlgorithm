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
        return key;
    }

    @Override
    public V value() {
        return value;
    }

    @Override
    public void setKey(K key) {
        this.key = key;
    }

    @Override
    public void setValue(V value) {
        this.value = value;
    }

    @Override
    public N left() {
        return left;
    }

    @Override
    public N right() {
        return right;
    }

    @Override
    public void setLeft(N left) {
        this.left = left;
    }

    @Override
    public void setRight(N right) {
        this.right = right;
    }
}
