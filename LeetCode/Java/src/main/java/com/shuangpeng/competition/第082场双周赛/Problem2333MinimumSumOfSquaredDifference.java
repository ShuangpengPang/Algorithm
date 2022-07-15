package com.shuangpeng.competition.第082场双周赛;

/**
 * @Description: Problem2333MinimumSumOfSquaredDifference（最小差值平方和）
 * @Date 2022/7/15 12:16 PM
 * @Version 1.0
 */
public class Problem2333MinimumSumOfSquaredDifference {

    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int k = k1 + k2;
        int n = nums1.length;
        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            sum += d;
            max = Math.max(max, d);
        }
        if (k >= sum) {
            return 0;
        }
        sum -= k;
        int left = 0, right = max;
        long s = 0;
        long target = 0;
        while (left <= right) {
            int mid = left + (right - left >> 1);
            long temp = 0;
            for (int i = 0; i < n; i++) {
                temp += Math.min(mid, Math.abs(nums1[i] - nums2[i]));
            }
            if (temp > sum) {
                right = mid - 1;
            } else {
                s = temp;
                target = mid;
                left = mid + 1;
            }
        }
        long count = sum - s;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long d = Math.abs(nums1[i] - nums2[i]);
            if (count > 0 && d > target) {
                ans += (target + 1) * (target + 1);
                count--;
            } else {
                d = Math.min(d, target);
                ans += d * d;
            }
        }
        return ans;
    }
}
