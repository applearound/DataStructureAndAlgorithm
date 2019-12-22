package me.zyz.dsal.set;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.collection.set.AvlSet;
import me.zyz.dsal.collection.set.BiasRbSet;
import me.zyz.dsal.collection.set.RbSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author zyz
 */
@Slf4j
public class SetTest {

    private final int MIN = 1;
    private final int MAX = 1000_0000;

    @BeforeEach
    public void say() {
        log.info("{}数据量测试", MAX - MIN + 1);
    }

    @Test
    public void test1() {
        AvlSet<Integer> avlSet = new AvlSet<>();
        long startTime = 0L;
        long endTime = 0L;

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            avlSet.add(i);
        }
        endTime = System.nanoTime();
        log.info("AVL 插入性能：{} s", (endTime - startTime) / 1_000_000_000.0);

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            if (!avlSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        log.info("AVL 查找性能：{} s", (endTime - startTime) / 1_000_000_000.0);
    }

    @Test
    public void test2() {
        BiasRbSet<Integer> biasRbSet = new BiasRbSet<>();
        long startTime = 0L;
        long endTime = 0L;

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            biasRbSet.add(i);
        }
        endTime = System.nanoTime();
        log.info("BiasRBTree 插入性能：{} s", (endTime - startTime) / 1_000_000_000.0);

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            if (!biasRbSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        log.info("BiasRBTree 查找性能：{} s", (endTime - startTime) / 1_000_000_000.0);
    }

    @Test
    public void test3() {
        RbSet<Integer> rbSet = new RbSet<>();
        long startTime = 0L;
        long endTime = 0L;

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            rbSet.add(i);
        }
        endTime = System.nanoTime();
        log.info("RBTree 插入性能：{} s", (endTime - startTime) / 1_000_000_000.0);

        startTime = System.nanoTime();
        for (int i = MIN; i <= MAX; i++) {
            if (!rbSet.contains(i)) {
                throw new IllegalStateException();
            }
        }
        endTime = System.nanoTime();
        log.info("RBTree 查找性能：{} s", (endTime - startTime) / 1_000_000_000.0);
    }
}
