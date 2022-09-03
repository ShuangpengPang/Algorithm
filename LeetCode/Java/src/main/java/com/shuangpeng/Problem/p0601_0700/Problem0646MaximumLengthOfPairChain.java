package com.shuangpeng.Problem.p0601_0700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description:d（最长数对链）
 * @Date 2022/9/3 6:58 PM
 **/
public class Problem0646MaximumLengthOfPairChain {

    public int findLongestChain0(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[0]));
        int n = pairs.length;
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int j = i - 1;
            while (j >= 0 && pairs[j][1] >= pairs[i][0]) {
                j--;
            }
            dp[i] = j >= 0 ? dp[j] + 1 : 1;
            ans = Math.max(dp[i], ans);
        }
        return ans;
    }

    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int n = pairs.length;
        int end = pairs[0][1], length = 1;
        for (int i = 1; i < n; i++) {
            if (pairs[i][0] > end) {
                length++;
                end = pairs[i][1];
            }
        }
        return length;
    }
}
