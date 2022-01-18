package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public interface ArraySort {
    void sort(byte[] array);

    void sort(boolean[] array);

    void sort(short[] array);

    void sort(char[] array);

    void sort(int[] array);

    void sort(long[] array);

    void sort(float[] array);

    void sort(double[] array);

    <E extends Comparable<E>> void sort(E[] array);

    void sort(byte[] array, int startIdx, int endIdx);

    void sort(boolean[] array, int startIdx, int endIdx);

    void sort(short[] array, int startIdx, int endIdx);

    void sort(char[] array, int startIdx, int endIdx);

    void sort(int[] array, int startIdx, int endIdx);

    void sort(long[] array, int startIdx, int endIdx);

    void sort(float[] array, int startIdx, int endIdx);

    void sort(double[] array, int startIdx, int endIdx);

    <E extends Comparable<E>> void sort(E[] array, int startIdx, int endIdx);
}
