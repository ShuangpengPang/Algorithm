package com.shuangpeng.Problem.p0901_1000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0910SmallestRangeII（最小差值II）
 * @date 2023/3/8 11:58 AM
 */
public class Problem0910SmallestRangeII {

    public int smallestRangeII0(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, min = nums[0], max = nums[0];
        int ans = nums[n - 1] - nums[0];
        k <<= 1;
        for (int i = n - 1; i > 0; i--) {
            max = Math.max(max, nums[i] - k);
            min = Math.min(min, nums[i] - k);
            ans = Math.min(ans, Math.max(max, nums[i - 1]) - min);
        }
        return ans;
    }

    public int smallestRangeII(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, ans = nums[n - 1] - nums[0];
        int high = nums[n - 1] - k, low = nums[0] + k;
        for (int i = 0; i < n - 1; i++) {
            int max = Math.max(nums[i] + k, high);
            int min = Math.min(nums[i + 1] - k, low);
            ans = Math.min(ans, max - min);
        }
        return ans;
    }
}
