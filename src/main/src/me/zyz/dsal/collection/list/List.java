package me.zyz.dsal.collection.list;

/**
 * @author yz
 */
public interface List<E> {
    int size();

    void add(E e);

    void add(int index, E e);

    E set(int index, E e);

    E get(int index);

    boolean remove(E e);

    E remove(int index);

    int indexOf(E e);

    int lastIndexOf(E e);

    public boolean contains(E e);

    boolean isEmpty();
}
