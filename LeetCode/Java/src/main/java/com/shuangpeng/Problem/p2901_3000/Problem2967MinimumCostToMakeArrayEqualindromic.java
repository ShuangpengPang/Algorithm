package com.shuangpeng.Problem.p2901_3000;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2967MinimumCostToMakeArrayEqualindromic（使数组成为等数数组的最小代价）
 * @date 2024/1/12 10:44 AM
 */
public class Problem2967MinimumCostToMakeArrayEqualindromic {

    public long minimumCost(int[] nums) {
        Arrays.sort(nums);
        int mid = nums[nums.length >> 1];
        return Math.min(getCost(nums, getMinPalindromic(mid)), getCost(nums, getMaxPalindromic(mid)));
    }

    private long getCost(int[] nums, int target) {
        long sum = 0;
        for (int num : nums) {
            sum += Math.abs(num - target);
        }
        return sum;
    }

    private int getMinPalindromic(int num) {
        char[] cs = Integer.toString(num).toCharArray();
        int n = cs.length, h = n - 1 >> 1, i = h;
        while (i >= 0 && cs[i] == cs[n - i - 1]) {
            i--;
        }
        if (i < 0) {
            return num;
        }
        if (cs[i] < cs[n - i - 1]) {
            for (int j = i; j >= 0; j--) {
                cs[n - j - 1] = cs[j];
            }
            return getValue(cs);
        }
        i = h;
        while (cs[i] == '0') {
            i--;
        }
        if (i == 0 && cs[i] == '1') {
            return (int) Math.pow(10, n - 1) - 1;
        }
        cs[i]--;
        for (int j = 0; j <= h; j++) {
            cs[j] = cs[n - j - 1] = j <= i ? cs[j] : '9';
        }
        return getValue(cs);
    }

    private int getMaxPalindromic(int num) {
        char[] cs = Integer.toString(num).toCharArray();
        int n = cs.length, h = n - 1 >> 1, i = h;
        while (i >= 0 && cs[i] == cs[n - i - 1]) {
            i--;
        }
        if (i < 0) {
            return num;
        }
        if (cs[i] > cs[n - i - 1]) {
            for (int j = 0; j <= i; j++) {
                cs[n - j - 1] = cs[j];
            }
            return getValue(cs);
        }
        i = h;
        while (cs[i] == '9') {
            i--;
        }
        cs[i]++;
        for (int j = 0; j <= h; j++) {
            cs[j] = cs[n - j - 1] = j <= i ? cs[j] : '0';
        }
        return getValue(cs);
    }

    private boolean check(char[] cs) {
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs[i] != cs[j]) {
                return false;
            }
        }
        return true;
    }

    private int getValue(char[] cs) {
        int num = 0;
        for (int i = 0; i < cs.length; i++) {
            num = num * 10 + cs[i] - '0';
        }
        return num;
    }
}
