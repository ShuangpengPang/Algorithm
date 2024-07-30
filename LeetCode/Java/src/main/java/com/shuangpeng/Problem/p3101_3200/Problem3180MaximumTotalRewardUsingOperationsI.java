package com.shuangpeng.Problem.p3101_3200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3180MaximumTotalRewardUsingOperationsI（执行操作可获得的最大总奖励）
 * @date 2024/7/30 12:05 PM
 */
public class Problem3180MaximumTotalRewardUsingOperationsI {

    public int maxTotalReward(int[] rewardValues) {
        Arrays.sort(rewardValues);
        int n = rewardValues.length, N = rewardValues[n - 1];
        boolean[] set = new boolean[N];
        set[0] = true;
        int ans = 0;
        for (int r : rewardValues) {
            for (int i = 0; i < r; i++) {
                if (set[i]) {
                    int s = i + r;
                    ans = Math.max(ans, s);
                    if (s < N) {
                        set[s] = true;
                    }
                }
            }
        }
        return ans;
    }
}
