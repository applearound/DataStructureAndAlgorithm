package me.zyz.dsal.collection.set;

/**
 * @author yz
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int size();

    boolean isEmpty();
}
