package com.shuangpeng.Problem.P2201_2300;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2208MinimumOperationsToHalveArraySum（将数组和减半的最少操作次数）
 * @date 2023/7/27 11:43 AM
 */
public class Problem2208MinimumOperationsToHalveArraySum {

    public int halveArray(int[] nums) {
        PriorityQueue<Double> q = new PriorityQueue<>(Comparator.reverseOrder());
        double sum = 0.0;
        for (int num : nums) {
            sum += num;
            q.offer((double) num);
        }
        double s = 0.0;
        int cnt = 0;
        while (s < sum / 2) {
            double h = q.poll() / 2;
            s += h;
            q.offer(h);
            cnt++;
        }
        return cnt;
    }
}
