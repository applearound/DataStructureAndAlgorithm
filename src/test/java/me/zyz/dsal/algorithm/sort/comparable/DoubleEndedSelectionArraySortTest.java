package me.zyz.dsal.algorithm.sort.comparable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;

@Slf4j
class DoubleEndedSelectionArraySortTest extends SortTest {
    @BeforeAll
    static void init() {
        arraySort = new DoubleEndedSelectionArraySort();
    }
}
