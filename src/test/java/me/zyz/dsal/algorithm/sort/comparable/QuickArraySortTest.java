package me.zyz.dsal.algorithm.sort.comparable;

import org.junit.jupiter.api.BeforeAll;

class QuickArraySortTest extends SortTest {
    @BeforeAll
    static void init() {
        arraySort = new QuickArraySort();
    }
}
