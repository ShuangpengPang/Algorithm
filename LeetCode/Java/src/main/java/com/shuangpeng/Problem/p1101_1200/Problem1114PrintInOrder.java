package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1114PrintInOrder（按序打印）
 * @date 2024/1/14 5:12 PM
 */
public class Problem1114PrintInOrder {

    class Foo {

        private volatile int state = 0;

        public Foo() {
            state = 0;
        }

        public void first(Runnable printFirst) throws InterruptedException {

            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            state = 1;
        }

        public void second(Runnable printSecond) throws InterruptedException {
            while (state == 0) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            state = 2;
        }

        public void third(Runnable printThird) throws InterruptedException {
            while (state != 2) {
                try {
                    Thread.sleep(1);
                } catch (Exception e) {
                }
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }
    }
}
