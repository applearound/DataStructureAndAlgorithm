package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public interface BinarySearchTree<K, V> {
    int size();

    boolean isEmpty();

    void add(K key, V value);

    V get(K key);

    boolean contains(K key);
}
