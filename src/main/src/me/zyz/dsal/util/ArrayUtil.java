package me.zyz.dsal.util;

/**
 * @author yz
 */
public class ArrayUtil {
    private ArrayUtil() {
    }

    public static <E extends Comparable<E>> void swap(E[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    //TODO byte[] short[] int[] long[] char[] float[] double[]
    public static <E extends Comparable<E>> boolean binSearch(E[] arr, E val) {
        return binSearch0(arr, val, 0, arr.length - 1);
    }

    private static <E extends Comparable<E>> boolean binSearch0(E[] arr, E val, int l, int r) {
        if (l > r) {
            return false;
        }

        int mid = l + ((r - l) >> 1);
        int comp = val.compareTo(arr[mid]);
        if (comp < 0) {
            return binSearch0(arr, val, l, mid - 1);
        } else if (comp > 0) {
            return binSearch0(arr, val, mid + 1, r);
        } else {
            return true;
        }
    }
}
