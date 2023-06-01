package com.shuangpeng.Problem.p1101_1200;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1115PrintFooBarAlternately（交替打印FooBar）
 * @date 2023/6/1 5:21 PM
 */
public class Problem1115PrintFooBarAlternately {
}

class FooBar {
    private int n;
    private AtomicInteger state;
    private static int N = (int) 5e6;

    public FooBar(int n) {
        this.n = n;
        state = new AtomicInteger(-1);
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0, j = -1; i < n; i++, j++) {
            if (state.get() != j) {
                Thread.yield();
                i--;
                j--;
            } else {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                state.set(++j);
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0, j = 0; i < n; i++, j++) {
            if (state.get() != j) {
                Thread.yield();
                i--;
                j--;
            } else {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                state.set(++j);
            }
        }
    }
}


