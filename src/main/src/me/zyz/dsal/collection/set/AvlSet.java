package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.AvlTree;

/**
 * @author zyz
 */
public class AvlSet<E> implements Set<E> {

    private AvlTree<E, Object> avlTree = new AvlTree<>();

    @Override
    public void add(E e) {
        avlTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        throw new NullPointerException();
    }

    @Override
    public boolean contains(E e) {
        return avlTree.contains(e);
    }

    @Override
    public int size() {
        return avlTree.size();
    }

    @Override
    public boolean isEmpty() {
        return avlTree.size() == 0;
    }
}
