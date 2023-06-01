package com.shuangpeng.Problem.p1101_1200;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1115PrintFooBarAlternately（交替打印FooBar）
 * @date 2023/6/1 5:21 PM
 */
public class Problem1115PrintFooBarAlternately {

    static class FooBar {
        private int n;
        private volatile int state;
        private static int N = 1000;

        public FooBar(int n) {
            this.n = n;
            state = -1;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0, j = -1; i < n; i++, j++) {
                int loop = 0;
                while (state != j) {
                    if (++loop == N) {
                        Thread.yield();
                        loop = 0;
                    }
                }
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                state = ++j;
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0, j = 0; i < n; i++, j++) {
                int loop = 0;
                while (state != j) {
                    if (++loop == N) {
                        Thread.yield();
                        loop = 0;
                    }
                }
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                state = ++j;
            }
        }
    }
}

class Problem1115PrintFooBarAlternately0 {

    class FooBar {
        private int n;
        private BlockingQueue<Integer> foo = new ArrayBlockingQueue<>(1);
        private BlockingQueue<Integer> bar = new ArrayBlockingQueue<>(1);

        public FooBar(int n) {
            this.n = n;
        }

        public void foo(Runnable printFoo) throws InterruptedException {

            for (int i = 0, j = -1; i < n; i++, j++) {
                foo.put(1);
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                bar.put(1);
            }
        }

        public void bar(Runnable printBar) throws InterruptedException {

            for (int i = 0, j = 0; i < n; i++, j++) {
                bar.take();
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                foo.take();
            }
        }
    }
}

class FooBar {
    private int n;
    private BlockingQueue<Integer> foo = new ArrayBlockingQueue<>(1);
    private BlockingQueue<Integer> bar = new ArrayBlockingQueue<>(1);

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0, j = -1; i < n; i++, j++) {
            foo.put(1);
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            bar.put(1);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0, j = 0; i < n; i++, j++) {
            bar.take();
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            foo.take();
        }
    }
}
