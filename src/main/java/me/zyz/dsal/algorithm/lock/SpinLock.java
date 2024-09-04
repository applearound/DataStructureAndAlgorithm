package me.zyz.dsal.algorithm.lock;

import java.lang.invoke.VarHandle;
import java.util.concurrent.atomic.AtomicLong;

public class SpinLock {
    private static final long UNLOCKED = 0;
    private static final long LOCKED   = 1;

    private final AtomicLong lock;
    private       long       lockThread;

    public SpinLock() {
        this.lock       = new AtomicLong(UNLOCKED);
        this.lockThread = 0L;
    }

    private boolean holding() {
        return lock.get() == LOCKED && lockThread == Thread.currentThread().threadId();
    }

    public void lock() {
        if (holding()) {
            throw new IllegalMonitorStateException("重复获取锁");
        }

        while (!lock.compareAndSet(UNLOCKED, LOCKED)) ;

        lockThread = Thread.currentThread().threadId();
    }

    public void unlock() {
        if (!holding()) {
            throw new IllegalMonitorStateException("非锁持有线程");
        }

        lockThread = 0;

        VarHandle.fullFence();

        lock.set(UNLOCKED);
    }
}
