package com.example.demo.thread;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
public class Guarded {
    public static void main(String[] args) {
        GuardedObject guardedObject = new GuardedObject();
        new Thread(() -> {
            log.debug("等待结果");
            System.out.println(guardedObject.get(2000));
        },"t1").start();

        new Thread(() -> {
            synchronized (guardedObject){
                log.debug("结果开始产生");
                try {
                    guardedObject.wait(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.debug("产生结果了");
                int[] ints = new int[]{1,2,3};
                guardedObject.complete(Arrays.toString(ints));
            }
        },"t2").start();
    }
}



@Data
@AllArgsConstructor
@NoArgsConstructor
class GuardedObject{

    private int id;
    private Object response;

    public GuardedObject(int generateId) {
        this.id = generateId;
    }

    public Object get(long timeout){
        synchronized (this){
            long begin = System.currentTimeMillis();
            long passtime = 0;
            while (response == null){
                if (passtime >= timeout){
                    System.out.println("chaoshi");
                    break;
                }
                try {
                    this.wait(timeout - passtime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                passtime = System.currentTimeMillis() - begin;
            }
            return response;
        }
    }

    public void complete(Object response){
        synchronized (this){
            this.response = response;
            this.notifyAll();
        }
    }
}
