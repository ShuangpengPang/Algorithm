package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2594MinimumTimeToRepairCars（修车的最少时间）
 * @date 2023/9/7 10:30 AM
 */
public class Problem2594MinimumTimeToRepairCars {

    public long repairCars(int[] ranks, int cars) {
        int n = ranks.length, max = 0;
        for (int r : ranks) {
            max = Math.max(max, r);
        }
        int[] cnt = new int[max + 1];
        for (int r : ranks) {
            cnt[r]++;
        }
        long left = 0, right = (long) max * (cars / n + 1) * (cars / n + 1);
        while (left <= right) {
            long mid = left + (right - left >> 1);
            long count = 0;
            for (int i = 1; i <= max && count < cars; i++) {
                if (cnt[i] > 0) {
                    count += (long) Math.sqrt(mid / i) * cnt[i];
                }
            }
            if (count < cars) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
}
