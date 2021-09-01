package com.shuangpeng.Problem;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem0873LengthOfLongestFibonacciSubsequence {

    public int lenLongestFibSubseq0(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(dp[i], 2);
        }
        Map<Integer, Integer> index = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            index.put(arr[i], i);
        }
        int maxLength = 0;
        for (int i = 2; i < n; ++i) {
            for (int j = i - 1; j > 0 && ((arr[j] << 1) > arr[i]); j--) {
                int k = index.getOrDefault(arr[i] - arr[j], -1);
                if (k != -1) {
                    dp[j][i] = Math.max(dp[j][i], dp[k][j] + 1);
                    maxLength = Math.max(maxLength, dp[j][i]);
                }
            }
        }
        return maxLength >= 3 ? maxLength : 0;
    }

    public int lenLongestFibSubseq1(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> indexMap = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            indexMap.put(arr[i], i);
        }
        int[][] dp = new int[n][n];
        int maxLength = 0;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j < n; ++j) {
                dp[i][j] = 2;
            }
            for (int j = i + 1; j < n - 1 && arr[i] + arr[j] <= arr[n - 1]; ++j) {
                int k = indexMap.getOrDefault(arr[i] + arr[j], -1);
                if (k != -1) {
                    dp[i][j] = dp[j][k] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength > 2 ? maxLength : 0;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        Map<Integer, Integer> index = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            index.put(arr[i], i);
        }
        Map<Integer, Integer> longest = new HashMap<>();
        int maxLength = 0;
        for (int k = 2; k < n; ++k) {
            for (int j = k - 1; j > 0 && ((arr[j] << 1) > arr[k]); --j) {
                int i = index.getOrDefault(arr[k] - arr[j], -1);
                if (i != -1) {
                    int length = longest.getOrDefault(i * n + j, 2) + 1;
                    maxLength = Math.max(maxLength, length);
                    longest.put(j * n + k, length);
                }
            }
        }
        return maxLength;
    }
}
