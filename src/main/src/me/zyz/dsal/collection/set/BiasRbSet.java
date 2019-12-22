package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.BiasRbTree;

/**
 * @author zyz
 */
public class BiasRbSet<E> implements Set<E> {

    private BiasRbTree<E, Object> biasRbTree = new BiasRbTree<>();

    @Override
    public void add(E e) {
        biasRbTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contains(E e) {
        return biasRbTree.contains(e);
    }

    @Override
    public int size() {
        return biasRbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return biasRbTree.size() == 0;
    }
}
