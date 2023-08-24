package com.shuangpeng.Problem.p2801_2900;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2827NumberOfBeautifulIntegersInTheRange（范围中美丽整数的数目）
 * @date 2023/8/24 2:34 PM
 */
public class Problem2827NumberOfBeautifulIntegersInTheRange {

    public int numberOfBeautifulIntegers(int low, int high, int k) {
        return calc(high, k) - calc(low - 1, k);
    }

    private int calc(int num, int k) {
        String s = Integer.toString(num);
        int n = s.length();
        int[][][] memo = new int[n][(n << 1) + 1][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= n << 1; j++) {
                Arrays.fill(memo[i][j], -1);
            }
        }
        return dfs(s.toCharArray(), 0, n, 0, k, true, false, memo);
    }

    private int dfs(char[] cs, int i, int diff, int mod, int k, boolean isLimit, boolean isNum, int[][][] memo) {
        int n = cs.length;
        if (i == n) {
            return isNum && diff == n && mod == 0 ? 1 : 0;
        }
        if (!isLimit && isNum && memo[i][diff][mod] != -1) {
            return memo[i][diff][mod];
        }
        int ans = 0;
        if (!isNum) {
            ans = dfs(cs, i + 1, diff, mod, k, false, false, memo);
        }
        int up = isLimit ? cs[i] - '0' : 9, m = mod * 10 % k;
        for (int j = isNum || i == n - 1 ? 0 : 1; j <= up; j++) {
            ans += dfs(cs, i + 1, diff + ((j & 1) << 1) - 1, (m + j) % k, k, isLimit && j == up, true, memo);
        }
        if (!isLimit && isNum) {
            memo[i][diff][mod] = ans;
        }
        return ans;
    }
}
