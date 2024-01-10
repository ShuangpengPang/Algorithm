package com.shuangpeng.Problem.p2901_3000;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2958LengthOfLongestSubarrayWithAtMostKFrequency（最多K个重复元素的最长子数组）
 * @date 2024/1/10 11:22 PM
 */
public class Problem2958LengthOfLongestSubarrayWithAtMostKFrequency {

    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0, n = nums.length; j < n; j++) {
            if (map.getOrDefault(nums[j], 0) == k) {
                while (nums[i] != nums[j]) {
                    map.merge(nums[i], -1, Integer::sum);
                    i++;
                }
                i++;
            } else {
                map.merge(nums[j], 1, Integer::sum);
            }
            ans = Math.max(ans, j - i);
        }
        return ans + 1;
    }
}
