package com.shuangpeng.lcr.p001_100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR093LenLongestFibSubseq（最长的斐波那契子序列的长度）
 * @date 2024/7/4 10:24 AM
 */
public class LCR093LenLongestFibSubseq {

    // 超时
    public int lenLongestFibSubseq0(int[] arr) {
        int ans = 0, n = arr.length;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(n);
        for (int i = 0; i < n; i++) {
            Map<Integer, Integer> cur = map.computeIfAbsent(arr[i], k -> new HashMap<>(n));
            for (int j = i - 1; j >= 0; j--) {
                int m = cur.getOrDefault(arr[j], 2);
                m = Math.max(m, map.get(arr[j]).getOrDefault(arr[i] - arr[j], 1) + 1);
                cur.put(arr[j], m);
                if (m >= 3) {
                    ans = Math.max(ans, m);
                }
            }
        }
        return ans;
    }

    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0, n = arr.length;
        int[][] dp = new int[n][n];
        for (int j = 0; j < n; j++) {
            for (int i = j - 1; i >= 0; i--) {
                int k = map.getOrDefault(arr[j] - arr[i], n);
                dp[i][j] = k < i ? dp[k][i] + 1 : 2;
                if (dp[i][j] > 2) {
                    ans = Math.max(ans, dp[i][j]);
                }
            }
            map.put(arr[j], j);
        }
        return ans;
    }
}
