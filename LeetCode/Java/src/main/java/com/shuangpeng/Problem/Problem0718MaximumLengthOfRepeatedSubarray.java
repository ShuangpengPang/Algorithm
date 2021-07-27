package com.shuangpeng.Problem;

import java.util.HashSet;
import java.util.Set;

public class Problem0718MaximumLengthOfRepeatedSubarray {

    public int findLength0(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int[] dp = new int[n2 + 1];
        int maxLength = 0;
        for (int i = 1; i <= n1; i++) {
            for (int j = n2; j >= 1; j--) {
                dp[j] = nums1[i - 1] == nums2[j - 1] ? dp[j - 1] + 1 : 0;
                maxLength = Math.max(maxLength, dp[j]);
            }
        }
        return maxLength;
    }

    public int findLength1(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int maxLength = 0;
        for (int i = 0; i < n1; i++) {
            maxLength = Math.max(maxLength, maxSubarray(nums1, nums2, i, 0));
        }
        for (int i = 0; i < n2; i++) {
            maxLength = Math.max(maxLength, maxSubarray(nums1, nums2, 0, i));
        }
        return maxLength;
    }

    private int maxSubarray(int[] nums1, int[] nums2, int startA, int startB) {
        int length = Math.min(nums1.length - startA, nums2.length - startB);
        int maxLength = 0;
        int k = 0;
        for (int i = 0; i < length; i++) {
            k = nums1[startA + i] == nums2[startB + i] ? k + 1 : 0;
            maxLength = Math.max(maxLength, k);
        }
        return maxLength;
    }

    public int findLength2(int[] nums1, int[] nums2) {
        int n = Math.min(nums1.length, nums2.length);
        int left = 0, right = n;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (check0(nums1, nums2, mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left - 1;
    }

    private boolean check0(int[] nums1, int[] nums2, int length) {
        int base = 101;
        Set<Double> set = new HashSet<>();
        double hash = 0;
        double maxValue = Math.pow(base, length);
        for (int i = 0; i < nums1.length; i++) {
            hash = hash * base + nums1[i];
            if (i == length - 1) {
                set.add(hash);
            } else if (i >= length) {
                hash -= nums1[i - length] * maxValue;
                set.add(hash);
            }
        }
        hash = 0;
        for (int i = 0; i < nums2.length; i++) {
            hash = hash * base + nums2[i];
            if (i >= length) {
                hash -= nums2[i - length] * maxValue;
            }
            if (i >= length - 1 && set.contains(hash)) {
                return true;
            }
        }
        return false;
    }

    int mod = 1000000009;
    int base = 113;

    public int findLength(int[] A, int[] B) {
        int left = 1, right = Math.min(A.length, B.length) + 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(A, B, mid)) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    public boolean check(int[] A, int[] B, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + A[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<Long>();
        bucketA.add(hashA);
        long mult = qPow(base, len - 1);
        for (int i = len; i < A.length; i++) {
            hashA = ((hashA - A[i - len] * mult % mod + mod) % mod * base + A[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + B[i]) % mod;
        }
        if (bucketA.contains(hashB)) {
            return true;
        }
        for (int i = len; i < B.length; i++) {
            hashB = ((hashB - B[i - len] * mult % mod + mod) % mod * base + B[i]) % mod;
            if (bucketA.contains(hashB)) {
                return true;
            }
        }
        return false;
    }

    // 使用快速幂计算 x^n % mod 的值
    public long qPow(long x, long n) {
        long ret = 1;
        while (n != 0) {
            if ((n & 1) != 0) {
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }

//    public static void main(String[] args) {
//        int[] nums1 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
//        int[] nums2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0};
//        Problem0718MaximumLengthOfRepeatedSubarray a = new Problem0718MaximumLengthOfRepeatedSubarray();
//        a.findLength(nums1, nums2);
//    }
}
