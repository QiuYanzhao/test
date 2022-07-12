package com.example.demo.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AlternateOutput {
    static int flag = 1;
    static int loopNum = 0;
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        Condition condition3 = lock.newCondition();
        System.out.println();
        new Thread(() -> {
            try {
                lock.lock();
                while (true){
                    if (1 != flag){
                        try {
                            condition1.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if (loopNum == 15){
                        flag = 2;
                        condition2.signal();
                        break;
                    }else {
                        System.out.print("a");
                        loopNum++;
                        flag = 2;
                        condition2.signal();
                    }
                }
            }finally {
                lock.unlock();
            }

        },"a").start();

        new Thread(() -> {
                try {
                    lock.lock();
                    while (true){
                        if (2 != flag){
                            try {
                                condition2.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else if (loopNum == 15){
                            flag = 3;
                            condition3.signal();
                            break;
                        }else {
                            System.out.print("b");
                            flag = 3;
                            loopNum++;
                            condition3.signal();
                        }
                    }
                }finally {
                    lock.unlock();
                }

        },"b").start();

        new Thread(() -> {
                try {
                    lock.lock();
                    while (true){
                        if (3 != flag){
                            try {
                                condition1.signal();
                                condition3.await();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }else if (loopNum == 15){
                            break;
                        }else {
                            System.out.print("c");
                            loopNum++;
                            flag = 1;
                            condition1.signal();
                        }
                    }

                }finally {
                    lock.unlock();
                }
        },"c").start();

    }
}
