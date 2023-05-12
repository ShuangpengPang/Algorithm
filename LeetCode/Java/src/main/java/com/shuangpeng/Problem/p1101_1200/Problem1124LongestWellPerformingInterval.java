package com.shuangpeng.Problem.p1101_1200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1124LongestWellPerformingInterval（表现良好的最长时间段）
 * @date 2023/5/12 4:53 PM
 */
public class Problem1124LongestWellPerformingInterval {

    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + (hours[i] > 8 ? 1 : -1);
        }
        int[] stack = new int[n + 2];
        int top = 0;
        for (int i = 1; i <= n; i++) {
            if (preSum[i] < preSum[stack[top]]) {
                stack[++top] = i;
            }
        }
        stack[top + 1] = n;
        int ans = 0;
        for (int i = n; i > 0 && top >= 0; i--) {
            while (top >= 0 && preSum[stack[top]] < preSum[i]) {
                top--;
            }
            ans = Math.max(ans, i - stack[top + 1]);
        }
        return ans;
    }
}
