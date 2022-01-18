package me.zyz.dsal.algorithm.sort.comparable;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.sort.ArraySort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
abstract class SortTest {
    private static final Random random = new Random();

    static ArraySort arraySort;

    @BeforeEach
    void logSelf() {
        log.debug("Use {}", arraySort.getClass());
    }

    @Test
    void sortByteArray() {
        final byte[] byteArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", byteArray);

        arraySort.sort(byteArray);

        log.debug("sorted: {}", byteArray);

        for (int i = 0; i < byteArray.length; i++) {
            assertEquals(i, byteArray[i]);
        }
    }

    @Test
    void sortBooleanArray() {
        final boolean[] booleanArray = {true, false, true, false, true};
        log.debug("original: {}", booleanArray);

        arraySort.sort(booleanArray);

        log.debug("sorted: {}", booleanArray);

        assertFalse(booleanArray[0]);
        assertFalse(booleanArray[1]);
        assertTrue(booleanArray[2]);
        assertTrue(booleanArray[3]);
        assertTrue(booleanArray[4]);
    }

    @Test
    void testSortShortArray() {
        final short[] shortArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", shortArray);

        arraySort.sort(shortArray);

        log.debug("sorted: {}", shortArray);

        for (int i = 0; i < shortArray.length; i++) {
            assertEquals(i, shortArray[i]);
        }
    }

    @Test
    void testSortCharArray() {
        final char[] charArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", charArray);

        arraySort.sort(charArray);

        log.debug("sorted: {}", charArray);

        for (int i = 0; i < charArray.length; i++) {
            assertEquals(i, charArray[i]);
        }
    }

    @Test
    void testSortIntArray() {
        final int[] intArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", intArray);

        arraySort.sort(intArray);

        log.debug("sorted: {}", intArray);

        for (int i = 0; i < intArray.length; i++) {
            assertEquals(i, intArray[i]);
        }
    }

    @Test
    void testSortLongArray() {
        final long[] longArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", longArray);

        arraySort.sort(longArray);

        log.debug("sorted: {}", longArray);

        for (int i = 0; i < longArray.length; i++) {
            assertEquals(i, longArray[i]);
        }
    }

    @Test
    void testSortFloatArray() {
        final float[] floatArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", floatArray);

        arraySort.sort(floatArray);

        log.debug("sorted: {}", floatArray);

        for (int i = 0; i < floatArray.length; i++) {
            assertEquals(i, floatArray[i]);
        }
    }

    @Test
    void testSortDoubleArray() {
        final double[] doubleArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", doubleArray);

        arraySort.sort(doubleArray);

        log.debug("sorted: {}", doubleArray);

        for (int i = 0; i < doubleArray.length; i++) {
            assertEquals(i, doubleArray[i]);
        }
    }

    @Test
    void testSortComparableArray() {
        final Integer[] integerArray = {4, 3, 2, 1, 0};
        log.debug("original: {}", (Object[]) integerArray);

        arraySort.sort(integerArray);

        log.debug("sorted: {}", (Object[]) integerArray);

        for (int i = 0; i < integerArray.length; i++) {
            assertEquals(i, integerArray[i]);
        }
    }

    @Test
    void testPerformance() {
        final int[] intArray = randomIntArray(10000, 0, 10000);

        log.debug("original: {}", intArray);

        final int[] arrayToSort = Arrays.copyOf(intArray, intArray.length);
        arraySort.sort(arrayToSort);

        log.debug("sorted: {}", arrayToSort);

        assertTrue(isSorted(intArray, arrayToSort));
    }

    /**
     * 生成随机int序列
     *
     * @param length 序列长度
     * @param low    下界（包含）
     * @param high   上界（包含）
     * @return Integer[]
     */
    static int[] randomIntArray(final int length, final int low, final int high) {
        if (length <= 0) {
            throw new IllegalArgumentException("invalid length");
        }

        if (low > high) {
            throw new IllegalArgumentException("low must be less equal than high");
        }

        return random.ints(length, low, high + 1).toArray();
    }

    static int[] almostSortedIntArray(final int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("invalid length");
        }

        int[] intArray = IntStream.rangeClosed(Integer.MIN_VALUE, Integer.MIN_VALUE + length + 1).toArray();
        for (int i = 0; i < (intArray.length / 1000) + 1; i++) {
            int index1 = random.nextInt(intArray.length);
            int index2 = random.nextInt(intArray.length);

            int temp = intArray[index1];
            intArray[index1] = intArray[index2];
            intArray[index2] = temp;
        }

        return intArray;
    }

    static boolean isSorted(final Object originalArray, final Object sortedArray) {
        if (!originalArray.getClass().isArray()) {
            throw new IllegalArgumentException("originalArray");
        }

        if (!sortedArray.getClass().isArray()) {
            throw new IllegalArgumentException("sortedArray");
        }

        final Map<Object, Integer> counterMap = new TreeMap<>();

        final int originalLength = Array.getLength(originalArray);
        for (int i = 0; i < originalLength; i++) {
            final Object e = Array.get(originalArray, i);

            counterMap.compute(e, (k, v) -> {
                if (v == null) {
                    return 1;
                } else {
                    return v + 1;
                }
            });
        }

        int i = 0;
        for (final Map.Entry<Object, Integer> entry : counterMap.entrySet()) {
            for (int j = 0; j < entry.getValue(); ++j) {
                final Comparable o = (Comparable) Array.get(sortedArray, i + j);
                if (o.compareTo(entry.getKey()) != 0) {
                    return false;
                }
            }
            i += entry.getValue();
        }

        return true;
    }
}
