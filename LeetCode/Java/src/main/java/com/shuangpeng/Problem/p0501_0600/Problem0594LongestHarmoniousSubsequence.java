package com.shuangpeng.Problem.p0501_0600;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0594LongestHarmoniousSubsequence {

    public int findLHS0(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int ans = 0;
        for (int key : map.keySet()) {
            ans = Math.max(ans, map.get(key) + map.getOrDefault(key + 1, Integer.MIN_VALUE));
        }
        return ans;
    }

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            while (nums[r] - nums[l] > 1) {
                ++l;
            }
            if (nums[r] - nums[l] == 1) {
                ans = Math.max(ans, r - l + 1);
            }
        }
        return ans;
    }
}
