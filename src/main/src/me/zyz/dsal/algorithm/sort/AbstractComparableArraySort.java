package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public abstract class AbstractComparableArraySort<E extends Comparable<E>> implements ComparableSort<E> {
    protected static <E extends Comparable<E>> void swap(E[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
