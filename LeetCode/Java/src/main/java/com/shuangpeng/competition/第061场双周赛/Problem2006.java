package com.shuangpeng.competition.第061场双周赛;

import java.util.HashMap;
import java.util.Map;

public class Problem2006 {

    public int countKDifference0(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (Math.abs(nums[i] - nums[j]) == k) {
                    ++ans;
                }
            }
        }
        return ans;
    }

    public int countKDifference1(int[] nums, int k) {
        final int M = 101;
        int[] count = new int[M];
        for (int num : nums) {
            ++count[num];
        }
        int ans = 0;
        for (int i = 1; i + k < M; ++i) {
            ans += count[i] * count[i + k];
        }
        return ans;
    }

    public int countKDifference2(int[] nums, int k) {
        final int M = 101;
        int[] buckets = new int[M];
        for (int num : nums) {
            ++buckets[num];
        }
        int ans = 0;
        for (int i = 1; i + k < M; ++i) {
            ans += buckets[i] * buckets[i + k];
        }
        return ans;
    }

    public int countKDifference3(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : map.keySet()) {
            ans += map.get(num) * map.getOrDefault(num + k, 0);
        }
        return ans;
    }

    public int countKDifference(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            ans += map.getOrDefault(num - k, 0) + map.getOrDefault(num + k, 0);
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return ans;
    }
}
