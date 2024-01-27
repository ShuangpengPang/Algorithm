package com.shuangpeng.lcp;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCP19MinimumOperations（秋叶收藏集）
 * @date 2024/1/28 12:26 AM
 */
public class LCP19MinimumOperations {

    public int minimumOperations(String leaves) {
        char[] cs = leaves.toCharArray();
        int n = cs.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (cs[i - 1] == 'r' ? 1 : 0);
        }
        int minValue = 1 - (preSum[1] << 1), ans = Integer.MAX_VALUE;
        for (int i = 2; i < n; i++) {
            int value = i - (preSum[i] << 1);
            ans = Math.min(ans, minValue - value);
            minValue = Math.min(minValue, value);
        }
        return ans + n - preSum[n];
    }
}
