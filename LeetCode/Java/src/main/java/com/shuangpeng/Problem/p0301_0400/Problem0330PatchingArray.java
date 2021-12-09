package com.shuangpeng.Problem.p0301_0400;

public class Problem0330PatchingArray {

    public int minPatches0(int[] nums, int n) {
        long max = 0;
        int ans = 0;
        for (int num : nums) {
            while (max < num - 1 && max < n) {
                ++ans;
                max += max + 1;
            }
            if (max >= n) {
                return ans;
            }
            max += num;
        }
        while (max < n) {
            ++ans;
            max += max + 1;
        }
        return ans;
    }

    public int minPatches(int[] nums, int n) {
        int patches = 0;
        long x = 1;
        int length = nums.length, index = 0;
        while (x <= n) {
            if (index < length && nums[index] <= x) {
                x += nums[index];
                index++;
            } else {
                x *= 2;
                patches++;
            }
        }
        return patches;
    }
}
