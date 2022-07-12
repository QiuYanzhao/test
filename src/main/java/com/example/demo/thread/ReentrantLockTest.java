package com.example.demo.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class ReentrantLockTest {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                log.debug("try to get lock");
                if (!lock.tryLock(2, TimeUnit.SECONDS)) {
                    log.debug("get lock fail");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"t1");

        lock.lock();
        log.debug("main thread get the lock");
        t1.start();
        Thread.sleep(500);
        lock.unlock();
        log.debug("main release the lock");
    }
}
