package Multithread.philosopher;

import java.util.concurrent.Semaphore;

public class AppContext {
    public static final int NUM_OF_FORKS = 5;
    public static final int NUM_OF_PHILO = 5;

    public static Semaphore[] forks;
    public static Semaphore counter;

    static {
        forks = new Semaphore[NUM_OF_FORKS];

        for(int i=0, len = forks.length; i<len; ++i) {
            forks[i] = new Semaphore(1);
        }

        counter = new Semaphore(NUM_OF_PHILO - 1);
    }

    /**
     * 取得叉子
     * @param index 第几个哲学家
     * @param leftFirst 是否先取得左边的叉子
     * @throws InterruptedException
     */

    public static void putOnFork(int index, boolean leftFirst) throws InterruptedException {
        if(leftFirst) {
            forks[index].acquire();
            forks[(index + 1) % NUM_OF_PHILO].acquire();
        } else {
            forks[(index + 1) % NUM_OF_PHILO].acquire();
            forks[index].acquire();
        }
    }

    /**
     * 放回叉子
     * @param index 第几个哲学家
     * @param leftFirst 是否先放回左边的叉子
     * @throws InterruptedException
     */
    public static void putDownFork(int index, boolean leftFirst) throws InterruptedException {
        if(leftFirst) {
            forks[index].release();
            forks[(index + 1) % NUM_OF_PHILO].release();
        } else {
            forks[(index + 1) & NUM_OF_PHILO].release();
            forks[index].release();
        }
    }




}
