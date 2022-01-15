package com.shuangpeng.Problem.p0701_0800;

public class Problem0747LargestNumberAtLeastTwiceOfOthers {

    public int dominantIndex(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return 0;
        }
        int ans = -1, largest = Integer.MIN_VALUE, second = Integer.MIN_VALUE;
        for (int i = 0; i < n; ++i) {
            if (nums[i] > largest) {
                second = largest;
                largest = nums[i];
                ans = i;
            } else if (nums[i] > second) {
                second = nums[i];
            }
        }
        return largest >= second << 1 ? ans : -1;
    }
}
