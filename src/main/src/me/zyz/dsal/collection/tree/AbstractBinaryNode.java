package me.zyz.dsal.collection.tree;

/**
 * @author yz
 */
public abstract class AbstractBinaryNode<K, V, N extends AbstractBinaryNode<K, V, N>> implements BinarySearchTree.BinaryNode<K, V, N> {
    private K key;
    private V value;
    private N left;
    private N right;
    private N parent;

    AbstractBinaryNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
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
    public N left() {
        return left;
    }

    @Override
    public N right() {
        return right;
    }

    @Override
    public N parent() {
        return parent;
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
    public void setLeft(N node) {
        this.left = node;
    }

    @Override
    public void setRight(N right) {
        this.right = right;
    }

    @Override
    public void setParent(N parent) {
        this.parent = parent;
    }
}
