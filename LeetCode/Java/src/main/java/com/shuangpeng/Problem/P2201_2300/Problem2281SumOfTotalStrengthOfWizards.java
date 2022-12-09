package com.shuangpeng.Problem.P2201_2300;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2281SumOfTotalStrengthOfWizards（巫师的总力量和）
 * @date 2022/12/9 12:12 PM
 */
public class Problem2281SumOfTotalStrengthOfWizards {

    public int totalStrength(int[] strength) {
        int n = strength.length, M = (int) 1e9 + 7;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = (preSum[i] + strength[i]) % M;
        }
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = (preSum[i] + preSum[i + 1]) % M;
        }
        int[] left = new int[n + 1];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            while (!stack.isEmpty() && strength[stack.peek() - 1] > strength[i - 1]) {
                stack.pop();
            }
            left[i] = stack.isEmpty() ? 0 : stack.peek();
            stack.push(i);
        }
        int[] right = new int[n + 1];
        stack.clear();
        for (int i = n; i >= 1; i--) {
            while (!stack.isEmpty() && strength[stack.peek() - 1] >= strength[i - 1]) {
                stack.pop();
            }
            right[i] = stack.isEmpty() ? n + 1 : stack.peek();
            stack.push(i);
        }
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            int l = left[i], r = right[i], cnt1 = i - l, cnt2 = r - i;
            int sum1 = (preSum[r - 1] - preSum[i - 1] + M) % M;
            int sum2 = (preSum[i - 1] - (l > 0 ? preSum[l - 1] : 0) + M) % M;
            ans = (int) ((ans + (long) strength[i - 1] * (((long) sum1 * cnt1 % M - (long) sum2 * cnt2 % M + M) % M)) % M);
        }
        return ans;
    }
}
