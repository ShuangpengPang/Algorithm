package com.shuangpeng.Problem.p3301_3400;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3349AdjacentIncreasingSubarraysDetectionI（检测相邻递增子数组I）
 * @date 2025/3/14 13:41
 */
public class Problem3349AdjacentIncreasingSubarraysDetectionI {

    public boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
        int n = nums.size();
        int[] p = new int[n];
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            cnt = i == 0 || nums.get(i) > nums.get(i - 1) ? cnt + 1 : 1;
            if (cnt >= k && i >= k && p[i - k] >= k) {
                return true;
            }
            p[i] = cnt;
        }
        return false;
    }
}
