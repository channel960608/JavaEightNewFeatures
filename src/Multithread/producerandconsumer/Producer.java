package Multithread.producerandconsumer;


import Multithread.Task;

import java.util.*;
import java.util.concurrent.*;

public class Producer implements Runnable {

    private List<Task> buffer;

    public Producer(List<Task> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while(true) {
            synchronized (buffer) {
                while(buffer.size() >= Constants.MAX_BUFFER_SIZE) {
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Task task = new Task();
                buffer.add(task);
                buffer.notifyAll();
                System.out.println("Producer[" + Thread.currentThread().getName() + "] put " + task);
            }
        }
    }




    public static void main(String... args) {
        List<Task> buffer = new ArrayList<>(Constants.MAX_BUFFER_SIZE);
        ExecutorService es =Executors.newFixedThreadPool(Constants.NUM_OF_CONSUMER);
        for (int i = 1; i <= Constants.NUM_OF_PRODUCER; ++i) {
            es.execute(new Producer(buffer));
        }
        for (int i = 1; i <= Constants.NUM_OF_CONSUMER; ++i) {
            es.execute(new Consumer(buffer));
        }
    }
}
