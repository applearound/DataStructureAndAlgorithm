//package me.zyz.dsal.set;
//
//import lombok.extern.slf4j.Slf4j;
//import me.zyz.dsal.collection.set.*;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//
///**
// * @author zyz
// */
//@Slf4j
//public class SetTest {
//
//    private static final int MIN = 1;
//    private static final int MAX = 5000000;
//
//    @BeforeAll
//    public static void say() {
//        log.info("{}数据量测试", MAX - MIN + 1);
//    }
//
//    private long insertTest(Set<Integer> set) {
//        long startTime = System.nanoTime();
//        for (int i = MIN; i <= MAX; i++) {
//            set.add(i);
//        }
//        long endTime = System.nanoTime();
//
//        return endTime - startTime;
//    }
//
//    private long queryTest(Set<Integer> set) {
//        long startTime = System.nanoTime();
//        for (int i = MIN; i <= MAX; i++) {
//            if (!set.contains(i)) {
//                throw new IllegalStateException();
//            }
//        }
//        long endTime = System.nanoTime();
//        return endTime - startTime;
//    }
//
//    private void testInsertAndQuery(Set<Integer> set) throws IllegalAccessException, InstantiationException {
//        long insertTime = insertTest(set);
//        log.info("{} 插入性能：{} ms", set.getClass().getSimpleName(), insertTime / 1_000_000.0);
//
//        long queryTime = queryTest(set);
//        log.info("{} 查找性能：{} ms", set.getClass().getSimpleName(), queryTime / 1_000_000.0);
//    }
//
//    @Test
//    public void test1() throws InstantiationException, IllegalAccessException {
//        testInsertAndQuery(new AvlSet<>());
//    }
//
//    @Test
//    public void test2() throws InstantiationException, IllegalAccessException {
//        testInsertAndQuery(new RecursionAvlSet<>());
//    }
//
//    @Test
//    public void test3() throws InstantiationException, IllegalAccessException {
//        testInsertAndQuery(new LeftLeaningRbSet<>());
//    }
//
//    @Test
//    public void test4() throws InstantiationException, IllegalAccessException {
//        testInsertAndQuery(new RbSet<>());
//    }
//}
