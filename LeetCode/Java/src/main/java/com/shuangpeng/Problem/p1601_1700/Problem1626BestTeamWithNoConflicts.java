package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1626BestTeamWithNoConflicts（无矛盾的最佳球队）
 * @date 2023/4/5 8:11 PM
 */
public class Problem1626BestTeamWithNoConflicts {

    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        Integer[] ids = new Integer[n];
        Arrays.setAll(ids, i -> i);
        Arrays.sort(ids, (a, b) -> {
            if (ages[a] != ages[b]) {
                return ages[a] - ages[b];
            }
            return scores[a] - scores[b];
        });
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int s = scores[ids[i]];
            dp[i] = s;
            for (int j = i - 1; j >= 0; j--) {
                if (scores[ids[j]] <= s) {
                    dp[i] = Math.max(dp[i], dp[j] + s);
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
