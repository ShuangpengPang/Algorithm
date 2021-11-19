package com.shuangpeng.Problem.p0401_0500;

import java.util.Arrays;

public class Problem0453MinimumMovesToEqualArrayElements {

    public int minMoves0(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        int prev = 0;
        int ans = 0;
        for (int i = 1; i < n; ++i) {
            ans += nums[i] - nums[i - 1] + prev;
            prev += nums[i] - nums[i - 1];
        }
        return ans;
    }

    public int minMoves(int[] nums) {
        int minValue = Arrays.stream(nums).min().getAsInt();
        int ans = 0;
        for (int num : nums) {
            ans += num - minValue;
        }
        return ans;
    }
}
