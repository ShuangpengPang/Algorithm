package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3212CountSubmatricesWithEqualFrequencyOfXAndY（统计X和Y频数相等的子矩阵数量）
 * @date 2024/7/18 11:03 AM
 */
public class Problem3212CountSubmatricesWithEqualFrequencyOfXAndY {

    public int numberOfSubmatrices(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[n][2];
        int ans = 0;
        for (char[] r : grid) {
            int x = 0, y = 0;
            for (int i = 0; i <  n; i++) {
                if (r[i] == 'X') {
                    x++;
                } else if (r[i] == 'Y') {
                    y++;
                }
                dp[i][0] += x;
                dp[i][1] += y;
                if (dp[i][0] > 0 && dp[i][0] == dp[i][1]) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
