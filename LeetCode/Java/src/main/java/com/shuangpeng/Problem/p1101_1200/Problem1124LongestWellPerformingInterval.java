package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1124LongestWellPerformingInterval（表现良好的最长时间段）
 * @date 2023/5/12 4:53 PM
 */
public class Problem1124LongestWellPerformingInterval {

    public int longestWPI0(int[] hours) {
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

    public int longestWPI1(int[] hours) {
        int n = hours.length;
        Map<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            sum += hours[i] > 8 ? 1 : -1;
            if (sum > 0) {
                ans = i + 1;
            } else {
                ans = Math.max(ans, i - map.getOrDefault(sum - 1, n));
                map.put(sum, Math.min(i, map.getOrDefault(sum, n)));
            }
        }
        return ans;
    }

    public int longestWPI2(int[] hours) {
        int n = hours.length;
        int[] map = new int[(n << 1) + 2];
        Arrays.fill(map, n);
        map[n + 1] = 0;
        int ans = 0;
        for (int s = 0, i = 1; i <= n; i++) {
            s += hours[i - 1] > 8 ? 1 : -1;
            ans = Math.max(ans, s > 0 ? i : i - map[s + n]);
            map[s + n + 1] = Math.min(map[s + n + 1], i);
        }
        return ans;
    }

    public int longestWPI(int[] hours) {
        int n = hours.length;
        int[] map = new int[n + 2];
        Arrays.fill(map, n);
        map[0] = 0;
        int ans = 0;
        for (int i = 1, s = 0; i <= n; i++) {
            s += hours[i - 1] > 8 ? 1 : -1;
            ans = Math.max(ans, s > 0 ? i : i - map[1 - s]);
            if (s < 0) {
                map[-s] = Math.min(map[-s], i);
            }
        }
        return ans;
    }
}
