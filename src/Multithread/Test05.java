package Multithread;

import java.util.*;
import java.util.concurrent.*;

public class Test05 {
    private static final int THREAD_POOL_SIZE = 2;

    public static void main(String... args) {
//        线程不安全
//        List<Double> list = new ArrayList<>();

        List<Double> list = new CopyOnWriteArrayList<>();  //线程安全
        ExecutorService es = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
        es.execute(new AddThread(list));
        es.execute(new AddThread(list));
        es.shutdown();


    }
}
