package com.shuangpeng.Problem.p3101_3200;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3101CountAlternatingSubarrays（交替子数组计数）
 * @date 2024/4/8 10:59 AM
 */
public class Problem3101CountAlternatingSubarrays {

    public long countAlternatingSubarrays0(int[] nums) {
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

    public long countAlternatingSubarrays(int[] nums) {
        long ans = 0;
        for (int n = nums.length, cnt = 0, i = 0; i < n; i++) {
            cnt = i > 0 && nums[i] != nums[i - 1] ? cnt + 1 : 1;
            ans += cnt;
        }
        return ans;
    }
}
