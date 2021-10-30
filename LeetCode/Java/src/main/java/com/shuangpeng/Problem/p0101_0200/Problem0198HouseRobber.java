package com.shuangpeng.Problem.p0101_0200;

public class Problem0198HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int a = 0;
        int b = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int current = Math.max(b, nums[i] + a);
            a = b;
            b = current;
        }
        return b;
    }
}
