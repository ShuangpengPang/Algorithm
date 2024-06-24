package com.shuangpeng.lcr.p101_200;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR125BookManagementII（图书整理II）
 * @date 2024/5/13 6:22 PM
 */
public class LCR125BookManagementII {

    class CQueue {

        Queue<Integer> q;

        public CQueue() {
            q = new ArrayDeque<>();
        }

        public void appendTail(int value) {
            q.offer(value);
        }

        public int deleteHead() {
            if (q.isEmpty()) {
                return -1;
            }
            return q.poll();
        }
    }

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */
}
