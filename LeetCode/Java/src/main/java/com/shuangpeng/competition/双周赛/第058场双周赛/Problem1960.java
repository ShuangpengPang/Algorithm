package com.shuangpeng.competition.双周赛.第058场双周赛;

public class Problem1960 {

    public long maxProduct0(String s) {
        int n = s.length();
        int[] dpLeft = new int[n];
        int[] dpRight = new int[n];
        int[] dp = new int[n];
        maxPalindromic(s, dp);
        int k = 0;
        dpLeft[k] = 1;
        for (int i = 0; i < n; ++i) {
            int r = i + dp[i];
            while (r > k) {
                k++;
                dpLeft[k] = ((k - i) << 1) + 1;
            }
        }
        k = n - 1;
        dpRight[k] = 1;
        for (int i = n - 1; i >= 0; --i) {
            int l = i - dp[i];
            while (k > l) {
                k--;
                dpRight[k] = ((i - k) << 1) + 1;
            }
        }
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = dpLeft[0];
        right[n - 1] = dpRight[n - 1];
        for (int i = 1; i < n; ++i) {
            left[i] = Math.max(dpLeft[i], left[i - 1]);
            right[n - i - 1] = Math.max(dpRight[n - i - 1], right[n - i]);
        }
        long maxValue = Long.MIN_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            maxValue = Math.max(maxValue, (long) left[i] * (long) right[i + 1]);
        }
        return maxValue;
    }

    private void maxPalindromic0(String s, int[] dp) {
        int n = s.length();
        int center = 0, left = 0, right = 0;
        for (int i = 1; i < n; ++i) {
            int j = (center << 1) - i;
            if (j >= 0 && j - dp[j] > left) {
                dp[i] = dp[j];
                continue;
            }
            int r = Math.max(i + 1, right);
            int l = (i << 1) - r;
            while (r < n && l >= 0 && s.charAt(l) == s.charAt(r)) {
                r++;
                l = (i << 1) - r;
            }
            dp[i] = r - i - 1;
            center = i;
            left = i - dp[i];
            right = i + dp[i];
        }
    }

    private void maxPalindromic(String s, int[] dp) {
        int n = s.length();
        for (int i = 1, left = 0, right = 0; i < n; ++i) {
            if (i < right) {
                dp[i] = Math.min(dp[left + right - i], right - i);
            }
            int r = i + dp[i] + 1;
            int l = (i << 1) - r;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                r++;
                l--;
            }
            dp[i] = r - i - 1;
            left = Math.min(left, i - dp[i]);
            right = Math.max(right, i + dp[i]);
        }
    }

    public long maxProduct(String s) {
        int n = s.length();
        int[] dp = new int[n];
        maxPalindromic(s, dp);
        int[] left = new int[n];
        int[] right = new int[n];
        left[0] = 1;
        for (int i = 1, p = 0; i < n; ++i) {
            while (p + dp[p] < i) {
                p++;
            }
            left[i] = Math.max(left[i - 1], ((i - p) << 1) + 1);
        }
        right[n - 1] = 1;
        for (int i = n - 2, p = n - 1; i >= 0; --i) {
            while (p - dp[p] > i) {
                p--;
            }
            right[i] = Math.max(right[i + 1], ((p - i) << 1) + 1);
        }
        long maxValue = Long.MIN_VALUE;
        for (int i = 0; i < n - 1; ++i) {
            maxValue = Math.max(maxValue, (long) left[i] * right[i + 1]);
        }
        return maxValue;
    }
}
