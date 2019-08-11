package me.zyz.dsal.sort;

import me.zyz.dsal.algorithm.sort.*;
import me.zyz.dsal.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortTest {
    private static TestUtil testUtil;
    private static Integer[] testIntegerListRawData;

    private Integer[] testIntegerListData;

    @BeforeAll
    public static void setUpAll() {
        testUtil = TestUtil.getInstance();
        testIntegerListRawData = TestUtil.getInstance().randomIntegerArray(10000, 10000);
    }

    @BeforeEach
    public void setUp() {
        testIntegerListData = testIntegerListRawData.clone();
    }

    @AfterEach
    public void isSorted() {
        assertTrue(testUtil.isSorted(testIntegerListRawData, testIntegerListData));
        testIntegerListData = null;
    }

    @Test
    public void testBubbleArraySort() {
        testUtil.test(testIntegerListData, new BubbleArraySort<>());
    }

    @Test
    public void testSelectionArraySort() {
        testUtil.test(testIntegerListData, new SelectionArraySort<>());
    }

    @Test
    public void testInsertionArraySort() {
        testUtil.test(testIntegerListData, new InsertionArraySort<>());
    }

    @Test
    public void testShellArraySort() {
        testUtil.test(testIntegerListData, new ShellArraySort<>());
    }

    @Test
    public void testMergeArraySort() {
        testUtil.test(testIntegerListData, new MergeArraySort<>());
    }

    @Test
    public void testHeapArraySort() {
        testUtil.test(testIntegerListData, new HeapArraySort<>());
    }

    @Test
    public void testQuickArraySort() {
        testUtil.test(testIntegerListData, new QuickArraySort<>());
    }
}
