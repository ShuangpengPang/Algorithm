package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0045JumpGameII {

    public int jump0(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (i - j <= nums[j]) {
                    min = Math.min(min, dp[j] + 1);
                }
            }
            dp[i] = min;
        }
        return dp[n - 1];
    }

    public int jump1(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            if (dp[i] == Integer.MAX_VALUE) {
                continue;
            }
            for (int j = Math.min(n - i - 1, nums[i]); j >= 1; j--) {
                dp[i + j] = Math.min(dp[i + j], dp[i] + 1);
            }
        }
        return dp[n - 1];
    }

    public int jump2(int[] nums) {
        int n = nums.length;
        int step = 0;
        int i = 0;
        int s = 0;
        for (int j = 1; j < n; j++) {
            if (j - i <= nums[i]) {
                if (j + nums[j] > s + nums[s]) {
                    s = j;
                }
            }
            if ((j - i == nums[i]) || (j == n - 1)) {
                step++;
                i = s;
            }
        }
        return step;
    }

    public int jump3(int[] nums) {
        int length = nums.length;
        int end = 0;
        int maxPosition = 0;
        int steps = 0;
        for (int i = 0; i < length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                end = maxPosition;
                steps++;
            }
        }
        return steps;
    }

    public int jump(int[] nums) {
        int maxPosition = 0;
        int end = 0;
        int step = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            maxPosition = Math.max(maxPosition, i + nums[i]);
            if (i == end) {
                step++;
                end = maxPosition;
            }
        }
        return step;
    }

//    public static void main(String[] args) {
//        Problem0045JumpGameII a = new Problem0045JumpGameII();
//        a.jump(new int[]{2, 3, 1, 1, 4});
//    }
}
