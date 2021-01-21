package me.zyz.dsal.sort;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.sort.*;
import me.zyz.dsal.util.TestUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class SortTest {
    private static TestUtil testUtil;
    private static Integer[] testIntegerListRawData;

    private Integer[] testIntegerListData;

    @BeforeAll
    static void setUpAll() {
        testUtil = TestUtil.getInstance();
        testIntegerListRawData = TestUtil.getInstance().randomIntegerArray(50000, 30000);
    }

    @BeforeEach
    void setUp() {
        testIntegerListData = testIntegerListRawData.clone();
    }

    @AfterEach
    void isSorted() {
        assertTrue(testUtil.isSorted(testIntegerListRawData, testIntegerListData));
        testIntegerListData = null;
    }

    @Test
    void testBubbleArraySort() {
        testUtil.test(testIntegerListData, new BubbleArraySort<>());
    }

    @Test
    void testSelectionArraySort() {
        testUtil.test(testIntegerListData, new SelectionArraySort<>());
    }

    @Test
    void testInsertionArraySort() {
        testUtil.test(testIntegerListData, new InsertionArraySort<>());
    }

    @Test
    void testShellArraySort() {
        testUtil.test(testIntegerListData, new ShellArraySort<>());
    }

    @Test
    void testMergeArraySort() {
        testUtil.test(testIntegerListData, new MergeArraySort<>());
    }

    @Test
    void testHeapArraySort() {
        testUtil.test(testIntegerListData, new HeapArraySort<>());
    }

    @Test
    void testQuickArraySort() {
        testUtil.test(testIntegerListData, new QuickArraySort<>());
    }

    @Test
    void testRadixSort() {
        testUtil.test(testIntegerListData, new RadixSort());
    }

    @Test
    void testBucketSort() {
        testUtil.test(testIntegerListData, new BucketSort());
    }
}
