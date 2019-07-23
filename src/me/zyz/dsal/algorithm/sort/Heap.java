package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public interface Heap<E extends Comparable<E>> {
    void enter(E e);

    E quit();
}
