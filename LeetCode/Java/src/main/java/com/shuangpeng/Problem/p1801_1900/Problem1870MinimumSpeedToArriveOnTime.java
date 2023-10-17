package com.shuangpeng.Problem.p1801_1900;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1870MinimumSpeedToArriveOnTime（准时到达的列车最小时速）
 * @date 2023/10/17 6:17 PM
 */
public class Problem1870MinimumSpeedToArriveOnTime {

    public int minSpeedOnTime(int[] dist, double hour) {
        if (dist.length - 1 >= hour) {
            return -1;
        }
        int left = 1, right = (int) 1e7;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (check(dist, mid, hour)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean check(int[] dist, int speed, double hour) {
        double time = 0.0;
        int n = dist.length;
        for (int i = 0; i < n - 1; i++) {
            time += (dist[i] + speed - 1) / speed;
        }
        return time + (double) dist[n - 1] / speed <= hour;
    }
}
