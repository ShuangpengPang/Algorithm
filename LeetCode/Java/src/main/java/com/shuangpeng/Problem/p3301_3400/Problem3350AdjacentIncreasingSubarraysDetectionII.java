package com.shuangpeng.Problem.p3301_3400;

import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3350AdjacentIncreasingSubarraysDetectionII（检测相邻子数组II）
 * @date 2025/3/14 15:28
 */
public class Problem3350AdjacentIncreasingSubarraysDetectionII {

    public int maxIncreasingSubarrays(List<Integer> nums) {
        int n = nums.size();
        int p = 0, cnt = 0, ans = 0;
        for (int i = 0; i < n; i++) {
            cnt++;
            if (i == n - 1 || nums.get(i) >= nums.get(i + 1)) {
                ans = Math.max(ans, Math.max(cnt >> 1, Math.min(p, cnt)));
                p = cnt;
                cnt = 0;
            }
        }
        return ans;
    }
}
