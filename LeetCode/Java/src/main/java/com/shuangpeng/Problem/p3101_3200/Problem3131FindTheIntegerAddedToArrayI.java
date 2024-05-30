package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3131FindTheIntegerAddedToArrayI（找出与数组相加的整数I）
 * @date 2024/5/30 11:36 AM
 */
public class Problem3131FindTheIntegerAddedToArrayI {

    public int addedInteger(int[] nums1, int[] nums2) {
        int min1 = Integer.MAX_VALUE, min2 = min1;
        for (int n = nums1.length, i = 0; i < n; i++) {
            min1 = Math.min(min1, nums1[i]);
            min2 = Math.min(min2, nums2[i]);
        }
        return min2 - min1;
    }
}
