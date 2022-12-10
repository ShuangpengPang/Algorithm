package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Description: Problem1691MaximumHeightByStackingCuboids（堆叠长方体的最大高度）
 * @Date 2022/9/22 5:28 PM
 * @Version 1.0
 */
public class Problem1691MaximumHeightByStackingCuboids {

    public int maxHeight0(int[][] cuboids) {
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (a, b) -> {
            if (a[2] != b[2]) {
                return b[2] - a[2];
            } else if (a[1] != b[1]) {
                return b[1] - a[1];
            }
            return b[0] - a[0];
        });
        int n = cuboids.length;
        int[][][] arr = new int[n][3][3];
        for (int i = 0; i < n; i++) {
            arr[i][0] = cuboids[i];
            int w = cuboids[i][0], l = cuboids[i][1], h = cuboids[i][2];
            arr[i][1] = new int[]{w, h, l};
            arr[i][2] = new int[]{l, h, w};
        }
        int[][] dp = new int[n][3];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int max = 0;
                for (int x = 0; x < i; x++) {
                    for (int y = 0; y < 3; y++) {
                        if (arr[x][y][0] >= arr[i][j][0] && arr[x][y][1] >= arr[i][j][1] && arr[x][y][2] >= arr[i][j][2]) {
                            max = Math.max(max, dp[x][y]);
                        }
                    }
                }
                dp[i][j] = max + arr[i][j][2];
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    public int maxHeight1(int[][] cuboids) {
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, (a, b) -> b[2] != a[2] ? b[2] - a[2] : (b[1] != a[1] ? b[1] - a[1] : b[0] - a[0]));
        int n = cuboids.length;
        int[][] dp = new int[n][3];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 3; j++) {
                int[] p1 = getPair(cuboids[i], j);
                int w = p1[0], l = p1[1], h = cuboids[i][j];
                dp[i][j] = h;
                for (int x = i - 1; x >= 0; x--) {
                    for (int y = 0; y < 3; y++) {
                        if (cuboids[x][y] >= h) {
                            int[] p2 = getPair(cuboids[x], y);
                            if (p2[0] >= w && p2[1] >= l) {
                                dp[i][j] = Math.max(dp[i][j], dp[x][y] + h);
                            }
                        }
                    }
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }
        return ans;
    }

    private int[] getPair(int[] c, int i) {
        int[] ans = new int[2];
        for (int j = 0, k = 0; j < 3; j++) {
            if (j != i) {
                ans[k++] = c[j];
            }
        }
        return ans;
    }

    public int maxHeight2(int[][] cuboids) {
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, (a, b) -> b[2] != a[2] ? b[2] - a[2] : (b[1] != a[1] ? b[1] - a[1] : b[0] - a[0]));
        int n = cuboids.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            int w = cuboids[i][0], l = cuboids[i][1], h = cuboids[i][2];
            dp[i] = h;
            for (int j = i - 1; j >= 0; j--) {
                if (cuboids[j][0] >= w && cuboids[j][1] >= l && cuboids[j][2] >= h) {
                    dp[i] = Math.max(dp[i], dp[j] + h);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }

    public int maxHeight(int[][] cuboids) {
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, Comparator.comparingInt(e -> e[0] + e[1] + e[2]));
        int n = cuboids.length;
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = cuboids[i][2];
            for (int j = 0; j < i; j++) {
                if (cuboids[j][0] <= cuboids[i][0] && cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2]) {
                    dp[i] = Math.max(dp[i], dp[j] + cuboids[i][2]);
                }
            }
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}

class Problem1691MaximumHeightByStackingCuboids0 {

    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] c : cuboids) {
            Arrays.sort(c);
        }
        Arrays.sort(cuboids, Comparator.comparingInt(e -> e[0] + e[1] + e[2]));
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(cuboids, -1, 0, memo);
    }

    private int dfs(int[][] cuboids, int top, int index, int[] memo) {
        if (index == cuboids.length) {
            return 0;
        }
        if (top != -1 && memo[top] != -1) {
            return memo[top];
        }
        int height = dfs(cuboids, top, index + 1, memo), h = cuboids[index][2];
        if (top == -1 || check(cuboids, top, index)) {
            height = Math.max(height, dfs(cuboids, index, index + 1, memo) + h);
        }
        if (top != -1) {
            memo[top] = height;
        }
        return height;
    }

    private boolean check(int[][] cuboids, int i, int j) {
        return cuboids[i][0] <= cuboids[j][0] && cuboids[i][1] <= cuboids[j][1] && cuboids[i][2] <= cuboids[j][2];
    }
}
