package com.shuangpeng.competition.第302场周赛;

import java.util.Arrays;

/**
 * @Description: Problem2344MinimumDeletionsToMakeArrayDivisible（使数组可以被整除的最少删除次数）
 * @Date 2022/7/23 5:34 PM
 * @Version 1.0
 */
public class Problem2344MinimumDeletionsToMakeArrayDivisible {

    public int minOperations(int[] nums, int[] numsDivide) {
        int n = nums.length, m = numsDivide.length;
        int g = numsDivide[0];
        for (int i = 1; i < m && g > 1; i++) {
            g = gcd(numsDivide[i], g);
        }
        Arrays.sort(nums);
        int idx = 0;
        while (idx < n && (g % nums[idx]) != 0) {
            idx++;
        }
        return idx < n ? idx : -1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

class Problem2344MinimumDeletionsToMakeArrayDivisible0 {

    public int minOperations(int[] nums, int[] numsDivide) {
        int m = numsDivide.length, g = numsDivide[0];
        for (int i = 1; i < m && g > 1; i++) {
            g = gcd(numsDivide[i], g);
        }
        int num = Integer.MAX_VALUE;
        for (int d : nums) {
            if (g % d == 0) {
                num = Math.min(num, d);
            }
        }
        if (num == Integer.MAX_VALUE) {
            return -1;
        }
        int ans = 0;
        for (int d : nums) {
            if (d < num) {
                ans++;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
