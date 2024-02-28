package com.shuangpeng.Problem.p3001_3100;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3026MaximumGoodSubarraySum（最大好子数组和）
 * @date 2024/2/28 11:36 AM
 */
public class Problem3026MaximumGoodSubarraySum {

    public long maximumSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        Map<Integer, Integer> map = new HashMap<>();
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            if (map.containsKey(nums[i] - k)) {
                ans = Math.max(ans, preSum[i + 1] - preSum[map.get(nums[i] - k)]);
            }
            if (map.containsKey(nums[i] + k)) {
                ans = Math.max(ans, preSum[i + 1] - preSum[map.get(nums[i] + k)]);
            }
            int j = map.getOrDefault(nums[i], -1);
            if (j == -1 || preSum[j] > preSum[i]) {
                map.put(nums[i], i);
            }
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }
}
