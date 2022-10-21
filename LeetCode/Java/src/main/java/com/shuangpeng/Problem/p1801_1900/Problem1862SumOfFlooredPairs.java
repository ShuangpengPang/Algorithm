package com.shuangpeng.Problem.p1801_1900;

/**
 * @Description: Problem1862SumOfFlooredPairs（向下取整数对和）
 * @Date 2022/10/21 2:24 PM
 * @Version 1.0
 */
public class Problem1862SumOfFlooredPairs {

    static int N = (int) 1e5;
    static int[] arr = new int[N + 1], suffixSum = new int[N + 1];

    public int sumOfFlooredPairs(int[] nums) {
        int M = 0;
        for (int i : nums) {
            M = Math.max(M, i);
        }
        for (int i = 0; i <= M; i++) {
            arr[i] = 0;
            suffixSum[i] = 0;
        }
        for (int i : nums) {
            arr[i]++;
        }
        suffixSum[M] = arr[M];
        for (int i = M - 1; i >= 1; i--) {
            suffixSum[i] = arr[i] + suffixSum[i + 1];
        }
        long ans = 0, MOD = (long) 1e9 + 7;
        for (int i = 0; i <= M; i++) {
            if (arr[i] > 0) {
                for (int j = i; j <= M; j += i) {
                    ans += (long) arr[i] * suffixSum[j];
                }
                ans %= MOD;
            }
        }
        return (int) ans;
    }
}
