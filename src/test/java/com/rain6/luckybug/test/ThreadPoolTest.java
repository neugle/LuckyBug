package com.rain6.luckybug.test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Rain6 on 2017/4/28.
 * 线程池测试
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.execute(new Add());
        pool.execute(new Reduce());
        pool.execute(new Multi());
    }
}

class Add extends Thread {
    private int n = 0;

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println("add-----" + i);
            n += i;
        }
        System.out.println(n);
    }
}

class Reduce extends Thread {
    private int n = 10;

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println("reduce-----" + i);
            n -= i;
        }
        System.out.println(n);
    }
}

class Multi extends Thread {
    private int n = 1;

    @Override
    public void run() {
        super.run();
        for (int i = 1; i <= 5; i++) {
            System.out.println("multi-----" + i);
            n *= i;
        }
        System.out.println(n);
    }
}
