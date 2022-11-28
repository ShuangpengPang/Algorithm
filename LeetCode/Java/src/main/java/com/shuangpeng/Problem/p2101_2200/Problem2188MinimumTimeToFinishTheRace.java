package com.shuangpeng.Problem.p2101_2200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2188MinimumTimeToFinishTheRace（完成比赛的最少时间）
 * @date 2022/11/28 4:49 PM
 */
public class Problem2188MinimumTimeToFinishTheRace {

    public int minimumFinishTime(int[][] tires, int changeTime, int numLaps) {
        int L = 20, N = 1 << 18;
        int[] t = new int[L];
        Arrays.fill(t, Integer.MAX_VALUE >> 1);
        for (int[] tire : tires) {
            long f = tire[0], r = tire[1];
            for (int i = 1, j = 1, sum = 0; f * j <= N; i++, j *= r) {
                sum += f * j;
                t[i] = Math.min(t[i], sum);
            }
        }
        int[] dp = new int[numLaps + 1];
        Arrays.fill(dp, Integer.MAX_VALUE >> 1);
        dp[0] = -changeTime;
        for (int i = 1; i <= numLaps; i++) {
            for (int j = i; j >= Math.max(1, i - (L - 1) + 1); j--) {
                dp[i] = Math.min(dp[i], dp[j - 1] + changeTime + t[i - j + 1]);
            }
        }
        return dp[numLaps];
    }
}
