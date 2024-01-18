package com.shuangpeng.Problem.p2501_2600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2518NumberOfGreatPartition（好分区的数目）
 * @date 2024/1/18 9:37 AM
 */
public class Problem2518NumberOfGreatPartition {

    private static int N = (int) 1e9 + 7;

    public int countPartitions(int[] nums, int k) {
        long sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < k << 1) {
            return 0;
        }
        int[] dp = new int[k];
        dp[0] = 1;
        for (int num : nums) {
            for (int i = k - 1; i >= num; i--) {
                dp[i] = (dp[i] + dp[i - num]) % N;
            }
        }
        int ans = 0;
        for (int num : dp) {
            ans = (ans + num) % N;
        }
        ans = (ans << 1) % N;
        return (pow(2, nums.length) - ans + N) % N;
    }

    private int pow(long n, long p) {
        long ans = 1;
        while (p != 0) {
            if ((p & 1) == 1) {
                ans = ans * n % N;
            }
            n = n * n % N;
            p >>= 1;
        }
        return (int) ans;
    }
}
