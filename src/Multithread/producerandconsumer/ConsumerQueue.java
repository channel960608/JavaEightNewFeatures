package Multithread.producerandconsumer;

import Multithread.Task;

import java.util.concurrent.*;

public class ConsumerQueue implements Runnable {

    private BlockingQueue<Task> buffer;

    public ConsumerQueue(BlockingQueue<Task> buffr) {
       this.buffer = buffr;
    }

    @Override
    public void run() {
        while(true) {
            try {
                Task task = buffer.take();
                System.out.println(
                        "Consumer[" + Thread.currentThread().getName() + "] got " + task);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
