package me.zyz.dsal.algorithm.sort;

/**
 * @param <E> 数组元素类型
 * @author yezhou
 */
public interface ISort<E> {
    /**
     * 将传出的数组进行排序
     *
     * @param arr 待排序数组
     */
    void sort(E[] arr);
}
