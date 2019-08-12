package me.zyz.dsal.collection.tree;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author yezhou
 */
public interface BinaryTree<K, V> extends Tree<K, V> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    void add(K key, V value);

    @Override
    V get(K key);

    @Override
    boolean contains(K key);

    interface BinaryNode<K, V> extends Tree.Node<K, V> {
        BinaryNode<K, V> left();

        BinaryNode<K, V> right();

        @Override
        K key();

        @Override
        V value();
    }

    interface BinaryNodeOperation<K, V> extends Tree.NodeOperation<BinaryNode<K, V>, K, V> {
        @Override
        void operate(BinaryNode<K, V> node);
    }
}
