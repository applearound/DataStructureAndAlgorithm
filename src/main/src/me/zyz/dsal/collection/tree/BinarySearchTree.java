package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public interface BinarySearchTree<K, V, N extends BinarySearchTree.BinaryNode<K, V, N>> {
    int size();

    boolean isEmpty();

    void add(K key, V value);

    void add(N node);

    V findValue(K key);

    N findNode(K key);

    boolean contains(K key);

    boolean contains(N node);

    interface BinaryNode<K, V, N extends BinaryNode<K, V, N>> {
        K key();

        V value();

        N left();

        N right();

        void setLeft(N node);

        void setRight(N node);
    }
}
