package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.RecursionAvlTree;

/**
 * @author yezhou
 */
public class RecursionAvlSet<E> implements Set<E> {

    private RecursionAvlTree<E, Object> recursionAvlTree = new RecursionAvlTree<>();

    @Override
    public void add(E e) {
        recursionAvlTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        throw new NullPointerException();
    }

    @Override
    public boolean contains(E e) {
        return recursionAvlTree.contains(e);
    }

    @Override
    public int size() {
        return recursionAvlTree.size();
    }

    @Override
    public boolean isEmpty() {
        return recursionAvlTree.isEmpty();
    }
}
