package Multithread.Java7;

import com.sun.org.apache.xpath.internal.functions.FuncRound;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class Calculator extends RecursiveTask<Integer> {

    private static final long serialVersionUID = 7333472779649130114L;

    private static final int THRESHOLD = 10;
    private int start;
    private int end;

    public Calculator(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Integer compute() {
        int sum = 0;
        if((end - start) < THRESHOLD) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
        } else {
            int middle = (start + end) >>> 1;

            Calculator left = new Calculator(start, middle);
            Calculator right = new Calculator(middle + 1, end);
            left.fork();
            right.fork();

            sum = left.join() + right.join();
        }

        return sum;
    }

    public static void main(String... args) throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(new Calculator(1, 1000000));
        System.out.println(result.get());
    }


}
