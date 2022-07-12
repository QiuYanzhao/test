package com.example.demo.thread;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class TestAccount {
    public static void main(String[] args) {
        AccountCas accountCas = new AccountCas(10000);
        Account.demo(accountCas);
        System.out.println(accountCas.getBalance());
        AtomicInteger atomicInteger = new AtomicInteger(3);
        AtomicStampedReference<String> atomicReference = new AtomicStampedReference<String>("s",0);
        atomicReference.getReference();//获取到封装的数据
        atomicReference.getStamp();//获取到版本号
        boolean b = atomicReference.compareAndSet("s", "d", 0, 1);
        System.out.println(b);
        //AtomicReference,AtomicStampedReference,StomicMarkableReference
        AtomicMarkableReference<String> atomicMarkableReference = new AtomicMarkableReference<>("q",true);

    }
}
class AccountCas implements Account{
    private AtomicInteger balance;

    public AccountCas(int balance) {
        this.balance = new AtomicInteger(balance);
    }

    @Override public Integer getBalance() {
        return balance.get();
    }

    @Override public void withDraw(Integer amount) {
        while (true){
            int pre = balance.get();
            int next = pre - amount;
            if (balance.compareAndSet(pre,next)) {
                break;
            }
        }
    }
}
interface Account{
    Integer getBalance();

    void withDraw(Integer amoumt);

    static void demo(Account account){
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            threads.add(new Thread(() -> {
                account.withDraw(10);
            }));
        }
        long start = System.currentTimeMillis();
        threads.forEach(Thread::start);
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start + "ms");
    }
}
