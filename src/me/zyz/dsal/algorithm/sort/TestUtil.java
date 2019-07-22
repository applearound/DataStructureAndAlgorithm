package me.zyz.dsal.algorithm.sort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

public class TestUtil {
    private Random random = new Random();

    private static final TestUtil TEST_UTIL = new TestUtil();

    private TestUtil() {
    }

    public static TestUtil getInstance() {
        return TEST_UTIL;
    }

    public Integer[] randomIntegerArray(int length, int bound) {
        return randomIntegerArray(length, 0, bound);
    }

    /**
     * 生成随机Integer序列
     *
     * @param length 序列长度
     * @param low    下界（包含）
     * @param high   上界（包含）
     * @return Integer[]
     */
    public Integer[] randomIntegerArray(int length, int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("low must be less equal than high");
        }

        return random.ints(length, low, high + 1).boxed().toArray(Integer[]::new);
    }

    public Integer[] almostSameIntegerArray(int length) {
        Integer[] integerArray = new Integer[length];

        for (int i = 0; i < integerArray.length; i++) {
            integerArray[i] = random.nextInt(5);
        }

        return integerArray;
    }

    public Integer[] almostSortedIntegerArray(int low, int high) {
        if (low > high) {
            throw new IllegalArgumentException("low must be less equal than high");
        }

        Integer[] integerArray = IntStream.rangeClosed(low, high).boxed().toArray(Integer[]::new);
        for (int i = 0; i < (integerArray.length / 100) + 1; i++) {
            int index1 = random.nextInt(integerArray.length);
            int index2 = random.nextInt(integerArray.length);

            Integer temp = integerArray[index1];
            integerArray[index1] = integerArray[index2];
            integerArray[index2] = temp;
        }

        return integerArray;
    }

    public <E extends Comparable<E>> boolean isSorted(E[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }

        return true;
    }

    public <E extends Comparable<E>> void test(E[] arr, Sort sort) {
        long start = System.nanoTime();
        sort.sort(arr);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        if (!isSorted(arr)) {
            throw new IllegalStateException("not sorted: " + sort.getClass().getSimpleName());
        }
    }

    public <E extends Comparable<E>> void arraysSort(E[] arr) {
        long start = System.nanoTime();
        Arrays.sort(arr);
        System.out.println((System.nanoTime() - start) / 1_000_000.0 + "ms");
        if (!isSorted(arr)) {
            throw new IllegalStateException("not sorted: Arrays.sort()");
        }
    }
}
