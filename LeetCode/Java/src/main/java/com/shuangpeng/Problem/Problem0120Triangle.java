package com.shuangpeng.Problem;

import java.util.List;

public class Problem0120Triangle {

    public int minimumTotal0(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 0; i < n - 1; i++) {
            List<Integer> list = triangle.get(i + 1);
            int[] temp = new int[i + 2];
            for (int j = 0; j <= i; j++) {
                if (j == 0) {
                    temp[j] = dp[j] + list.get(j);
                } else {
                    temp[j] = Math.min(dp[j - 1], dp[j]) + list.get(j);
                }
                if (j == i) {
                    temp[j + 1] = dp[j] + list.get(j + 1);
                }
            }
            dp = temp;
        }
        int min = dp[0];
        for (int i = 0; i < n; i++) {
            min = Math.min(min, dp[i]);
        }
        return min;
    }

    public int minimumTotal1(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] f = new int[n];
        f[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; ++i) {
            f[i] = f[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; --j) {
                f[j] = Math.min(f[j - 1], f[j]) + triangle.get(i).get(j);
            }
            f[0] += triangle.get(i).get(0);
        }
        int minTotal = f[0];
        for (int i = 1; i < n; ++i) {
            minTotal = Math.min(minTotal, f[i]);
        }
        return minTotal;
    }

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i - 1] + triangle.get(i).get(i);
            for (int j = i - 1; j > 0; j--) {
                dp[j] = Math.min(dp[j], dp[j - 1]) + triangle.get(i).get(j);
            }
            dp[0] += triangle.get(i).get(0);
        }
        int answer = dp[0];
        for (int i = 1; i < n; i++) {
            answer = Math.min(answer, dp[i]);
        }
        return answer;
    }
}
