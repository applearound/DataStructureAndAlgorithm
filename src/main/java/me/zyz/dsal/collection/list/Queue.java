package me.zyz.dsal.collection.list;

/**
 * @author yz
 */
public interface Queue<E> extends Collection<E> {
    void enter(E e);

    E quit();
}
