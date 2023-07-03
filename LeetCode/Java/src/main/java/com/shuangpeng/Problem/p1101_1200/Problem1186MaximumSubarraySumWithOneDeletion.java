package com.shuangpeng.Problem.p1101_1200;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1186MaximumSubarraySumWithOneDeletion（删除一次得到子数组最大和）
 * @date 2023/6/9 11:39 AM
 */
public class Problem1186MaximumSubarraySumWithOneDeletion {

    public int maximumSum0(int[] arr) {
        int n = arr.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + arr[i];
        }
        int[] left = new int[n], right = new int[n];
        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int m = i;
            while (!q.isEmpty() && arr[q.peek()] >= arr[i]) {
                int j = left[q.pop()];
                if (preSum[j] <= preSum[m]) {
                    m = j;
                }
            }
            left[i] = m;
            q.push(i);
        }
        q.clear();
        for (int i = n - 1; i >= 0; i--) {
            int m = i + 1;
            while (!q.isEmpty() && arr[q.peek()] >= arr[i]) {
                int j = right[q.pop()];
                if (preSum[j] >= preSum[m]) {
                    m = j;
                }
            }
            right[i] = q.isEmpty() && preSum[m] < preSum[n] ? n : m;
            q.push(i);
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 0, s = 0; i < n; i++) {
            s = Math.max(s + arr[i], arr[i]);
            ans = Math.max(ans, s);
            if (right[i] - left[i] > 1) {
                ans = Math.max(ans, preSum[right[i]] - preSum[left[i]] - arr[i]);
            }
        }
        return ans;
    }

    public int maximumSum(int[] arr) {
        int n = arr.length, ans = arr[0], delete = Integer.MIN_VALUE >> 1, sum = arr[0];
        for (int i = 1; i < n; i++) {
            int s = sum;
            sum = sum > 0 ? sum + arr[i] : arr[i];
            delete = Math.max(s, delete + arr[i]);
            ans = Math.max(ans, Math.max(sum, delete));
        }
        return ans;
    }
}
