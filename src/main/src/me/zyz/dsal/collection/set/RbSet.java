package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.RbTree;

public class RbSet<E> implements Set<E> {

    private RbTree<E, Object> rbTree = new RbTree<>();

    @Override
    public void add(E e) {
        rbTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        throw new NullPointerException();
    }

    @Override
    public boolean contains(E e) {
        return rbTree.contains(e);
    }

    @Override
    public int size() {
        return rbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return rbTree.isEmpty();
    }
}
