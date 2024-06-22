package com.shuangpeng.lcr;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: LCR038DailyTemperatures（每日温度）
 * @date 2024/6/22 3:56 PM
 */
public class LCR038DailyTemperatures {

    public int[] dailyTemperatures0(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new ArrayDeque<>(n);
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peekLast()] <= temperatures[i]) {
                stack.pollLast();
            }
            ans[i] = !stack.isEmpty() ? stack.peekLast() - i : 0;
            stack.offerLast(i);
        }
        return ans;
    }

    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Integer> s = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && temperatures[s.peekLast()] < temperatures[i]) {
                int j = s.pollLast();
                ans[j] = i - j;
            }
            s.offerLast(i);
        }
        return ans;
    }
}
