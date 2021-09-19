package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0960DeleteColumnsToMakeSortedIII {

    public int minDeletionSize(String[] strs) {
        int m = strs.length, n = strs[0].length();
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxCount = 1;
        for (int i = 1; i < n; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                boolean valid = true;
                for (int k = 0; k < m; ++k) {
                    if (strs[k].charAt(j) > strs[k].charAt(i)) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxCount = Math.max(maxCount, dp[i]);
                }
            }
        }
        return n - maxCount;
    }
}
