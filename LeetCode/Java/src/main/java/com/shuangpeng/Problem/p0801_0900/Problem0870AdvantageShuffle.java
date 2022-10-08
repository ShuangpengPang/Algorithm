package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem0870AdvantageShuffle（优势洗牌）
 * @Date 2022/10/8 10:14 AM
 * @Version 1.0
 */
public class Problem0870AdvantageShuffle {

    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[][] nums = new int[n][2];
        for (int i = 0; i < n; i++) {
            nums[i][0] = nums2[i];
            nums[i][1] = i;
        }
        Arrays.sort(nums1);
        Arrays.sort(nums, Comparator.comparingInt(a -> a[0]));
        int[] ans = new int[n];
        for (int i = 0, l = 0, r = n - 1; i < n; i++) {
            if (nums1[i] > nums[l][0]) {
                ans[nums[l++][1]] = nums1[i];
            } else {
                ans[nums[r--][1]] = nums1[i];
            }
        }
        return ans;
    }
}
