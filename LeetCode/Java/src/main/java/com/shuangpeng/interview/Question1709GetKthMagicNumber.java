package com.shuangpeng.interview;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @Description: Question1709GetKthMagicNumber（第K个数）
 * @Date 2022/9/28 12:15 PM
 * @Version 1.0
 */
public class Question1709GetKthMagicNumber {

    public int getKthMagicNumber(int k) {
        PriorityQueue<Long> q = new PriorityQueue<>();
        q.offer(1L);
        Set<Long> visited = new HashSet<>();
        for (int i = 1; i < k; i++) {
            long num = q.poll();
            long a = num * 3, b = num * 5, c = num * 7;
            if (visited.add(a)) {
                q.offer(a);
            }
            if (visited.add(b)) {
                q.offer(b);
            }
            if (visited.add(c)) {
                q.offer(c);
            }
        }
        long ans = q.poll();
        return (int) ans;
    }
}
