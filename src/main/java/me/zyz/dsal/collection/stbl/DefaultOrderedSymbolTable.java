package me.zyz.dsal.collection.stbl;

import java.util.NoSuchElementException;
import java.util.TreeMap;

public class DefaultOrderedSymbolTable<K extends Comparable<K>, V> implements OrderedSymbolTable<K, V> {
    private TreeMap<K, V> treeMap;

    public DefaultOrderedSymbolTable() {
        this.treeMap = new TreeMap<>();
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }

        if (value == null) {
            treeMap.remove(key);
        } else {
            treeMap.put(key, value);
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
        return treeMap.get(key);
    }

    @Override
    public void delete(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
        put(key, null);
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
        return treeMap.containsKey(key);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return treeMap.size();
    }

    @Override
    public Iterable<K> keys() {
        return keys(min(), max());
    }

    @Override
    public K min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol Table is Empty");
        }
        return treeMap.firstKey();
    }

    @Override
    public K max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Symbol Table is Empty");
        }
        return treeMap.lastKey();
    }

    @Override
    public K floor(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
        K k = treeMap.floorKey(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are greater than " + key);
        }

        return k;
    }

    @Override
    public K ceiling(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key can't be null.");
        }
        K k = treeMap.ceilingKey(key);
        if (k == null) {
            throw new NoSuchElementException("all keys are less than " + key);
        }

        return k;
    }

    @Override
    public int rank(K key) {
        return 0;
    }

    @Override
    public K select(int k) {
        return null;
    }

    @Override
    public void deleteMin() {

    }

    @Override
    public void deleteMax() {

    }

    @Override
    public int size(K lo, K hi) {
        return 0;
    }

    @Override
    public Iterable<K> keys(K lo, K hi) {
        return treeMap.navigableKeySet();
    }
}
