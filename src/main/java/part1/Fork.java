package part1;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {

    private int number;
    private ReentrantLock lock = new ReentrantLock();

    public Fork(int number) {
        this.number = number;
    }

    public boolean lock(Long timeout, TimeUnit unit) throws InterruptedException {
        return lock.tryLock(timeout, unit);
    }

    public void unlock() {
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        return "Fork{" +
                "number=" + number +
                '}';
    }
}
