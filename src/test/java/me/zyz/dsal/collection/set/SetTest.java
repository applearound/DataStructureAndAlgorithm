package me.zyz.dsal.collection.set;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author zyz
 */
@Slf4j
class SetTest {
    @Test
    void testBstSet() {
        final BstSet<Integer> intSet = new BstSet<>();

        for (int i = 0; i < 1000; i++) {
            intSet.add(i);
        }

        log.debug("BST Set size: {}", intSet.size());
        Assertions.assertEquals(1000, intSet.size());

        int c = 0;
        for (final Integer integer : intSet) {
            log.debug("{} == {}", c, integer);
            Assertions.assertEquals(c++, integer);
        }

        for (int i = 0; i < 1000; i++) {
            intSet.remove(i);
        }
        log.debug("BST Set size: {}", intSet.size());
        Assertions.assertEquals(0, intSet.size());
    }
}
