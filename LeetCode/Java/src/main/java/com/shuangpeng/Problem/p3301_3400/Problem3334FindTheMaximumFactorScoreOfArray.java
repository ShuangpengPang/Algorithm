package com.shuangpeng.Problem.p3301_3400;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3334FindTheMaximumFactorScoreOfArray（数组的最大因子得分）
 * @date 2025/2/26 7:06 PM
 */
public class Problem3334FindTheMaximumFactorScoreOfArray {

    public long maxScore0(int[] nums) {
        int n = nums.length;
        long[] leftGcd = new long[n + 2], leftLcm = new long[n + 2];
        long[] rightGcd = new long[n + 2], rightLcm = new long[n + 2];
        leftGcd[1] = leftLcm[1] = nums[0];
        rightGcd[n] = rightLcm[n] = nums[n - 1];
        for (int i = 2, j = n - 1; i <= n; i++, j--) {
            leftGcd[i] = gcd(leftGcd[i - 1], nums[i - 1]);
            leftLcm[i] = lcm(leftLcm[i - 1], nums[i - 1]);
            rightGcd[j] = gcd(rightGcd[j + 1], nums[j - 1]);
            rightLcm[j] = lcm(rightLcm[j + 1], nums[j - 1]);
        }
        long ans = rightGcd[1] * rightLcm[1];
        ans = Math.max(ans, rightGcd[2] * rightLcm[2]);
        ans = Math.max(ans, leftGcd[n - 1] * leftLcm[n - 1]);
        for (int i = 2; i < n; i++) {
            ans = Math.max(ans, lcm(leftLcm[i - 1], rightLcm[i + 1]) * gcd(leftGcd[i - 1], rightGcd[i + 1]));
        }
        return ans;
    }

    public long maxScore(int[] nums) {
        int n = nums.length;
        long[] sufGcd = new long[n + 1];
        long[] sufLcm = new long[n + 1];
        sufLcm[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            sufGcd[i] = gcd(sufGcd[i + 1], nums[i]);
            sufLcm[i] = lcm(sufLcm[i + 1], nums[i]);
        }
        long ans = sufGcd[0] * sufLcm[0];
        long preGcd = 0, preLcm = 1;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, gcd(preGcd, sufGcd[i + 1]) * lcm(preLcm, sufLcm[i + 1]));
            preGcd = gcd(preGcd, nums[i]);
            preLcm = lcm(preLcm, nums[i]);
        }
        return ans;
    }

    private long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
}
