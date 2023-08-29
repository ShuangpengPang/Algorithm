package com.shuangpeng.Problem.p0801_0900;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:（带因子的二叉树）
 * @date 2023/8/29 11:25 AM
 **/
public class Problem0823BinaryTreesWithFactors {

    public int numFactoredBinaryTrees0(int[] arr) {
        Arrays.sort(arr);
        Map<Integer, Integer> dp = new HashMap<>();
        int n = arr.length;
        final int M = (int) 1e9 + 7;
        int count = 0;
        for (int i = 0; i < n; ++i) {
            int c = 1;
            for (int j = 0; j < i; ++j) {
                if (arr[i] % arr[j] == 0) {
                    int k = arr[i] / arr[j];
                    c = (int) (((long) dp.get(arr[j]) * dp.getOrDefault(k, 0) + c) % M);
                }
            }
            count += c;
            if (count >= M) {
                count -= M;
            }
            dp.put(arr[i], c);
        }
        return count;
    }

    public int numFactoredBinaryTrees1(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, N = (int) 1e9 + 7;
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(arr[i], i);
        }
        int[] dp = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; (long) arr[j] * arr[j] <= arr[i]; j++) {
                if (arr[i] % arr[j] == 0) {
                    int num = arr[i] / arr[j], k = indexMap.getOrDefault(num, -1);
                    if (k != -1) {
                        dp[i] = (int) ((dp[i] + (arr[j] == num ? (long) dp[j] * dp[k] : (long) dp[j] * dp[k] << 1)) % N);
                    }
                }
            }
            ans = (ans + dp[i]) % N;
        }
        return ans;
    }

    public int numFactoredBinaryTrees(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, N = (int) 1e9 + 7, ans = 0;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            for (int j = 0, k = i - 1; j <= k; j++) {
                if (arr[i] % arr[j] == 0) {
                    int num = arr[i] / arr[j];
                    while (j <= k && arr[k] > num) {
                        k--;
                    }
                    if (k >= j && arr[k] == num) {
                        dp[i] = (int) ((dp[i] + (k == j ? (long) dp[j] * dp[k] : (long) dp[j] * dp[k] << 1)) % N);
                    }
                }
            }
            ans = (ans + dp[i]) % N;
        }
        return ans;
    }
}
