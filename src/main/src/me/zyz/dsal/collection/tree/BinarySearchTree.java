package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
interface BinarySearchTree<K, V, N extends BinarySearchTree.BinaryNode<K, V, N>> {
    int size();

    boolean isEmpty();

    void add(K key, V value);

    V findValue(K key);

    boolean contains(K key);

    interface BinaryNode<K, V, N extends BinaryNode<K, V, N>> {
        K key();

        V value();

        N left();

        N right();

        boolean hasLeft();

        boolean hasRight();

        void setLeft(N node);

        N clearLeft();

        void setRight(N node);

        N clearRight();
    }
}
