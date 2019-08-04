package me.zyz.dsal.collection.tree.segment;

/**
 * @author yz
 */
public interface Merger<E> {
    E merge(E left, E right);
}
