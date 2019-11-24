package me.zyz.dsal.set;

import me.zyz.dsal.collection.set.AvlSet;
import me.zyz.dsal.collection.set.RbSet;
import org.junit.jupiter.api.Test;

/**
 * @author zyz
 */
public class SetTest {

    @Test
    public void test1() {
        AvlSet<Integer> avlSet = new AvlSet<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            avlSet.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if (!avlSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000);
    }

    @Test
    public void test2() {
        RbSet<Integer> rbSet = new RbSet<>();

        long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            rbSet.add(i);
        }
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000);

        startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            if (!rbSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1_000_000);
    }
}
