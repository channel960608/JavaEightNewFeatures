package Multithread.philosopher;

public class Philosopher implements Runnable {

    private int index;
    private String name;

    public Philosopher(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public void run() {
        while(true) {
            try {
                AppContext.counter.acquire();
                boolean leftFirst = index % 2 == 0;
                AppContext.putOnFork(index, leftFirst);
                System.out.println(name + "正在吃意大利面（通心粉）。。。" );
                AppContext.putDownFork(index, leftFirst);
                AppContext.counter.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {
        String[] names = { "骆昊", "王大锤", "张三丰", "杨过", "李莫愁" };
        for(int i = 0, len = names.length; i < len; ++i) {
            new Thread(new Philosopher(i, names[i])).start();
        }
    }
}
