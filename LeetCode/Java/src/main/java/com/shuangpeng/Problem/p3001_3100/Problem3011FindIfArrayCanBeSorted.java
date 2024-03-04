package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3011FindIfArrayCanBeSorted（判断一个数组是否可以变为有序）
 * @date 2024/3/4 7:34 PM
 */
public class Problem3011FindIfArrayCanBeSorted {

    public boolean canSortArray0(int[] nums) {
        int preMax = 0, min = 0, max = 0, cnt = 0;
        for (int num : nums) {
            int c = Integer.bitCount(num);
            if (c != cnt) {
                cnt = c;
                preMax = max;
                min = max = num;
            } else {
                min = Math.min(min, num);
                max = Math.max(max, num);
            }
            if (min < preMax) {
                return false;
            }
        }
        return true;
    }

    public boolean canSortArray(int[] nums) {
        int n = nums.length, preMax = 0;
        int i = 0;
        while (i < n) {
            int ones = Integer.bitCount(nums[i]), max = 0;
            while (i < n && Integer.bitCount(nums[i]) == ones) {
                if (nums[i] < preMax) {
                    return false;
                }
                max = Math.max(max, nums[i++]);
            }
            preMax = max;
        }
        return true;
    }
}
