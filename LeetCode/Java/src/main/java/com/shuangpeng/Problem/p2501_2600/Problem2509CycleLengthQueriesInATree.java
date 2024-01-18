package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2509CycleLengthQueriesInATree（查询树中环的长度）
 * @date 2024/1/18 12:53 PM
 */
public class Problem2509CycleLengthQueriesInATree {

    public int[] cycleLengthQueries(int n, int[][] queries) {
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int a = queries[i][0], b = queries[i][1];
            int j = n, k = n;
            while ((a & (1 << j)) == 0) {
                j--;
            }
            while ((b & (1 << k)) == 0) {
                k--;
            }
            while (j >= 0 && k >= 0 && ((a >> j) & 1) == ((b >> k) & 1)) {
                j--;
                k--;
            }
            ans[i] = j + k + 3;
        }
        return ans;
    }
}
