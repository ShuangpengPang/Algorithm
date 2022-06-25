package com.shuangpeng.competition.第080场双周赛;

/**
 * @Description: Problem2302CountSubaarraysWithScoreLessThanK（统计得分小于K的子数组数目）
 * @Date 2022/6/25 5:42 PM
 * @Version 1.0
 */
public class Problem2302CountSubaarraysWithScoreLessThanK {

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long[] preSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
        }
        long ans = 0;
        for (int l = 1, r = 1; r <= n; r++) {
            while (l <= r && (preSum[r] - preSum[l - 1]) * (r - l + 1) >= k) {
                l++;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
