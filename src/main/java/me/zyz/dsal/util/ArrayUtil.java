package me.zyz.dsal.util;

import lombok.NonNull;

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
    public static void swap(@NonNull final byte[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final short[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final char[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final int[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final long[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final float[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final double[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static void swap(@NonNull final boolean[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
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
    public static <E> void swap(@NonNull final E[] array, final int idx1, final int idx2) {
        if (idx1 < 0 || idx1 >= array.length) {
            throw new IndexOutOfBoundsException(idx1);
        }

        if (idx2 < 0 || idx2 >= array.length) {
            throw new IndexOutOfBoundsException(idx2);
        }

        if (idx1 == idx2) {
            return;
        }

        final E temp = array[idx1];
        array[idx1] = array[idx2];
        array[idx2] = temp;
    }

    public static int binSearch(@NonNull final byte[] array, final byte val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final short[] array, final short val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final char[] array, final char val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final int[] array, final int val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final long[] array, final long val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final float[] array, final float val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static int binSearch(@NonNull final double[] array, final double val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    public static <E extends Comparable<E>> int binSearch(@NonNull final E[] array, @NonNull final E val) {
        return binSearch0(array, val, 0, array.length - 1);
    }

    private static int binSearch0(final byte[] arr, final byte val, final int l, final int r) {
        if (l > r) {
            return -1;
        }

        final int  midx = l + ((r - l) >> 1);
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

        final int   midx = l + ((r - l) >> 1);
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

        final int  midx = l + ((r - l) >> 1);
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

        final int cmp = Integer.compare(val, mval);
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

        final int  midx = l + ((r - l) >> 1);
        final long mval = arr[midx];

        final int cmp = Long.compare(val, mval);
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

        final int   midx = l + ((r - l) >> 1);
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

        final int    midx = l + ((r - l) >> 1);
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
        final E   mval = arr[midx];

        int cmp = val.compareTo(mval);
        if (cmp < 0) {
            return binSearch0(arr, val, l, midx - 1);
        } else if (cmp > 0) {
            return binSearch0(arr, val, midx + 1, r);
        } else {
            return midx;
        }
    }

    public void sortBooleanArray(@NonNull final boolean[] array) {
        if (array.length <= 1) {
            return;
        }

        sortBooleanArray(array, 0, array.length - 1, true);
    }

    public void sortBooleanArrayDescending(@NonNull final boolean[] array) {
        if (array.length <= 1) {
            return;
        }

        sortBooleanArray(array, 0, array.length - 1, false);
    }

    public void sortBooleanArray(@NonNull final boolean[] array, final int startIdx, final int endIdx) {
        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException(startIdx);
        }

        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException(endIdx);
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("start index greater than end index");
        }

        if (startIdx == endIdx) {
            return;
        }

        sortBooleanArray(array, startIdx, endIdx, true);
    }

    public void sortBooleanArrayDescending(@NonNull final boolean[] array, final int startIdx, final int endIdx) {
        if (startIdx < 0 || startIdx >= array.length) {
            throw new IndexOutOfBoundsException(startIdx);
        }

        if (endIdx < 0 || endIdx >= array.length) {
            throw new IndexOutOfBoundsException(endIdx);
        }

        if (startIdx > endIdx) {
            throw new IllegalArgumentException("startIdx should less than or equal to endIdx");
        }

        if (startIdx == endIdx) {
            return;
        }

        sortBooleanArray(array, startIdx, endIdx, false);
    }

    private void sortBooleanArray(final boolean[] array, final int begin, final int end, final boolean theBigger) {
        for (int i = begin, count = 0; i <= end; ++i) {
            if (array[i] == theBigger) {
                continue;
            }

            final int cIdx = begin + count;
            array[cIdx] = !theBigger;
            if (i != cIdx) {
                array[i] = theBigger;
            }
            count++;
        }
    }
}
