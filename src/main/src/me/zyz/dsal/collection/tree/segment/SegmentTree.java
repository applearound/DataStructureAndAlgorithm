package me.zyz.dsal.collection.tree.segment;

import java.util.Arrays;

/**
 * @author yz
 */
public class SegmentTree<E> {
    private static final double LN_2 = Math.log(2);

    private Object[] data;
    private Object[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] data, Merger<E> merger) {
        this.merger = merger;

        this.data = Arrays.copyOf(data, data.length);
        this.tree = new Object[treeSize(this.data.length)];
        buildSegmentTree(0, 0, data.length - 1);
    }

    public E get(int index) {
        return (E) data[index];
    }

    public int size() {
        return data.length;
    }

    private static double log2(double num) {
        return Math.log(num) / LN_2;
    }

    private static int treeSize(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must gt or eq to 1");
        }
        int log2Result = (int) Math.ceil(log2(length) + 1);
        return pow2(log2Result) - 1;
    }

    private static int pow2(int pow) {
        if (pow < 0 || pow > 30) {
            throw new IllegalArgumentException("pow not in range [0, 30]");
        }
        return 1 << pow;
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = merger.merge((E) tree[leftTreeIndex], (E) tree[rightTreeIndex]);
    }

    private static int leftChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be equal to or greater than 0");
        }
        return (index << 1) + 1;
    }

    private static int rightChild(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index must be equal to or greater than 0");
        }
        return (index << 1) + 2;
    }
}
