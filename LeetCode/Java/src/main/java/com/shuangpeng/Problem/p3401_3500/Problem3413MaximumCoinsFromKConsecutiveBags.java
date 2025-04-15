package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3413MaximumCoinsFromKConsecutiveBags（收集连续K个袋子可以获得的最多硬币数量）
 * @date 2025/4/15 14:57
 */
public class Problem3413MaximumCoinsFromKConsecutiveBags {

    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins, Comparator.comparingInt(a -> a[0]));
        int n = coins.length;
        long ans = 0, s = 0;
        for (int i = 0, j = 0; j < n; i++) {
            int l = coins[i][0];
            while (j < n && l + k > coins[j][1]) {
                s += (long) coins[j][2] * (coins[j][1] - coins[j][0] + 1);
                j++;
            }
            ans = Math.max(ans, s);
            if (j < n && l + k > coins[j][0]) {
                ans = Math.max(ans, s + (long) coins[j][2] * (l + k - coins[j][0]));
            }
            if (i < j) {
                s -= (long) coins[i][2] * (coins[i][1] - coins[i][0] + 1);
            } else {
                j++;
            }
        }
        s = 0;
        for (int i = n - 1, j = n - 1; i >= 0; j--) {
            int r = coins[j][1];
            while (i >= 0 && r - k < coins[i][0]) {
                s += (long) coins[i][2] * (coins[i][1] - coins[i][0] + 1);
                i--;
            }
            ans = Math.max(ans, s);
            if (i >= 0 && r - k < coins[i][1]) {
                ans = Math.max(ans, s + (long) coins[i][2] * (coins[i][1] - r + k));
            }
            if (i < j) {
                s -= (long) coins[j][2] * (coins[j][1] - coins[j][0] + 1);
            } else {
                i--;
            }
        }
        return ans;
    }
}
