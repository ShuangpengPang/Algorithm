package com.shuangpeng.Problem.p0301_0400;

public class Problem0376WiggleSubsequence {

    public int wiggleMaxLength0(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 1;
        dp[0][1] = 1;
        int max = 1;
        for (int i = 1; i < n; i++) {
            int a = Integer.MIN_VALUE;
            int b = Integer.MIN_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i]) {
                    a = Math.max(a, dp[j][1] + 1);
                } else if (nums[j] > nums[i]) {
                    b = Math.max(b, dp[j][0] + 1);
                } else {
                    a = Math.max(a, dp[j][0]);
                    b = Math.max(b, dp[j][1]);
                }
                max = Math.max(max, Math.max(a, b));
                dp[i][0] = a;
                dp[i][1] = b;
            }
        }
        return max;
    }

    public int wiggleMaxLength1(int[] nums) {
        int n = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                up = Math.max(up, down + 1);
            } else if (nums[i - 1] > nums[i]) {
                down = Math.max(down, up + 1);
            }
        }
        return Math.max(up, down);
    }

    public int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) {
            return n;
        }
        int prediff = nums[1] - nums[0];
        int answer = prediff != 0 ? 2 : 1;
        for (int i = 2; i < n; i++) {
            int diff = nums[i] - nums[i - 1];
            if ((diff > 0 && prediff <= 0) || (diff < 0 && prediff >= 0)) {
                answer++;
                prediff = diff;
            }
        }
        return answer;
    }
}
