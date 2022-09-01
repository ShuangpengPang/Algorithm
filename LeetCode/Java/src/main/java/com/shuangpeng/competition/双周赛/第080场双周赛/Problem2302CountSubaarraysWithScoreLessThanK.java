package com.shuangpeng.competition.双周赛.第080场双周赛;

/**
 * @Description: Problem2302CountSubaarraysWithScoreLessThanK（统计得分小于K的子数组数目）
 * @Date 2022/6/25 5:42 PM
 * @Version 1.0
 */
public class Problem2302CountSubaarraysWithScoreLessThanK {

    // 比赛时写法
    public long countSubarrays0(int[] nums, long k) {
        int n = nums.length;
        long sum = 0;
        long ans = 0;
        for (int l = 0, r = 0; r < n; ++r) {
            // sum * nums[r] * (r - l + 1) >= k
            while (l <= r && ((sum + nums[r]) * (r - l + 1) < 0 || (sum + nums[r]) * (r - l + 1) >= k)) {
                sum -= nums[l++];
            }
            sum += nums[r];
            ans += r - l + 1;
        }
        return ans;
    }

    public long countSubarrays1(int[] nums, long k) {
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

    public long countSubarrays(int[] nums, long k) {
        int n = nums.length;
        long ans = 0, sum = 0;
        for (int l = 0, r = 0; r < n; r++) {
            sum += nums[r];
            while (l <= r && sum * (r - l + 1) >= k) {
                sum -= nums[l++];
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
