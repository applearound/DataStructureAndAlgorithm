package me.zyz.dsal.algorithm.sort;

/**
 * @param <E> 元素类型
 * @author yezhou
 */
public interface Sort<E extends Comparable<E>> {
    /**
     * 将传入的数组进行排序
     *
     * @param arr 待排序数组
     */
    void sort(E[] arr);
}
