package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.BstTree;

/**
 * @author yz
 */
public class BstSet<E extends Comparable<E>> implements Set<E> {

    private BstTree<E, Object> bst;

    public BstSet() {
        this.bst = new BstTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e, null);
    }

    @Override
    public void remove(E e) {
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int size() {
        return bst.size();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
