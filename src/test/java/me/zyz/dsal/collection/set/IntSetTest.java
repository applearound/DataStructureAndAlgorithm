package me.zyz.dsal.collection.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
class IntSetTest {
    @Test
    void test() {
        final IntSet intSet = new IntSet();
        for (int i = 0; i < 1000; i++) {
            assertTrue(intSet.add(i), String.format("%d", i));
            assertTrue(intSet.contains(i));

            assertTrue(intSet.remove(i), String.format("%d", i));
            assertFalse(intSet.contains(i));
        }

        assertFalse(intSet.contains(-1));
    }

    @Test
    void test1() {
        final Set<Integer> intSet = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            assertTrue(intSet.add(i), String.format("%d", i));
            assertTrue(intSet.contains(i));

            assertTrue(intSet.remove(i), String.format("%d", i));
            assertFalse(intSet.contains(i));
        }

        assertFalse(intSet.contains(-1));
    }
}
