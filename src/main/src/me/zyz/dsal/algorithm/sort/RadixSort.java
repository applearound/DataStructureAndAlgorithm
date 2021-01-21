package me.zyz.dsal.algorithm.sort;

/**
 * @author yezhou
 */
public class RadixSort implements Sort<Integer> {
    private static final int RADIX = 10;

    @Override
    public void sort(Integer[] arr) {
        int maxValue = arr[0];
        for (int i = 1; i < arr.length; ++i) {
            if (arr[i] > maxValue) {
                maxValue = arr[i];
            }
        }

        int exp = 1;
        Integer[] aux = new Integer[arr.length];

        while (maxValue / exp > 0) {
            int[] count = new int[RADIX];
            for (Integer e : arr) {
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
}
