package com.shuangpeng.Problem.p0201_0300;

public class Problem0213HouseRobberII {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int n = nums.length;
        int a = 0, b = 0;
        for (int i = 0; i < n - 1; i++) {
            int t = b;
            b = a + nums[i];
            a = Math.max(a, t);
        }
        int max = Math.max(a, b);
        a = 0;
        b = 0;
        for (int i = 1; i < n; i++) {
            int t = b;
            b = a + nums[i];
            a = Math.max(a, t);
        }
        return Math.max(max, Math.max(a, b));
    }
}
