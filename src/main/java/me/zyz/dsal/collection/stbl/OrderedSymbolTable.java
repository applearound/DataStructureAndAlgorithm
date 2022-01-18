package me.zyz.dsal.collection.stbl;


public interface OrderedSymbolTable<K extends Comparable<K>, V> extends SymbolTable<K, V> {

    K min();

    K max();

    K floor(K key);

    K ceiling(K key);

    int rank(K key);

    K select(int k);

    void deleteMin();

    void deleteMax();

    int size(K lo, K hi);

    Iterable<K> keys(K lo, K hi);
}
