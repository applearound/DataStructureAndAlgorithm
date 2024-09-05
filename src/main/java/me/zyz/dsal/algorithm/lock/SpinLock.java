package me.zyz.dsal.algorithm.lock;

import java.util.concurrent.atomic.AtomicLong;

public class SpinLock {
    private static final long UNLOCKED = 0;
    // private static final long LOCKED   = 1;

    private final AtomicLong lock;

    public SpinLock() {
        this.lock = new AtomicLong(UNLOCKED);
    }

    private boolean holding() {
        return lock.get() == Thread.currentThread().threadId();
    }

    public void lock() {
        if (holding()) {
            throw new IllegalMonitorStateException("重复获取锁");
        }

        while (!lock.compareAndSet(UNLOCKED, Thread.currentThread().threadId())) ;
    }

    public void unlock() {
        if (!holding()) {
            throw new IllegalMonitorStateException("非锁持有线程");
        }

        lock.set(UNLOCKED);
    }
}
