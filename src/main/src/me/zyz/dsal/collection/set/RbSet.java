package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.RbTree;

public class RbSet<E> implements Set<E> {
    private RbTree<E, Object> innerRbTree = new RbTree<>();

    @Override
    public void add(E e) {
        innerRbTree.add(e, null);
    }

    @Override
    public void remove(E e) {

    }

    @Override
    public boolean contains(E e) {
        return innerRbTree.contains(e);
    }

    @Override
    public int size() {
        return innerRbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return innerRbTree.isEmpty();
    }
}
