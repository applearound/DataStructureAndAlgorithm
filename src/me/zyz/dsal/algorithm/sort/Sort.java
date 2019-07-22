package me.zyz.dsal.algorithm.sort;

public interface Sort {

    <E extends Comparable<E>> void sort(E[] arr);
}
