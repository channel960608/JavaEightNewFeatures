package Multithread;

public class Thread1 extends Thread {
    private String name;

    public Thread1(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(name + "运行 ： " + i);

            try {
                sleep((int) Math.random() * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class Main {

        public static void main(String[] args) {

            System.out.println(Thread.currentThread().getName()+"主线程运行开始!");

            Thread1 mTh1=new Thread1("A");
            Thread1 mTh2=new Thread1("B");
            mTh1.start();
            mTh2.start();

            try {
                mTh1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                mTh2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName()+"主线程运行结束!");


        }

    }
}
