package me.zyz.dsal.collection.stbl;

/**
 * @author yz
 * @param <K>
 * @param <V>
 */
public interface SymbolTable<K, V> {

    void put(K key, V value);

    V get(K key);

    void delete(K key);

    boolean contains(K key);

    boolean isEmpty();

    int size();

    Iterable<K> keys();
}
