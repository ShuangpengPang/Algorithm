package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3101CountAlternatingSubarrays（交替子数组计数）
 * @date 2024/4/8 10:59 AM
 */
public class Problem3101CountAlternatingSubarrays {

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        int n = nums.length, i = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && nums[j] != nums[j - 1]) {
                j++;
            }
            ans += (long) (j - i + 1) * (j - i) >> 1;
            i = j;
        }
        return ans;
    }
}
