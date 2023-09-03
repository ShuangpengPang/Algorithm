package com.shuangpeng.competition.第241到250场周赛.第248场周赛;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @description:（消灭怪物的最大数量）
 * @date 2023/9/3 11:10 PM
 **/
public class Problem1921 {

    public int eliminateMaximum0(int[] dist, int[] speed) {
        int n = dist.length;
        double[] temp = new double[n];
        for (int i = 0; i < n; i++) {
            temp[i] = (double) dist[i] / (double) speed[i];
        }
        Arrays.sort(temp);
        for (int i = 0; i < n; i++) {
            if (i >= temp[i]) {
                return i;
            }
        }
        return n;
    }

    public int eliminateMaximum1(int[] dist, int[] speed) {
        PriorityQueue<Integer> q = new PriorityQueue<>();
        int n = dist.length;
        for (int i = 0; i < n; i++) {
            q.offer((dist[i] + speed[i] - 1) / speed[i]);
        }
        int ans = 0;
        while (!q.isEmpty() && q.peek() > ans) {
            ans++;
            q.poll();
        }
        return ans;
    }

    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] time = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = (dist[i] + speed[i] - 1) / speed[i];
        }
        Arrays.sort(time);
        for (int i = 0; i < n; i++) {
            if (i >= time[i]) {
                return i;
            }
        }
        return n;
    }
}
