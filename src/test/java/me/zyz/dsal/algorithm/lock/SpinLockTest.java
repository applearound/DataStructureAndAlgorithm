package me.zyz.dsal.algorithm.lock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.lang.invoke.VarHandle;

@Slf4j
class SpinLockTest {
    int noLockValue = 0;
    int lockValue = 0;

    volatile int a = 1;

    @Test
    void testNoLock() throws InterruptedException {
        noLockValue = 0;
        a = 2;
        System.out.println(a);
        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                noLockValue++;
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                noLockValue++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("value {}", noLockValue);
    }

    int x = 0;
    int y = 0;

    @RepeatedTest(value = 10000)
    void testReadWrite() throws InterruptedException {
        x = 0;
        y = 0;
        final Thread t1 = new Thread(() -> {
            x = 1;
            VarHandle.fullFence();
            System.out.print(y);
        });

        final Thread t2 = new Thread(() -> {
            y = 1;
            VarHandle.fullFence();
            System.out.print(x);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println();
        x = 0;
        y = 0;
    }

    Object obj;
    volatile int lock = 0;

    void testVolatile() throws InterruptedException {
        final Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            obj = new Object();
            lock = 1;
        });

        final Thread t2 = new Thread(() -> {
            while (lock == 0) {
                System.out.println("waiting!");
            }

            obj.getClass();
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    @Test
    void testLock() throws InterruptedException {
        final SpinLock spinLock = new SpinLock();

        final Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                spinLock.lock();
                lockValue++;
                spinLock.unlock();
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100000; i++) {
                spinLock.lock();
                lockValue++;
                spinLock.unlock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        log.debug("value {}", lockValue);
    }
}