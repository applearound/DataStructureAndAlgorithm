package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.LinkedBinarySearchTree;

/**
 * @author yz
 */
public class BstSet<E extends Comparable<E>> implements Set<E> {

    private LinkedBinarySearchTree<E, Object> bst;

    public BstSet() {
        this.bst = new LinkedBinarySearchTree<>();
    }

    @Override
    public void add(E e) {
        bst.add(e, null);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
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
