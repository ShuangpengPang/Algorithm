package com.shuangpeng.Problem.p2501_2600;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2551PutMarblesInBags（将珠子放入背包中）
 * @date 2024/1/19 11:50 PM
 */
public class Problem2551PutMarblesInBags {

    public long putMarbles(int[] weights, int k) {
        int n = weights.length;
        int[] sum = new int[n - 1];
        for (int i = 0; i < n - 1; i++) {
            sum[i] = weights[i] + weights[i + 1];
        }
        Arrays.sort(sum);
        long minSum = 0;
        for (int i = 0; i < k - 1; i++) {
            minSum += sum[i];
        }
        long maxSum = 0;
        for (int i = n - k; i < n - 1; i++) {
            maxSum += sum[i];
        }
        return maxSum - minSum;
    }
}
