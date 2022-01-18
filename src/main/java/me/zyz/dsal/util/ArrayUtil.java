package me.zyz.dsal.util;

import java.util.Objects;

/**
 * @author yz
 */
public final class ArrayUtil {
    private ArrayUtil() {
    }

    /**
     * 交换byte数组中两个元素的位置
     *
     * @param array byte数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final byte[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final byte temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换short数组中两个元素的位置
     *
     * @param array short数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final short[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final short temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换char数组中两个元素的位置
     *
     * @param array char数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final char[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final char temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换int数组中两个元素的位置
     *
     * @param array int数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final int[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final int temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换long数组中两个元素的位置
     *
     * @param array long数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final long[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final long temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换float数组中两个元素的位置
     *
     * @param array float数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final float[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final float temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换double数组中两个元素的位置
     *
     * @param array double数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final double[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final double temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换boolean数组中两个元素的位置
     *
     * @param array boolean数组
     * @param idx1  索引1
     * @param idx2  索引2
     */
    public static void swap(final boolean[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final boolean temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    /**
     * 交换数组中两个元素的位置
     *
     * @param array 数组
     * @param idx1  索引1
     * @param idx2  索引2
     * @param <E>   元素类型
     */
    public static <E> void swap(final E[] array, final int idx1, final int idx2) {
        Objects.requireNonNull(array, "array");

        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException("idx1");
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException("idx2");
        }

        if (idx1 == idx2) {
            return;
        }

        final E temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    public static int binSearch(final byte[] array, final byte val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final short[] array, final short val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final char[] array, final char val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final int[] array, final int val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final long[] array, final long val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final float[] array, final float val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(final double[] array, final double val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> int binSearch(final E[] array, final E val) {
        checkBinSearchNull(array, val);
        return binSearch0(array, val, 0, array.length - 1);
    }

    private static void checkBinSearchNull(final Object array, final Object val) {
        Objects.requireNonNull(array, "array");
        Objects.requireNonNull(val, "val");
    }

    private static int binSearch0(final byte[] arr, final byte val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final byte mval = arr[midx];

        final int cmp = val - mval;
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final short[] arr, final short val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final short mval = arr[midx];

        final int cmp = val - mval;
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final char[] arr, final char val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final char mval = arr[midx];

        final int cmp = val - mval;
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final int[] arr, final int val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final int mval = arr[midx];

        final int cmp = val < mval ? -1 : val == mval ? 0 : 1;
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final long[] arr, final long val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final long mval = arr[midx];

        final int cmp = val < mval ? -1 : val == mval ? 0 : 1;
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final float[] arr, final float val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final float mval = arr[midx];

        final int cmp = Float.compare(val, mval);
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static int binSearch0(final double[] arr, final double val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final double mval = arr[midx];

        final int cmp = Double.compare(val, mval);
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    private static <E extends Comparable<E>> int binSearch0(final E[] arr, final E val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int midx = l + ((r - l) >> 1);
        final E mval = arr[midx];

        int cmp = val.compareTo(mval);
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    public void sortBooleanArray(final boolean[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sortBooleanArray(array, 0, array.length - 1, true);
    }

    public void sortBooleanArrayDescending(final boolean[] array) {
        Objects.requireNonNull(array, "array");

        if (array.length <= 1) {
            return;
        }

        sortBooleanArray(array, 0, array.length - 1, false);
    }

    public void sortBooleanArray(final boolean[] array, final int startIdx, final int endIdx) {
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

        sortBooleanArray(array, startIdx, endIdx, true);
    }

    public void sortBooleanArrayDescending(final boolean[] array, final int startIdx, final int endIdx) {
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

        sortBooleanArray(array, startIdx, endIdx, false);
    }

    private void sortBooleanArray(final boolean[] array, final int startIdx, final int endIdx, final boolean theBigger) {
        for (int i = startIdx, count = 0; i <= endIdx; ++i) {
            if (array[i] != theBigger) {
                final int cIdx = startIdx + count;
                array[cIdx] = !theBigger;
                if (i != cIdx) {
                    array[i] = theBigger;
                }
                ++count;
            }
        }
    }
}
