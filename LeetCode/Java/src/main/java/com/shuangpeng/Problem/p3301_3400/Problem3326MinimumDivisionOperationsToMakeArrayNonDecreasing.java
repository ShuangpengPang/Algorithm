package com.shuangpeng.Problem.p3301_3400;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3326MinimumDivisionOperationsToMakeArrayNonDecreasing（使数组非递减的最少除法操作次数）
 * @date 2025/2/24 7:04 PM
 */
public class Problem3326MinimumDivisionOperationsToMakeArrayNonDecreasing {

    private static final int N = (int) 1e6;
    private static final int[] divisors = new int[N + 1];

    static {
        divisors[1] = 1;
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (divisors[i] == 0) {
                divisors[i] = i;
                primes.add(i);
            }
            for (int p : primes) {
                int j = i * p;
                if (j <= N) {
                    divisors[j] = p;
                }
                if (j > N || i % p == 0) {
                    break;
                }
            }
        }
    }

    public int minOperations(int[] nums) {
        int n = nums.length, next = nums[n - 1];
        int ans = 0;
        for (int i = n - 2; i >= 0; i--) {
            int t = nums[i];
            if (t > next) {
                t = divisors[t];
                ans++;
            }
            if (t > next) {
                return -1;
            }
            next = t;
        }
        return ans;
    }
}
