package com.shuangpeng.competition.第251场周赛;

public class Problem1947 {

    public int maxCompatibilitySum0(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;
        int[][] array = new int[m][m];
        for (int s = 0; s < m; s++) {
            for (int t = 0; t < m; t++) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    if (students[s][i] == mentors[t][i]) {
                        sum++;
                    }
                }
                array[s][t] = sum;
            }
        }
        return dfs(array, 0, 0, 0);
    }

    private int dfs(int[][] array, int k, int sum, int status) {
        int m = array.length;
        if (k >= m) {
            return sum;
        }
        int maxValue = sum;
        for (int i = 0; i < m; i++) {
            int j = 1 << i;
            if ((status & j) == 0) {
                maxValue = Math.max(maxValue, dfs(array, k + 1, sum + array[k][m - i - 1], status | j));
            }
        }
        return maxValue;
    }

    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;
        int[][] array = new int[m][m];
        for (int s = 0; s < m; s++) {
            for (int t = 0; t < m; t++) {
                int sum = 0;
                for (int i = 0; i < n; i++) {
                    if (students[s][i] == mentors[t][i]) {
                        sum++;
                    }
                }
                array[s][t] = sum;
            }
        }
        int MAX = 1 << m;
        int[] dp = new int[MAX];
        for (int mask = 1; mask < MAX; mask++) {
            int s = Integer.bitCount(mask) - 1;
            for (int i = 0; i < m; i++) {
                if ((mask & (1 << i)) != 0) {
                    dp[mask] = Math.max(dp[mask], array[s][m - i - 1] + dp[mask ^ (1 << i)]);
                }
            }
        }
        return dp[MAX - 1];
    }
}
