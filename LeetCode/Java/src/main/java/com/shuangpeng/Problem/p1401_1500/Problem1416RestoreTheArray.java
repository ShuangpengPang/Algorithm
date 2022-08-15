package com.shuangpeng.Problem.p1401_1500;

/**
 * @Description: Problem1416RestoreTheArray（恢复数组）
 * @Date 2022/8/15 11:28 AM
 * @Version 1.0
 */
public class Problem1416RestoreTheArray {

    public int numberOfArrays(String s, int k) {
        if (s.charAt(0) == '0') {
            return 0;
        }
        int M = (int) 1e9 + 7;
        int n = s.length();
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            long base = 1, value = 0;
            long cnt = 0L;
            for (int j = i; j >= 0 && base <= k && value <= k; j--) {
                int digit = s.charAt(j) - '0';
                value += base * digit;
                base *= 10;
                if (digit == 0 || value > k) {
                    continue;
                }
                if (j > 0) {
                    cnt += dp[j - 1];
                } else {
                    cnt++;
                }
            }
            dp[i] = (int) (cnt % M);
        }
        return dp[n - 1];
    }
}
