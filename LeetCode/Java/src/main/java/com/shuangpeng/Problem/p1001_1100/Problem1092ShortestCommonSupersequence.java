package com.shuangpeng.Problem.p1001_1100;

public class Problem1092ShortestCommonSupersequence {

    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        int[][][] parent = new int[n1 + 1][n2 + 1][2];
        for (int i = 0; i <= n2; ++i) {
            parent[0][i][0] = -1;
            parent[0][i][1] = -1;
        }
        for (int i = 0; i <= n1; ++i) {
            parent[i][0][0] = -1;
            parent[i][0][1] = -1;
        }
        for (int i = 1; i <= n1; ++i) {
            for (int j = 1; j <= n2; ++j) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    parent[i][j][0] = i - 1;
                    parent[i][j][1] = j - 1;
                } else {
                    int x = i, y = j - 1;
                    if (dp[i][j - 1] < dp[i - 1][j]) {
                        x = i - 1;
                        y = j;
                    }
                    dp[i][j] = dp[x][y];
                    parent[i][j][0] = parent[x][y][0];
                    parent[i][j][1] = parent[x][y][1];
                }
            }
        }
        int[][] common = getParent(parent, dp[n1][n2]);
        reverse(common);
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        for (int idx = 0; idx < common.length; ++idx) {
            int p1 = common[idx][0], p2 = common[idx][1];
            while (i < p1) {
                sb.append(str1.charAt(i));
                ++i;
            }
            while(j < p2) {
                sb.append(str2.charAt(j));
                ++j;
            }
            sb.append(str2.charAt(j));
            ++i;
            ++j;
        }
        while (i < n1) {
            sb.append(str1.charAt(i));
            ++i;
        }
        while (j < n2) {
            sb.append(str2.charAt(j));
            ++j;
        }
        return sb.toString();
    }

    private int[][] getParent(int[][][] parent, int count) {
        int[][] common = new int[count][2];
        int x = parent.length - 1, y = parent[0].length - 1, t = 0;
        while (parent[x][y][0] != -1) {
            common[t][0] = parent[x][y][0];
            common[t][1] = parent[x][y][1];
            x = common[t][0];
            y = common[t][1];
            ++t;
        }
        return common;
    }

    private void reverse(int[][] nums) {
        int i = 0, j = nums.length - 1;
        while (i < j) {
            int x = nums[i][0];
            int y = nums[i][1];
            nums[i][0] = nums[j][0];
            nums[i][1] = nums[j][1];
            nums[j][0] = x;
            nums[j][1] = y;
            ++i;
            --j;
        }
    }
}
