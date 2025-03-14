package com.shuangpeng.Problem.p3401_3500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3471FindTheLargestAlmostMissingInteger（找出最大的几近缺失数据）
 * @date 2025/3/14 16:13
 */
public class Problem3471FindTheLargestAlmostMissingInteger {

    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        if (k == n) {
            return Arrays.stream(nums).max().getAsInt();
        }
        if (k == 1) {
            int ans = -1;
            for (int i = 0; i < n; i++) {
                if (check(nums, nums[i])) {
                    ans = Math.max(ans, nums[i]);
                }
            }
            return ans;
        }
        return Math.max(check(nums, nums[0]) ? nums[0] : -1, check(nums, nums[n - 1]) ? nums[n - 1] : -1);
    }

    private boolean check(int[] nums, int x) {
        int cnt = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == x && ++cnt > 1) {
                return false;
            }
        }
        return true;
    }
}
