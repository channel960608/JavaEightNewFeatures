package Multithread;

class ThreadYield extends Thread{
    public ThreadYield(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 1000; i++) {
            System.out.println("" + this.getName() + "-----" + i);
            // 当i为30时，该线程就会把CPU时间让掉，让其他或者自己的线程执行（也就是谁先抢到谁执行）
            if (i % 30 == 0) {
                this.yield();
            }
        }

    }


    public static void main(String[] args) {

        ThreadYield yt1 = new ThreadYield("1");
        ThreadYield yt2 = new ThreadYield("0");
        yt1.start();
        yt2.start();
    }
}

