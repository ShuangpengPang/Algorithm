package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

/**
 * @Description: Problem1883MinimumSkipsToArriveAtMeetingOnTime（准时抵达会议现场的最小跳过休息次数）
 * @Date 2022/10/25 2:44 PM
 * @Version 1.0
 */
public class Problem1883MinimumSkipsToArriveAtMeetingOnTime {

    public int minSkips(int[] dist, int speed, int hoursBefore) {
        int n = dist.length;
        double e = 1e-10, INF = Double.MAX_VALUE;
        double[] dp = new double[n];
        Arrays.fill(dp, INF);
        dp[0] = (double) dist[0] / speed;
        for (int i = 1; i < n; i++) {
            double t = (double) dist[i] / speed;
            for (int j = i; j >= 0; j--) {
                double res = INF;
                if (j > 0) {
                    double c = getCeiling(dp[j - 1]);
                    if (Math.abs(c - dp[j - 1]) > e) {
                        res = Math.min(res, dp[j - 1] + t);
                    }
                }
                if (Math.abs(dp[j] - INF) > e) {
                    res = Math.min(res, getCeiling(dp[j]) + t);
                }
                dp[j] = res;
            }
        }
        for (int i = 0; i < n; i++) {
            if (dp[i] < hoursBefore || Math.abs(hoursBefore - dp[i]) <= e) {
                return i;
            }
        }
        return -1;
    }

    private double getCeiling(double d) {
        double e = 1e-10;
        double ans = (int) d + 1;
        if (Math.abs(ans - d - 1) <= e) {
            return ans - 1;
        }
        return ans;
    }
}
