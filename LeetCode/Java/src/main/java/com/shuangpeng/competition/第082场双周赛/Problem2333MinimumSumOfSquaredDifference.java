package com.shuangpeng.competition.第082场双周赛;

import java.util.Arrays;

/**
 * @Description: Problem2333MinimumSumOfSquaredDifference（最小差值平方和）
 * @Date 2022/7/15 12:16 PM
 * @Version 1.0
 */
public class Problem2333MinimumSumOfSquaredDifference {

    public long minSumSquareDiff0(int[] nums1, int[] nums2, int k1, int k2) {
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

    public long minSumSquareDiff1(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        Integer[] diff = new Integer[n];
        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
        }
        Arrays.sort(diff, (a, b) -> b - a);
        int k = k1 + k2;
        long sum = 0;
        int i = 0;
        while (i < n) {
            long s = (long) diff[i] * i;
            if (k <= sum - s) {
                break;
            }
            sum += diff[i];
            i++;
        }
        if (k > sum) {
            return 0;
        }
        long ans = 0;
        if (i > 0) {
            long num = (sum - k) / i;
            int count = (int) ((sum - k) % i);
            for (int j = 0; j < count; j++) {
                ans += (num + 1) * (num + 1);
            }
            for (int j = count; j < i; j++) {
                ans += num * num;
            }
        }
        for (int j = i; j < n; j++) {
            ans += (long) diff[j] * diff[j];
        }
        return ans;
    }

//    public long minSumSquareDiff(int[] a, int[] nums2, int k1, int k2) {
//        long sum = 0L, ans = 0L;
//        int n = a.length;
//        for (int i = 0; i < n; i++) {
//            a[i] = Math.abs(a[i] - nums2[i]);
//            sum += a[i];
//            ans += (long) a[i] * a[i];
//        }
//        int k = k1 + k2;
//        if (sum <= k) {
//            return 0;
//        }
//        Arrays.sort(a);
//        for (int i = n - 1; i >= 0; i--) {
//            int m = n - i;
//            long v = a[i], c = m * (a[i] - (i > 0 ? a[i - 1] : 0));
//            ans -= v * v;
//            if (c < k) {
//                k -= c;
//                continue;
//            }
//            v -= k / m;
//            int mod = k % m;
//
//        }
//    }

//    public static void main(String[] args) {
//        int[] nums1 = {1,2,3,4};
//        int[] nums2 = {2,10,20,19};
//        Problem2333MinimumSumOfSquaredDifference a = new Problem2333MinimumSumOfSquaredDifference();
//        a.minSumSquareDiff(nums1, nums2, 0, 0);
//    }
}
