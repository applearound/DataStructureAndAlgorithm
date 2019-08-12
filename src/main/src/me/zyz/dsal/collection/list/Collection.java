package me.zyz.dsal.collection.list;

/**
 * @author yezhou
 */
public interface Collection<E> {
    int size();

    boolean isEmpty();

    boolean contains(E e);

    void add(E e);

    boolean remove(E e);

    void clear();
}
