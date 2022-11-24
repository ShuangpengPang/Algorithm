package com.shuangpeng.Problem.p0701_0800;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0795NumberOfSubarraysWithBoundedMaximum（区间子数组个数）
 * @date 2022/11/24 10:17 AM
 */
public class Problem0795NumberOfSubarraysWithBoundedMaximum {

    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int n = nums.length, ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            while (stack.peek() != -1 && nums[stack.peek()] < nums[i]) {
                int j = stack.peek();
                if (nums[j] >= left && nums[j] <= right) {
                    ans += dp[j] * (i - j);
                }
                stack.pop();
            }
            dp[i] = i - stack.peek();
            stack.push(i);
        }
        while (stack.peek() != -1) {
            int j = stack.pop();
            if (nums[j] >= left && nums[j] <= right) {
                ans += dp[j] * (n - j);
            }
        }
        return ans;
    }
}
