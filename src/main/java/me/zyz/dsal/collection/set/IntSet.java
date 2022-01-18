package me.zyz.dsal.collection.set;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

public class IntSet implements Set<Integer> {
    private long[] map = new long[67108864];
    private int sz = 0;

    @Override
    public int size() {
        return sz;
    }

    @Override
    public boolean isEmpty() {
        return sz == 0;
    }

    @Override
    public boolean contains(final Object o) {
        if (!(o instanceof Integer)) {
            return false;
        }

        final int v = (Integer) o;

        final int idx = (int) ((long) v - Integer.MIN_VALUE) >>> 6;
        final int offset = (int) ((long) v - Integer.MIN_VALUE) & 63;

        final long m = 1L << (63 - offset);

        return (map[idx] & m) != 0;
    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(final Integer integer) {
        final int v = Objects.requireNonNull(integer);

        final int idx = (int) ((long) v - Integer.MIN_VALUE) >>> 6;
        final int offset = (int) ((long) v - Integer.MIN_VALUE) & 63;

        final long m = 1L << (63 - offset);
        final long o = map[idx];

        map[idx] = o | m;

        sz++;

        return map[idx] != o;
    }

    @Override
    public boolean remove(final Object obj) {
        if (!(obj instanceof Integer)) {
            return false;
        }

        final int v = (Integer) obj;

        final int idx = (int) ((long) v - Integer.MIN_VALUE) >>> 6;
        final int offset = (int) ((long) v - Integer.MIN_VALUE) & 63;

        final long m = ~(1L << (63 - offset));
        final long o = map[idx];

        map[idx] = o & m;

        sz--;

        return map[idx] != o;
    }

    @Override
    public boolean containsAll(final Collection<?> c) {
        Objects.requireNonNull(c);

        for (final Object o : c) {
            if (!contains(o)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(final Collection<? extends Integer> c) {
        Objects.requireNonNull(c);

        boolean flag = false;
        for (final Integer integer : c) {
            if (add(integer) && !flag) {
                flag = true;
            }
        }

        return flag;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        map = new long[67108864];
    }
}
