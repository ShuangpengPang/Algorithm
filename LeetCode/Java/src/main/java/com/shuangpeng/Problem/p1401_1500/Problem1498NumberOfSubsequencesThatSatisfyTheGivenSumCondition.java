package com.shuangpeng.Problem.p1401_1500;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition（满足条件的子序列数目）
 * @date 2023/8/29 5:35 PM
 */
public class Problem1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition {

    public int numSubseq(int[] nums, int target) {
        int n = nums.length;
        long ans = 0, N = (long) 1e9 + 7;
        long[] cnt = new long[n + 1];
        cnt[0] = 1;
        for (int i = 1; i <= n; i++) {
            cnt[i] = (cnt[i - 1] << 1) % N;
        }
        Arrays.sort(nums);
        for (int i = 0, j = n - 1; i <= j; i++) {
            while (i <= j && nums[i] + nums[j] > target) {
                ans = (ans + (cnt[i] - 1) * cnt[j - i]) % N;
                j--;
            }
            if (i <= j) {
                ans = (ans + (i == j ? cnt[i] : (cnt[i + 1] - 1) * cnt[j - i - 1])) % N;
            }
        }
        return (int) ans;
    }
}

class Problem1498NumberOfSubsequencesThatSatisfyTheGivenSumCondition0 {

    private static int N = (int) 1e5, M = (int) 1e9 + 7;
    private static int[] power = new int[N + 1];
    static {
        power[0] = 1;
        for (int i = 1; i <= N; i++) {
            power[i] = (power[i - 1] << 1) % M;
        }
    }

    public int numSubseq(int[] nums, int target) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int i = 0, j = n - 1; i < n && nums[i] << 1 <= target; i++) {
            while (j > i && nums[j] > target - nums[i]) {
                j--;
            }
            ans = (ans + power[Math.max(0, j - i)]) % M;
        }
        return ans;
    }

    // 错误解法：
    public int numSubseq0(int[] nums, int target) {
        Arrays.sort(nums);
        int j = nums.length - 1;
        while (j >= 0 && nums[0] + nums[j] > target) {
            j--;
        }
        int ans = 0;
        for (int i = 0; j >= 0; j--) {
            while (i + 1 <= j && nums[i + 1] + nums[j] <= target) {
                i++;
            }
            // power[j] - power[Math.max(0, j - i - 1)]不能相减
            ans = (ans + power[j] - power[Math.max(0, j - i - 1)] + (i >= j ? 1 : 0) + M) % M;
        }
        return ans;
    }
}
