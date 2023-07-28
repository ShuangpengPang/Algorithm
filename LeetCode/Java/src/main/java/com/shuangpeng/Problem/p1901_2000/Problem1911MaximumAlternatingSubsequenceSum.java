package com.shuangpeng.Problem.p1901_2000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1911MaximumAlternatingSubsequenceSum（最大子序列交替和）
 * @date 2023/7/11 10:31 AM
 */
public class Problem1911MaximumAlternatingSubsequenceSum {

    public long maxAlternatingSum0(int[] nums) {
        long ans = 0;
        int prev = 0, i = 0, n = nums.length;
        while (i < n) {
            while (i + 1 < n && nums[i] <= nums[i + 1]) {
                i++;
            }
            ans += nums[i] - prev;
            while (i + 1 < n && nums[i] >= nums[i + 1]) {
                i++;
            }
            if (i < n) {
                prev = nums[i];
            }
            i++;
        }
        return ans;
    }

    public long maxAlternatingSum1(int[] nums) {
        int n = nums.length, i = 0, min = 0;
        long sum = 0;
        while (i < n) {
            while (i < n && (i == 0 || nums[i] >= nums[i - 1])) {
                i++;
            }
            sum += nums[i - 1] - min;
            while (i < n && nums[i] <= nums[i - 1]) {
                i++;
            }
            min = nums[i - 1];
        }
        return sum;
    }

    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long even = Integer.MIN_VALUE >> 1, odd = 0;
        for (int i = 0; i < n; i++) {
            long tmp = even;
            even = Math.max(even, odd + nums[i]);
            odd = Math.max(odd, tmp - nums[i]);
        }
        return even;
    }
}
