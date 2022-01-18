package me.zyz.dsal.collection.tree.segment;

import me.zyz.dsal.util.MathUtil;

import java.util.Arrays;

/**
 * @author yz
 */
public class SegmentTree<E> {
    /**
     * (Integer.MAX_VALUE - 1) / 2
     */
    private static final int LAST_INDEX_HAS_LEFT_CHILD = (Integer.MAX_VALUE - 1) >> 1;
    /**
     * (Integer.MAX_VALUE - 2) / 2
     */
    private static final int LAST_INDEX_HAS_RIGHT_CHILD = (Integer.MAX_VALUE - 2) >> 1;

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

    public E getResult(int l, int r) {
        if (l > r || l < 0 || r >= size()) {
            throw new IllegalArgumentException("0 <= l <= r < size()");
        }
        return getResult0(0, 0, data.length - 1, l, r);
    }

    public void update(int index, E val) {
        data[index] = val;


    }

    private E getResult0(int treeIndex, int l, int r, int l0, int r0) {
        if (l == l0 && r == r0) {
            return (E) tree[treeIndex];
        }

        int mid = l + (r - l) / 2;

        E left = null;
        if (l0 <= mid) {
            int leftChildIndex = leftChild(treeIndex);
            left = getResult0(leftChildIndex, l, mid, l0, Math.min(mid, r0));
        }

        E right = null;
        if (r0 > mid) {
            int rightChildIndex = rightChild(treeIndex);
            right = getResult0(rightChildIndex, mid + 1, r, Math.max(mid + 1, l0), r0);
        }

        if (left != null && right != null) {
            return merger.merge(left, right);
        }
        return left != null ? left : right;
    }

    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftChildIndex, l, mid);
        buildSegmentTree(rightChildIndex, mid + 1, r);

        tree[treeIndex] = merger.merge((E) tree[leftChildIndex], (E) tree[rightChildIndex]);
    }

    private static int leftChild(int index) {
        if (index < 0 || index > LAST_INDEX_HAS_LEFT_CHILD) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return (index << 1) + 1;
    }

    private static int rightChild(int index) {
        if (index < 0 || index > LAST_INDEX_HAS_RIGHT_CHILD) {
            throw new IndexOutOfBoundsException(String.valueOf(index));
        }
        return (index << 1) + 2;
    }

    private static int treeSize(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("length must gt or eq to 1");
        }
        int log2Result = (int) Math.ceil(MathUtil.log2(length) + 1);
        return MathUtil.pow2(log2Result) - 1;
    }

    public static void main(String[] args) {
        Integer[] integers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(integers, (n1, n2) -> n1 + n2);
        System.out.println(segmentTree.getResult(8, 9));
    }
}
