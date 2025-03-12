package com.shuangpeng.Problem.p3401_3500;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3427SumOfVariableLengthSubarrays（变长子数组求和）
 * @date 2025/3/12 12:00
 */
public class Problem3427SumOfVariableLengthSubarrays {

    public int subarraySum0(int[] nums) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            ans += preSum[i + 1] - preSum[Math.max(0, i - nums[i])];
        }
        return ans;
    }

    public int subarraySum(int[] nums) {
        int n = nums.length;
        int[] diff = new int[n + 1];
        for (int i = 0; i < n; i++) {
            diff[Math.max(0, i - nums[i])]++;
            diff[i + 1]--;
        }
        int ans = 0, cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt += diff[i];
            ans += nums[i] * cnt;
        }
        return ans;
    }
}
