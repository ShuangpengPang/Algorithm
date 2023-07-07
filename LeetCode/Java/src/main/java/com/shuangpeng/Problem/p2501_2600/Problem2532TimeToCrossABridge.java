package com.shuangpeng.Problem.p2501_2600;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2532TimeToCrossABridge（过桥的时间）
 * @date 2023/7/7 10:11 AM
 */
public class Problem2532TimeToCrossABridge {

    public int findCrossingTime(int n, int k, int[][] time) {
        PriorityQueue<int[]> left = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<int[]> right = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        PriorityQueue<Integer> leftWait = new PriorityQueue<>((a, b) -> time[a][0] + time[a][2] != time[b][0] + time[b][2] ? time[b][0] + time[b][2] - time[a][0] - time[a][2] : b - a);
        PriorityQueue<Integer> rightWait = new PriorityQueue<>((a, b) -> time[a][0] + time[a][2] != time[b][0] + time[b][2] ? time[b][0] + time[b][2] - time[a][0] - time[a][2] : b - a);
        for (int i = 0; i < k; i++) {
            leftWait.offer(i);
        }
        int t = 0, remain = n;
        while (!right.isEmpty() || !rightWait.isEmpty() || remain > 0) {
            while (!left.isEmpty() && left.peek()[1] <= t) {
                leftWait.offer(left.poll()[0]);
            }
            while (!right.isEmpty() && right.peek()[1] <= t) {
                rightWait.offer(right.poll()[0]);
            }
            if (!rightWait.isEmpty()) {
                int w = rightWait.poll();
                t += time[w][2];
                left.offer(new int[]{w, t + time[w][3]});
            } else if (!leftWait.isEmpty() && remain > 0) {
                int w = leftWait.poll();
                t += time[w][0];
                right.offer(new int[]{w, t + time[w][1]});
                remain--;
            } else {
                t = Math.min(left.isEmpty() ? Integer.MAX_VALUE : left.peek()[1], right.isEmpty() ? Integer.MAX_VALUE : right.peek()[1]);
            }
        }
        return t;
    }
}
