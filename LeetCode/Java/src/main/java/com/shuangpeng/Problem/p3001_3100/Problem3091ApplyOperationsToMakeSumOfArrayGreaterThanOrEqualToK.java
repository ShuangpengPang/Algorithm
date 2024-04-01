package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3091ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToK（执行操作使数据元素之和大于等于K）
 * @date 2024/4/1 5:16 PM
 */
public class Problem3091ApplyOperationsToMakeSumOfArrayGreaterThanOrEqualToK {

    public int minOperations(int k) {
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
}
