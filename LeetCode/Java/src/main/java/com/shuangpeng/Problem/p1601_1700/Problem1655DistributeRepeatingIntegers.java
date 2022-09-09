package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem1655DistributeRepeatingIntegers（分配重复整数）
 * @Date 2022/9/8 7:23 PM
 * @Version 1.0
 */
public class Problem1655DistributeRepeatingIntegers {

    public boolean canDistribute(int[] nums, int[] quantity) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int n = quantity.length, m = map.size(), N = 1 << n;
        int[] cnt = new int[m];
        int idx = 0;
        for (int c : map.values()) {
            cnt[idx++] = c;
        }
        int[] sum = new int[N];
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    sum[i] += quantity[j];
                }
            }
        }
        boolean[][] dp = new boolean[N][m + 1];
        Arrays.fill(dp[0], true);
        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1];
                if (!dp[i][j]) {
                    for (int k = i; k != 0; k = (k - 1) & i) {
                        if (cnt[j - 1] >= sum[k] && dp[i ^ k][j - 1]) {
                            dp[i][j] = true;
                            break;
                        }
                    }
                }
            }
        }
        return dp[N - 1][m];
    }
}

