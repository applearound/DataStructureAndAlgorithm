package me.zyz.dsal.collection.list;

/**
 * @author yz
 */
public interface List<E> extends Collection<E> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    boolean contains(E e);

    @Override
    void add(E e);

    @Override
    boolean remove(E e);

    @Override
    void clear();

    void add(int index, E e);

    E set(int index, E e);

    E get(int index);

    E remove(int index);

    int indexOf(E e);

    int lastIndexOf(E e);
}
