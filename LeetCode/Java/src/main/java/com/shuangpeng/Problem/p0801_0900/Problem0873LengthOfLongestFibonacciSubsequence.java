package com.shuangpeng.Problem.p0801_0900;

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

    public int lenLongestFibSubseq2(int[] arr) {
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

    // TLE
    public int lenLongestFibSubseq3(int[] arr) {
        int n = arr.length;
        Map<Integer, Map<Integer, Integer>> map = new HashMap<>(n);
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int num = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                map.putIfAbsent(num, new HashMap<>(i));
                Map<Integer, Integer> m = map.get(num);
                if (j > 0 && arr[j] > num / 2) {
                    int len = map.get(arr[j]).getOrDefault(num - arr[j], 1) + 1;
                    m.put(arr[j], len);
                    ans = Math.max(ans, len);
                } else {
                    m.put(arr[j], 2);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq4(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int num = arr[i];
            for (int l = 0, r = i - 1; r >= 0; r--) {
                if (l < r) {
                    while (arr[l] < num - arr[r]) {
                        l++;
                    }
                    if (l < r && arr[l] + arr[r] == num) {
                        dp[r][i] = dp[l][r] + 1;
                        ans = Math.max(ans, dp[r][i]);
                    } else {
                        dp[r][i] = 2;
                    }
                } else {
                    dp[r][i] = 2;
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq5(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];
        int ans = 0;
        for (int i = 1; i < n; i++) {
            int num = arr[i];
            for (int l = 0, r = i - 1; r >= 0 && l < r; r--) {
                while (arr[l] + arr[r] < num) {
                    l++;
                }
                if (l < r && arr[l] + arr[r] == num) {
                    dp[r][i] = Math.max(dp[l][r], 2) + 1;
                    ans = Math.max(ans, dp[r][i]);
                }
            }
        }
        return ans >= 3 ? ans : 0;
    }

    public int lenLongestFibSubseq(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][];
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i];
        }
        int maxLen = -2;
        for (int j = 1; j < n - 1; j++) {
            int i = 0, k = j + 1;
            while (i < j && k < n) {
                int sum = arr[i] + arr[j];
                if (sum < arr[k]) {
                    i++;
                } else if (sum > arr[k]) {
                    k++;
                } else {
                    maxLen = Math.max(maxLen, dp[k][j] = dp[j][i] + 1);
                    i++;
                    k++;
                }
            }
        }
        return maxLen + 2;
    }

//    public static void main(String[] args) {
//        Problem0873LengthOfLongestFibonacciSubsequence a = new Problem0873LengthOfLongestFibonacciSubsequence();
////        int[] arr = {2,4,5,6,7,8,11,13,14,15,21,22,34};
//        int[] arr = {1,2,3,4,5,6,7,8};
//        a.lenLongestFibSubseq(arr);
//    }
}
