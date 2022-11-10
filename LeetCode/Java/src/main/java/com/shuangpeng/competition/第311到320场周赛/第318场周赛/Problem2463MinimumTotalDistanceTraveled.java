package com.shuangpeng.competition.第311到320场周赛.第318场周赛;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2463MinimumTotalDistanceTraveled（最小移动总距离）
 * @date 2022/11/10 3:40 PM
 */
public class Problem2463MinimumTotalDistanceTraveled {

    public long minimumTotalDistance(List<Integer> robot, int[][] factory) {
        robot.sort(Comparator.comparingInt(a -> a));
        Arrays.sort(factory, Comparator.comparingInt(a -> a[0]));
        int m = robot.size(), n = factory.length;
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE >> 1);
        }
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                long cost = 0L;
                for (int c = 1; c <= Math.min(j, factory[i - 1][1]); c++) {
                    cost += Math.abs(robot.get(j - c) - factory[i - 1][0]);
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - c] + cost);
                }
            }
        }
        return dp[n][m];
    }
}