package me.zyz.dsal.util;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.sort.Sort;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.IntStream;

@Slf4j
public class TestUtil {
    private final Random random = new Random();

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
        for (int i = 0; i < (integerArray.length / 1000) + 1; i++) {
            int index1 = random.nextInt(integerArray.length);
            int index2 = random.nextInt(integerArray.length);

            Integer temp = integerArray[index1];
            integerArray[index1] = integerArray[index2];
            integerArray[index2] = temp;
        }

        return integerArray;
    }

    public <E extends Comparable<E>> boolean isSorted(E[] origin, E[] arr) {
        Map<E, Integer> countMap = new TreeMap<>();
        for (E e : origin) {
            if (countMap.containsKey(e)) {
                countMap.put(e, countMap.get(e) + 1);
            } else {
                countMap.put(e, 1);
            }
        }

        int i = 0;
        for (Map.Entry<E, Integer> eIntegerEntry : countMap.entrySet()) {
            for (int j = 0; j < eIntegerEntry.getValue(); ++j) {
                if (arr[i + j].compareTo(eIntegerEntry.getKey()) != 0) {
                    return false;
                }
            }
            i += eIntegerEntry.getValue();
        }

        return true;
    }

    public <E extends Comparable<E>> void test(E[] arr, Sort<E> sort) {
        final long start = System.nanoTime();
        sort.sort(arr);
        final long end = System.nanoTime();
        log.info(sort.getClass().getSimpleName() + " 用时: " + (end - start) / 1_000_000.0 + "ms ");
    }
}
