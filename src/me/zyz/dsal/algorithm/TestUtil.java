package me.zyz.dsal.algorithm;

import java.util.Arrays;
import java.util.Random;

public class TestUtil {

    private Random random = new Random();

    public Integer[] randomIntegerArray(int length, int bound) {
        Integer[] intArray = new Integer[length];
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = random.nextInt(bound);
        }
        return intArray;
    }

    public <E extends Comparable> boolean isSorted(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }

    public <E extends Comparable> void test(E[] arr, Sort sort) {
        long start = System.nanoTime();
        sort.sort(arr);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert isSorted(arr);
    }

    public <E extends Comparable> void testArraysSort(E[] arr) {
        long start = System.nanoTime();
        Arrays.sort(arr);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        assert isSorted(arr);
    }
}
