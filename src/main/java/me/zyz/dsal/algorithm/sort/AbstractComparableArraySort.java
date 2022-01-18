package me.zyz.dsal.algorithm.sort;

import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.Objects;

public abstract class AbstractComparableArraySort implements ArraySort {
    protected final Comparator comparator;

    protected AbstractComparableArraySort() {
        this(false);
    }

    protected AbstractComparableArraySort(final boolean descending) {
        this.comparator = descending ? Comparator.reverseOrder() : Comparator.naturalOrder();
    }

    @Override
    public void sort(final byte[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final boolean[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final short[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final char[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final int[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final long[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final float[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final double[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public <E extends Comparable<E>> void sort(final E[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sort0(array, 0, array.length - 1);
    }

    @Override
    public void sort(final byte[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final boolean[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final short[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final char[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final int[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final long[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final float[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public void sort(final double[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    @Override
    public <E extends Comparable<E>> void sort(final E[] array, final int startIdx, final int endIdx) {
        Objects.requireNonNull(array, "array");

        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException("startIdx");
        }
        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException("endIdx");
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sort0(array, startIdx, endIdx);
    }

    protected abstract void sort0(Object array, int startIdx, int endIdx);

    protected static void swap(final Object array, final int idx1, final int idx2) {
        final Object value1 = Array.get(array, idx1);

        Array.set(array, idx1, Array.get(array, idx2));
        Array.set(array, idx2, value1);
    }

    protected static void moveTo(final Object array, final int from, final int to) {
        Array.set(array, to, Array.get(array, from));
    }

    protected int compare(final Object value1, final Object value2) {
        return comparator.compare(value1, value2);
    }
}
