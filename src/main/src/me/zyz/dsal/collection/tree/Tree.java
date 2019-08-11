package me.zyz.dsal.collection.tree;

/**
 * @author yezhou
 */
public interface Tree {
    int size();

    boolean isEmpty();

    interface Node<E> {
        E value();

        Node<E>[] children();
    }
}
