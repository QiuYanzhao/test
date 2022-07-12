package com.example.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ConditionVariable {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        Thread t1 = new Thread(() -> {
            lock.tryLock();
            try {
                log.debug("t1 start");
                c1.await();
                log.debug("t1 continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                log.debug("t1 start");
                c2.await();
                log.debug("t1 continue");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }, "t2");

        t1.start();
        t2.start();

        Thread.sleep(1000);
        lock.lock();
        c1.signal();
        lock.unlock();
    }
}
