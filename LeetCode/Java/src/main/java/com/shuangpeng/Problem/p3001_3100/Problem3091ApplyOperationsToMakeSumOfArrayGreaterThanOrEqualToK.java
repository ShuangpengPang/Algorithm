package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3091ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToK（执行操作使数据元素之和大于等于K）
 * @date 2024/4/1 5:16 PM
 */
public class Problem3091ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToK {

    public int minOperations0(int k) {
        int left = 0, right = k - 1;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            if (!check(k, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int k, int count) {
        for (int i = 0; i <= count; i++) {
            if ((i + 1) * (1 + count - i) >= k) {
                return true;
            }
        }
        return false;
    }

    public int minOperations1(int k) {
        int ans = k - 1;
        for (int i = 0; i < k; i++) {
            ans = Math.min(ans, i + (k - 1) / (i + 1));
        }
        return ans;
        // m - 1 + (k - 1) / m
        // -1 + m + (k - 1) / m
    }

    public int minOperations(int k) {
        int m = Math.max((int) Math.sqrt(k - 1), 1);
        return Math.min(m + (k - 1) / m, m + 1 + (k - 1) / (m + 1)) - 1;
    }
}
