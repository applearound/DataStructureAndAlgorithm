package me.zyz.dsal.algorithm.sort.comparable;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@Slf4j
class InsertionArraySortTest extends SortTest {
    @BeforeAll
    static void init() {
        arraySort = new InsertionArraySort();
    }

    @Test
    void standaloneTest() {
        final int[] intArray = randomIntArray(10000, 0, 10000);

        final InsertionArraySort insertionArraySort = new InsertionArraySort();
        insertionArraySort.sortedIndex(intArray).forEach(i -> {
            log.debug("{}", intArray[i]);
        });
    }
}
