package com.shuangpeng.Problem.p0601_0700;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem0697DegreeOfAnArray（数组的度）
 * @date 2023/12/16 12:11 AM
 */
public class Problem0697DegreeOfAnArray {

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, int[]> range = new HashMap<>();
        int n = nums.length, maxFreq = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            maxFreq = Math.max(maxFreq, freq.merge(nums[i], 1, Integer::sum));
            range.computeIfAbsent(nums[i], k -> new int[]{j, j})[1] = i;
        }
        int ans = n;
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() == maxFreq) {
                int[] r = range.get(entry.getKey());
                ans = Math.min(ans, r[1] - r[0] + 1);
            }
        }
        return ans;
    }
}
