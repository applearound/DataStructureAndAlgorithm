package me.zyz.dsal.algorithm.sort.comparable;

import org.junit.jupiter.api.BeforeAll;

class HeapArraySortTest extends SortTest {
    @BeforeAll
    static void init() {
        arraySort = new HeapArraySort();
    }
}
