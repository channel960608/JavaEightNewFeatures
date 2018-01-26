package Multithread.test;

import java.util.concurrent.*;
import java.util.*;

public class Main {
    public static void main(String... args) {

        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> callables = Arrays.asList(
                () -> "task1" ,
                () -> "task2" ,
                () -> "task3"
        );

        try {
            executor.invokeAll(callables)
                    .stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            throw new IllegalStateException(e);
                        }
                    })
                    .forEach(System.out::println);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }






    }
}
