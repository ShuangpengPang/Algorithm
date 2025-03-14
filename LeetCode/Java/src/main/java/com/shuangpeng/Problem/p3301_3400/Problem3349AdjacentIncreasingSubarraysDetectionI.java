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
        int p = 0, cnt = 0, m = k << 1;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (cnt == m || cnt == k && p >= k) {
                return true;
            }
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                p = cnt;
                cnt = 0;
            }
        }
        return false;
    }
}
