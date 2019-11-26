package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public interface LinkedBinarySearchTree<K, V> extends BinarySearchTree<K, V> {
    interface BinaryNode<K, V> {
        K key();

        V value();

        BinaryNode<K, V> left();

        BinaryNode<K, V> right();
    }
}
