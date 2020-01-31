package me.zyz.dsal.collection.set;

import me.zyz.dsal.collection.tree.LeftLeaningRbTree;

/**
 * @author zyz
 */
public class LeftLeaningRbSet<E> implements Set<E> {

    private LeftLeaningRbTree<E, Object> leftLeaningRbTree = new LeftLeaningRbTree<>();

    @Override
    public void add(E e) {
        leftLeaningRbTree.add(e, null);
    }

    @Override
    public void remove(E e) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean contains(E e) {
        return leftLeaningRbTree.contains(e);
    }

    @Override
    public int size() {
        return leftLeaningRbTree.size();
    }

    @Override
    public boolean isEmpty() {
        return leftLeaningRbTree.size() == 0;
    }
}
