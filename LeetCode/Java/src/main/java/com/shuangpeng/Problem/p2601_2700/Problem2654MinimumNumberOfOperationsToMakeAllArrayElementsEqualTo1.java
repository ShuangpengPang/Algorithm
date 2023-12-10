package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2654MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1（使数组所有元素变成1的最少操作次数）
 * @date 2023/12/10 9:33 PM
 */
public class Problem2654MinimumNumberOfOperationsToMakeAllArrayElementsEqualTo1 {

    public int minOperations(int[] nums) {
        int n = nums.length, cnt = 0, g = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] != 1) {
                cnt++;
                g = gcd(g, nums[i]);
            }
        }
        if (cnt < n) {
            return cnt;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n && g == 1; i++) {
            g = nums[i];
            for (int j = i + 1; j < n && g != 1; j++) {
                g = gcd(g, nums[j]);
                if (g == 1) {
                    ans = Math.min(ans, j - i);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans + n - 1;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
