package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2605FormSmallestNumberFromTwoDigitArrays（从两个数字数组里生成最小数）
 * @date 2023/9/5 11:52 AM
 */
public class Problem2605FormSmallestNumberFromTwoDigitArrays {

    public int minNumber0(int[] nums1, int[] nums2) {
        int[] hash = new int[10];
        int m1 = 10, m2 = 10, m = 10;
        for (int num : nums1) {
            hash[num] |= 1;
            m1 = Math.min(m1, num);
        }
        for (int num : nums2) {
            if ((hash[num] |= 2) == 3) {
                m = Math.min(m, num);
            };
            m2 = Math.min(m2, num);
        }
        if (m < 10) {
            return m;
        }
        if (m1 > m2) {
            int t = m1;
            m1 = m2;
            m2 = t;
        }
        return m1 * 10 + m2;
    }

    public int minNumber(int[] nums1, int[] nums2) {
        boolean[] set = new boolean[10];
        int m1 = 10, m2 = 10, m = 10;
        for (int num : nums1) {
            set[num] = true;
            m1 = Math.min(m1, num);
        }
        for (int num : nums2) {
            if (set[num]) {
                m = Math.min(m, num);
            }
            m2 = Math.min(m2, num);
        }
        if (m < 10) {
            return m;
        }
        if (m1 > m2) {
            int t = m1;
            m1 = m2;
            m2 = t;
        }
        return m1 * 10 + m2;
    }
}
