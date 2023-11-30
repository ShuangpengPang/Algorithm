package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2555MaximizeWinFromTwoSegments（两个线段获得的最多奖品）
 * @date 2023/11/30 11:18 AM
 */
public class Problem2555MaximizeWinFromTwoSegments {

    public int maximizeWin(int[] prizePositions, int k) {
        int n = prizePositions.length;
        int[] dp = new int[n + 1];
        for (int i = n - 1, j = n - 1; i >= 0; i--) {
            while (j >= 0 && prizePositions[j] > prizePositions[i] + k) {
                j--;
            }
            dp[i] = Math.max(dp[i + 1], j - i + 1);
        }
        int ans = dp[0];
        for (int i = 0, j = 0; j < n; i++) {
            while (j < n && prizePositions[j] <= prizePositions[i] + k) {
                j++;
            }
            ans = Math.max(ans, j - i + dp[j]);
        }
        return ans;
    }
}
