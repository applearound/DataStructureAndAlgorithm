package me.zyz.dsal.set;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.set.AvlSet;
import me.zyz.dsal.collection.set.RbSet;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 * @author zyz
 */
@Slf4j
public class SetTest {

    @BeforeAll
    public static void say() {
        log.info("1000万数据量测试");
    }

    @Test
    public void test1() {
        AvlSet<Integer> avlSet = new AvlSet<>();
        long startTime = 0L;
        long endTime = 0L;

        startTime = System.nanoTime();
        for (int i = 0; i < 50000000; i++) {
            avlSet.add(i);
        }
        endTime = System.nanoTime();
        log.info("AVL 插入性能：{} s", (endTime - startTime) / 1_000_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < 50000000; i++) {
            if (!avlSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        log.info("AVL 查找性能：{} s", (endTime - startTime) / 1_000_000_000.0);
    }

    @Test
    public void test2() {
        RbSet<Integer> rbSet = new RbSet<>();
        long startTime = 0L;
        long endTime = 0L;

        startTime = System.nanoTime();
        for (int i = 0; i < 50000000; i++) {
            rbSet.add(i);
        }
        endTime = System.nanoTime();
        log.info("RBTree 插入性能：{} s", (endTime - startTime) / 1_000_000_000.0);

        startTime = System.nanoTime();
        for (int i = 0; i < 50000000; i++) {
            if (!rbSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        log.info("RBTree 查找性能：{} s", (endTime - startTime) / 1_000_000_000.0);
    }
}
