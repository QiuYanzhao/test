package com.example.demo.thread;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.LinkedList;

public class ProducersConsumers {
    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue(2);
        //生产者
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
               messageQueue.put(new Message(id,"value" + id));
            },"生产者-" + i).start();
        }
        //消费者
        new Thread(() -> {
            while (true){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(messageQueue.take());
            }
        },"消费者").start();

    }
}

class MessageQueue{
    //保存消息的队列
    private LinkedList list = new LinkedList();
    //容量
    private int capcity;

    //消费消息
    public Message take(){
        synchronized (list){
            while (list.isEmpty()){
                try {
                    System.out.println("queue is empty");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Message message = (Message)list.removeFirst();
            list.notifyAll();
            return message;
        }
    }

    //生产消息
    public void put(Message message){
        synchronized (list){
            while (list.size() == capcity){
                try {
                    System.out.println("queue is full");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            list.addLast(message);
            list.notifyAll();
        }
    }

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }
}

@Value
@AllArgsConstructor
class Message{
    private int id;
    private Object value;

}
