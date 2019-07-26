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
}
