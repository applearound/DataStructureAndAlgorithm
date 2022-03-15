package me.zyz.dsal.collection.set;

import org.junit.jupiter.api.Test;

class RbTreeSetTest {
    @Test
    void testAdd() {
        final RbTreeSet<Integer> rbTreeSet = new RbTreeSet<>();

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.add(i);
        }

        assertEquals(1000, rbTreeSet.size());
    }

    @Test
    void testAddThenRemove() {
        final RbTreeSet<Integer> rbTreeSet = new RbTreeSet<>();

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.add(i);
        }

        assertEquals(1000, rbTreeSet.size());

        for (int i = 0; i < 1000; i++) {
            rbTreeSet.remove(i);
        }

        assertEquals(0, rbTreeSet.size());
    }
}
