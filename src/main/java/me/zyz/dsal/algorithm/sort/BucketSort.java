package me.zyz.dsal.algorithm.sort;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author yz
 */
public class BucketSort implements ArraySort {
    @Override
    public void sort(byte[] arr) {
        Deque<Byte>[] deques = new Deque[arr.length];
        for (int i = 0; i < deques.length; i++) {
            deques[i] = new LinkedList<>();
        }
        int maxValue = arr[0];
        for (byte integer : arr) {
            if (integer > maxValue) {
                maxValue = integer;
            }
        }
        int maxLength = String.valueOf(maxValue).length();

        int divider = 1;
        for (int i = 0; i < maxLength; i++) {
            divider *= 10;
            for (byte aLong : arr) {
                deques[aLong % divider].add(aLong);
            }

            int j = 0;
            for (Deque<Byte> deque : deques) {
                while (!deque.isEmpty()) {
                    arr[j++] = deque.pop();
                }
            }
        }
    }

    @Override
    public void sort(boolean[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(short[] array) {
    }

    @Override
    public void sort(char[] array) {

    }

    @Override
    public void sort(int[] array) {

    }

    @Override
    public void sort(long[] array) {
    }

    @Override
    public void sort(float[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(double[] array) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array) {
        if (array instanceof Byte[] ||
                array instanceof Short[] ||
                array instanceof Character[] ||
                array instanceof Integer[] ||
                array instanceof Long[]) {

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public void sort(byte[] array, int startIdx, int endIdx) {

    }

    @Override
    public void sort(boolean[] array, int startIdx, int endIdx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(short[] array, int startIdx, int endIdx) {

    }

    @Override
    public void sort(char[] array, int startIdx, int endIdx) {

    }

    @Override
    public void sort(int[] array, int startIdx, int endIdx) {

    }

    @Override
    public void sort(long[] array, int startIdx, int endIdx) {

    }

    @Override
    public void sort(float[] array, int startIdx, int endIdx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sort(double[] array, int startIdx, int endIdx) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array, int startIdx, int endIdx) {
        if (array instanceof Byte[] ||
                array instanceof Short[] ||
                array instanceof Character[] ||
                array instanceof Integer[] ||
                array instanceof Long[]) {

        } else {
            throw new UnsupportedOperationException();
        }
    }
}
