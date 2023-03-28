package com.shuangpeng.Problem.p1001_1100;

/**
 * @description: 最短公共超序列
 * @date 2023/3/28 12:07 PM
 **/
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

class Problem1092ShortestCommonSupersequence0 {

    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[][][] dp = new int[n1 + 1][n2 + 1][3];
        for (int i = 1; i <= n1; i++) {
            for (int j = 1; j <= n2; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j][0] = i;
                    dp[i][j][1] = j;
                    dp[i][j][2] = dp[i - 1][j - 1][2] + 1;
                } else if (dp[i - 1][j][2] >= dp[i][j - 1][2]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n1, j = n2; i >= 0; i--, j--) {
            int idx1 = dp[i][j][0], idx2 = dp[i][j][1];
            while (i > idx1) {
                sb.append(str1.charAt(--i));
            }
            while (j > idx2) {
                sb.append(str2.charAt(--j));
            }
            if (i > 0) {
                sb.append(str1.charAt(i - 1));
            }
        }
        return sb.reverse().toString();
    }
}

class Problem1092ShortestCommonSupersequence1 {

    public String shortestCommonSupersequence(String str1, String str2) {
        int n1 = str1.length(), n2 = str2.length();
        int[][] dp = new int[n1 + 1][n2 + 1];
        for (int i = 0; i < n1; i++) {
            dp[i][n2] = n1 - i;
        }
        for (int i = 0; i < n2; i++) {
            dp[n1][i] = n2 - i;
        }
        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                if (str1.charAt(i) == str2.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + 1;
                } else {
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i][j + 1]) + 1;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (str1.charAt(i) == str2.charAt(j)) {
                sb.append(str1.charAt(i));
                i++;
                j++;
            } else if (dp[i + 1][j] == dp[i][j] - 1) {
                sb.append(str1.charAt(i));
                i++;
            } else {
                sb.append(str2.charAt(j));
                j++;
            }
        }
        if (i < n1) {
            sb.append(str1.substring(i));
        } else {
            sb.append(str2.substring(j));
        }
        return sb.toString();
    }
}
