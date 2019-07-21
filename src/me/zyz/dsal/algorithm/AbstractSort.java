package me.zyz.dsal.algorithm;

/**
 * @author yezhou
 */
public abstract class AbstractSort implements Sort {

    @Override
    public abstract <E extends Comparable<E>> void sort(E[] arr);

    protected <E extends Comparable> void swap(E[] arr, int index1, int index2) {
        if (index1 == index2) {
            return;
        }

        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
