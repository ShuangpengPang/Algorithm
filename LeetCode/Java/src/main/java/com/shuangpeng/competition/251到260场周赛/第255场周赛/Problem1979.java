package com.shuangpeng.competition.第255场周赛;

public class Problem1979 {

    public int findGCD(int[] nums) {
        int minValue = nums[0], maxValue = nums[0];
        for (int i = 1; i < nums.length; ++i) {
            minValue = Math.min(minValue, nums[i]);
            maxValue = Math.max(maxValue, nums[i]);
        }
        return gcd(maxValue, minValue);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
