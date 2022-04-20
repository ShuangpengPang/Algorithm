package com.shuangpeng.Problem.p1601_1700;

/**
 * @Description: Problem1672RichestCustomerWealth
 * @Date 2022/4/20 5:20 PM
 * @Version 1.0
 */
public class Problem1672RichestCustomerWealth {

    public int maximumWealth(int[][] accounts) {
        int m = accounts.length, n = accounts[0].length;
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int sum = 0;
            for (int j = 0; j < n; ++j) {
                sum += accounts[i][j];
            }
            ans = Math.max(ans, sum);
        }
        return ans;
    }
}
