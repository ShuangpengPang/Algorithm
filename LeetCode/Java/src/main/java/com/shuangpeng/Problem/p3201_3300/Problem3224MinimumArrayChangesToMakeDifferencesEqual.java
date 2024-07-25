package com.shuangpeng.Problem.p3201_3300;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3224MinimumArrayChangesToMakeDifferencesEqual（使差值相等的最少数组改动次数）
 * @date 2024/7/25 11:16 AM
 */
public class Problem3224MinimumArrayChangesToMakeDifferencesEqual {

    public int minChanges0(int[] nums, int k) {
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

    public int minChanges(int[] nums, int k) {
        int n = nums.length, h = n >> 1;
        k = 0;
        for (int i = 0; i < h; i++) {
            if (nums[i] > nums[n - i - 1]) {
                int t = nums[i];
                nums[i] = nums[n - i - 1];
                nums[n - i - 1] = t;
            }
            k = Math.max(k, nums[n - i - 1]);
        }
        int[] diff = new int[k + 2];
        for (int i = 0; i < h; i++) {
            int p = nums[i], q = nums[n - i - 1], d = q - p, m = Math.max(q, k - p) + 1;
            diff[0]++;
            diff[d]--;
            diff[d + 1]++;
            diff[m]++;
        }
        int ans = n, sum = 0;
        for (int i = 0; i <= k; i++) {
            sum += diff[i];
            ans = Math.min(ans, sum);
        }
        return ans;
    }
}
