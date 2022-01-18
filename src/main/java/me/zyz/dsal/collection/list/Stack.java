package me.zyz.dsal.collection.list;

/**
 * @author yz
 */
public interface Stack<E> extends Collection<E> {
    void push(E e);

    E pop();
}
