package com.shuangpeng.Problem.p0901_1000;

/**
 * @Description: Problem0915PartitionArrayIntoDisjointIntervals（分割数组）
 * @Date 2022/10/24 10:10 AM
 * @Version 1.0
 */
public class Problem0915PartitionArrayIntoDisjointIntervals {

    public int partitionDisjoint0(int[] nums) {
        int n = nums.length;
        int[] maxValue = new int[n];
        maxValue[0] = nums[0];
        for (int i = 1; i < n; i++) {
            maxValue[i] = Math.max(maxValue[i - 1], nums[i]);
        }
        int ans = 0, idx = n - 2, minValue = nums[n - 1];
        while (idx >= 0) {
            if (minValue >= maxValue[idx]) {
                ans = idx;
            }
            minValue = Math.min(minValue, nums[idx]);
            idx--;
        }
        return ans + 1;
    }

    public int partitionDisjoint(int[] nums) {
        int n = nums.length, ans = 0, max = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] < max) {
                while (ans < i) {
                    max = Math.max(max, nums[ans++]);
                }
            }
        }
        return ans + 1;
    }
}
