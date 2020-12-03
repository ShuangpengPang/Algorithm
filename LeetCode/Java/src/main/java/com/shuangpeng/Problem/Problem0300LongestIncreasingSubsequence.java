package com.shuangpeng.Problem;

public class Problem0300LongestIncreasingSubsequence {

    // [4,10,4,3,8,9]

    // 动态规划
    public int lengthOfLIS0(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        dp[0] = 1;
        int max = 1;
        for (int i = 1; i < length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] >= dp[i]) {
                    dp[i] = dp[j] + 1;
                    if (dp[i] > max) {
                        break;
                    }
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    public int lengthOfLIS2(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length + 1];
        dp[0] = Integer.MIN_VALUE;
        dp[1] = nums[0];
        int max = 1;
        for (int i = 1; i < length; i++) {
            if (nums[i] > dp[max]) {
                dp[++max] = nums[i];
            } else {
                int left = 0;
                int right = max;
                while (left < right) {
                    int mid = (left + right) >>> 1;
                    if (nums[i] > dp[mid]) {
                        left = mid + 1;
                    } else if (nums[i] < dp[mid]) {
                        right = mid;
                    } else {
                        left = mid;
                        break;
                    }
                }
                dp[left] = nums[i];
            }
        }
        return max;
    }

    public int lengthOfLIS(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; ++i) {
            if (nums[i] > d[len]) {
                d[++len] = nums[i];
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }
        return len;
    }
}
