package com.shuangpeng.Problem.p1301_1400;

import java.util.PriorityQueue;

/**
 * @Description: Problem1354ConstructTargetArrayWithMultipleSums（多次求和构造目标数组）
 * @Date 2022/8/9 11:01 AM
 * @Version 1.0
 */
public class Problem1354ConstructTargetArrayWithMultipleSums {

    public boolean isPossible(int[] target) {
        int n = target.length;
        if (n == 1) {
            return target[0] == 1;
        }
        long sum = 0L;
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i < n; i++) {
            int num = target[i];
            sum += num;
            q.offer(num);
        }
        while (sum > n) {
            int num = q.poll();
            long d = sum - num;
            long cnt = (num - q.peek()) / d;
            if (cnt > 0) {
                d *= cnt;
            }
            if (num <= d) {
                return false;
            }
            num -= d;
            sum -= d;
            q.offer(num);
        }
        return true;
    }

//    public static void main(String[] args) {
//        Problem1354ConstructTargetArrayWithMultipleSums a = new Problem1354ConstructTargetArrayWithMultipleSums();
//        int[] nums = {9, 3, 5};
//        a.isPossible(nums);
//    }
}