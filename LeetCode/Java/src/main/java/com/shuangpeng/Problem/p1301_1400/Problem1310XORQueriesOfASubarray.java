package com.shuangpeng.Problem.p1301_1400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1310XORQueriesOfASubarray（子数组异或查询）
 * @date 2023/7/7 5:55 PM
 */
public class Problem1310XORQueriesOfASubarray {

    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xorSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            xorSum[i + 1] = xorSum[i] ^ arr[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            ans[i] = xorSum[queries[i][1] + 1] ^ xorSum[queries[i][0]];
        }
        return ans;
    }
}
