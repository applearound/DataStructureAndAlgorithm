package me.zyz.dsal.lock;

import lombok.extern.slf4j.Slf4j;
import me.zyz.dsal.algorithm.lock.SpinLock;
import org.junit.jupiter.api.Test;

@Slf4j
class LockTest {
    @Test
    void testSpinLock() throws InterruptedException {
        final SpinLock spinLock = new SpinLock();

        final var t1 = new Thread(() -> {
            spinLock.lock();

            log.info("t1 锁获取成功！");
            try {
                Thread.sleep(10000L);
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("线程 {} 被中断", Thread.currentThread().getName());
            } finally {
                log.info("t1 准备放锁！");
                spinLock.unlock();
                log.info("t1 放锁！");
            }
        });

        final var t2 = new Thread(() -> {
            spinLock.lock();

            log.info("t2 锁获取成功！");
            try {
                Thread.sleep(10000L);
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("线程 {} 被中断", Thread.currentThread().getName());
            } finally {
                log.info("t2 准备放锁！");
                spinLock.unlock();
                log.info("t2 放锁！");
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
