package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3224MinimumArrayChangesToMakeDifferencesEqual（使差值相等的最少数组改动次数）
 * @date 2024/7/25 11:16 AM
 */
public class Problem3224MinimumArrayChangesToMakeDifferencesEqual {

    public int minChanges(int[] nums, int k) {
        int[] cnt = new int[k + 1], cnt1 = new int[k + 1];
        int n = nums.length, h = n >> 1;
        for (int i = 0; i < h; i++) {
            int p = nums[i], q = nums[n - i - 1];
            if (p > q) {
                int t = p;
                p = q;
                q = t;
            }
            cnt[q - p]++;
            cnt1[Math.max(q, k - p)]++;
        }
        int sum = 0, ans = n;
        for (int i = 0; i <= k; i++) {
            ans = Math.min(ans, h - cnt[i] + sum);
            sum += cnt1[i];
        }
        return ans;
    }
}
