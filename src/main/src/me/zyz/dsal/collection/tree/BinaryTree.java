package me.zyz.dsal.collection.tree;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * @author yezhou
 */
public interface BinaryTree<K, V> extends Tree<K, V> {
    @Override
    int size();

    @Override
    boolean isEmpty();

    @Override
    void add(K key, V value);

    @Override
    V get(K key);

    @Override
    boolean contains(K key);

    static <K extends Comparable<K>, V> void anyOrderNoRecursiveBase(BinaryNode<K, V> node) {
        Deque<BinaryNode<K, V>> stack = new LinkedList<>();
        Set<BinaryNode<K, V>> set = new HashSet<>();
        Set<BinaryNode<K, V>> accessed = new HashSet<>();

        stack.push(node);

        while (!stack.isEmpty()) {
            BinaryNode<K, V> head = stack.peek();
            if (head.left() != null && set.add(head.left())) {
                stack.push(head.left());
                continue;
            }

            if (accessed.add(head)) {
                System.out.println(head.key());
            }

            if (head.right() != null && set.add(head.right())) {
                stack.push(head.right());
                continue;
            }

            stack.pop();
        }
    }

    interface BinaryNode<K, V> extends Tree.Node<K, V> {
        BinaryNode<K, V> left();

        BinaryNode<K, V> right();

        @Override
        K key();

        @Override
        V value();
    }
}
