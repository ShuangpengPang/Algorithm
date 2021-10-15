package com.shuangpeng.competition.第062场双周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2025 {

    public int waysToPartition(int[] nums, int k) {
        int n = nums.length;
        int[] preSum = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }
        Map<Integer, Integer> right = new HashMap<>();
        for (int i = 1; i < n; ++i) {
            right.put(preSum[i], right.getOrDefault(preSum[i], 0) + 1);
        }
        int ans = 0;
        if ((preSum[n] & 1) == 0) {
            ans = right.getOrDefault(preSum[n] >> 1, 0);
        }
        Map<Integer, Integer> left = new HashMap<>();
        for (int i = 1; i <= n; ++i) {
            int add = k - nums[i - 1];
            if (((preSum[n] + add) & 1) == 0) {
                int half = (preSum[n] + add) >> 1;
                ans = Math.max(ans, left.getOrDefault(half, 0) + right.getOrDefault(half - add, 0));
            }
            left.put(preSum[i], left.getOrDefault(preSum[i], 0) + 1);
            right.put(preSum[i], right.getOrDefault(preSum[i], 0) - 1);
        }
        return ans;
    }
}
