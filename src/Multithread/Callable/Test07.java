package Multithread.Callable;

import java.util.concurrent.*;
import java.util.*;

public class Test07 {
    private static final int POOL_SIZE = 10;

    static class CalcThread implements Callable<Double> {

        private List<Double> dataList = new ArrayList<>();

        public CalcThread() {
            for(int i = 0; i < 10000; ++i) {
                dataList.add(Math.random());
            }
        }

        @Override
        public Double call() throws Exception {
            double total = 0;
            for(Double d : dataList) {
                total += d;
            }
            return total / dataList.size();
        }
    }

    public static void main(String... args) {
        List<Future<Double>> fList = new ArrayList<>();
        ExecutorService es = Executors.newFixedThreadPool(POOL_SIZE);
        for(int i = 0; i < POOL_SIZE; ++i) {
            fList.add(es.submit(new CalcThread()));
        }

        for(Future<Double> f : fList) {
            try {
                System.out.println(f.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        es.shutdown();
    }
}
