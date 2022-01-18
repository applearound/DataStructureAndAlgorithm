package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class RadixSort implements ArraySort {
    private static final int RADIX = 10;

    @Override
    public void sort(byte[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        int exp = 1;
        byte[] aux = new byte[arr.length];

        while (maxValue / exp > 0) {
            int[] count = new int[RADIX];
            for (byte e : arr) {
                count[(e / exp) % 10] += 1;
            }

            for (int i = 1; i < count.length; i++) {
                count[i] += count[i - 1];
            }

            for (int i = arr.length - 1; i >= 0; i--) {
                aux[--count[(arr[i] / exp) % 10]] = arr[i];
            }

            System.arraycopy(aux, 0, arr, 0, arr.length);

            exp *= 10;
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
        throw new UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }
}
