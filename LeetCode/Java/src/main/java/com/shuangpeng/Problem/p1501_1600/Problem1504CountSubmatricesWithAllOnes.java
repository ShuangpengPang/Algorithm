package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1504CountSubmatricesWithAllOnes（统计全1子矩形）
 * @date 2023/8/29 7:28 PM
 */
public class Problem1504CountSubmatricesWithAllOnes {

    public int numSubmat(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int ans = 0;
        int[][] dp = new int[2][n];
        for (int i = 0, left = 0; i < n; i++) {
            if (mat[0][i] == 1) {
                dp[0][i] = 1;
                ans += ++left;
            } else {
                left = 0;
            }
        }
        for (int i = 1; i < m; i++) {
            int idx = i & 1, p = idx ^ 1;
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1) {
                    int cnt = m;
                    for (int k = j; k >= 0 && mat[i][k] == 1; k--) {
                        cnt = Math.min(cnt, dp[p][k]);
                        ans += cnt + 1;
                    }
                    dp[idx][j] = dp[p][j] + 1;
                } else {
                    dp[idx][j] = 0;
                }
            }
        }
        return ans;
    }
}
