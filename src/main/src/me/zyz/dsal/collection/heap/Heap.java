package me.zyz.dsal.collection.heap;

/**
 * @author yezhou
 */
public interface Heap<E extends Comparable<E>> {
    void enter(E e);

    E quit();
}
