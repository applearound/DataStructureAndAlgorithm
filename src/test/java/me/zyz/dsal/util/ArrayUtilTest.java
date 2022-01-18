package me.zyz.dsal.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@Slf4j
class ArrayUtilTest {
    @Test
    void testSwapPrimitiveByte() {
        final byte num1 = 1;
        final byte num2 = 2;

        final byte[] byteArray = new byte[2];
        byteArray[0] = num1;
        byteArray[1] = num2;

        ArrayUtil.swap(byteArray, 0, 1);
        assertEquals(num1, byteArray[1]);
        assertEquals(num2, byteArray[0]);
    }

    @Test
    void testSwapPrimitiveShort() {
        final short num1 = 1;
        final short num2 = 2;

        final short[] shortArray = new short[2];
        shortArray[0] = num1;
        shortArray[1] = num2;

        ArrayUtil.swap(shortArray, 0, 1);
        assertEquals(num1, shortArray[1]);
        assertEquals(num2, shortArray[0]);
    }

    @Test
    void testSwapPrimitiveChar() {
        final char num1 = 1;
        final char num2 = 2;

        final char[] charArray = new char[2];
        charArray[0] = num1;
        charArray[1] = num2;

        ArrayUtil.swap(charArray, 0, 1);
        assertEquals(num1, charArray[1]);
        assertEquals(num2, charArray[0]);
    }

    @Test
    void testSwapPrimitiveInt() {
        final int num1 = 1;
        final int num2 = 2;

        final int[] intArray = new int[2];
        intArray[0] = num1;
        intArray[1] = num2;

        ArrayUtil.swap(intArray, 0, 1);
        assertEquals(num1, intArray[1]);
        assertEquals(num2, intArray[0]);
    }

    @Test
    void testSwapPrimitiveLong() {
        final long num1 = 1;
        final long num2 = 2;

        final long[] longArray = new long[2];
        longArray[0] = num1;
        longArray[1] = num2;

        ArrayUtil.swap(longArray, 0, 1);
        assertEquals(num1, longArray[1]);
        assertEquals(num2, longArray[0]);
    }

    @Test
    void testSwapPrimitiveFloat() {
        final float num1 = 1.0f;
        final float num2 = 2.0f;

        final float[] floatArray = new float[2];
        floatArray[0] = num1;
        floatArray[1] = num2;

        ArrayUtil.swap(floatArray, 0, 1);
        assertEquals(num1, floatArray[1]);
        assertEquals(num2, floatArray[0]);
    }

    @Test
    void testSwapPrimitiveDouble() {
        final double num1 = 1.0;
        final double num2 = 2.0;

        final double[] doubleArray = new double[2];
        doubleArray[0] = num1;
        doubleArray[1] = num2;

        ArrayUtil.swap(doubleArray, 0, 1);
        assertEquals(num1, doubleArray[1]);
        assertEquals(num2, doubleArray[0]);
    }

    @Test
    void testSwapPrimitiveBoolean() {
        final boolean num1 = true;
        final boolean num2 = false;

        final boolean[] booleanArray = new boolean[2];
        booleanArray[0] = num1;
        booleanArray[1] = num2;

        ArrayUtil.swap(booleanArray, 0, 1);
        assertEquals(num1, booleanArray[1]);
        assertEquals(num2, booleanArray[0]);
    }

    @Test
    void testSwap() {
        final Object num1 = new Object();
        final Object num2 = new Object();

        final Object[] objectArray = new Object[2];
        objectArray[0] = num1;
        objectArray[1] = num2;

        ArrayUtil.swap(objectArray, 0, 1);
        assertSame(num1, objectArray[1]);
        assertSame(num2, objectArray[0]);
    }

    @Test
    void testBinSearchPrimitiveByte() {
        final byte[] byteArray = {0, 1, 2, 3, 4};

        for (int i = 0; i < byteArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(byteArray, byteArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(byteArray, (byte) -1));
    }

    @Test
    void testBinSearchPrimitiveShort() {
        final int[] shortArray = {0, 1, 2, 3, 4};

        for (int i = 0; i < shortArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(shortArray, shortArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(shortArray, -1));
    }

    @Test
    void testBinSearchPrimitiveChar() {
        final char[] charArray = {0, 1, 2, 3, 4};

        for (int i = 0; i < charArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(charArray, charArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(charArray, (char) -1));
    }

    @Test
    void testBinSearchPrimitiveInt() {
        final int[] intArray = {0, 1, 2, 3, 4};

        for (int i = 0; i < intArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(intArray, intArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(intArray, -1));
    }

    @Test
    void testBinSearchPrimitiveLong() {
        final long[] longArray = {0L, 1L, 2L, 3L, 4L};

        for (int i = 0; i < longArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(longArray, longArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(longArray, -1));
    }

    @Test
    void testBinSearchPrimitiveFloat() {
        final float[] floatArray = {0.0f, 1.0f, 2.0f, 3.0f, 4.0f};

        for (int i = 0; i < floatArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(floatArray, floatArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(floatArray, -1));
    }

    @Test
    void testBinSearchPrimitiveDouble() {
        final double[] doubleArray = {0.0, 1.0, 2.0, 3.0, 4.0};

        for (int i = 0; i < doubleArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(doubleArray, doubleArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(doubleArray, -1));
    }

    @Test
    void binSearch() {
        final Integer[] integerArray = {0, 1, 2, 3, 4};

        for (int i = 0; i < integerArray.length; i++) {
            assertEquals(i, ArrayUtil.binSearch(integerArray, integerArray[i]));
        }
        assertEquals(-1, ArrayUtil.binSearch(integerArray, -1));
    }
}
