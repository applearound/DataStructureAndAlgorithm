package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public interface Tree<K, V> {
    int size();

    boolean isEmpty();

    void add(K key, V value);

    V get(K key);

    boolean contains(K key);

    interface Node<K, V> {
        K key();

        V value();
    }
}
