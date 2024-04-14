package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2956FindCommonElementsBetweenTwoArrays（找到两个数组中的公共元素）
 * @date 2024/4/14 6:15 PM
 */
public class Problem2956FindCommonElementsBetweenTwoArrays {

    public int[] findIntersectionValues(int[] nums1, int[] nums2) {
        int[] set = new int[101];
        for (int num : nums2) {
            set[num]++;
        }
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums1) {
            if (set[num] != 0) {
                cnt1++;
                if (set[num] > 0) {
                    cnt2 += set[num];
                    set[num] = -set[num];
                }
            }
        }
        return new int[]{cnt1, cnt2};
    }
}
