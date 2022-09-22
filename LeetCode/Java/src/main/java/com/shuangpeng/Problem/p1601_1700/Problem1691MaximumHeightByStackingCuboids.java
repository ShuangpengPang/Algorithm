package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1691MaximumHeightByStackingCuboids（堆叠长方体的最大高度）
 * @Date 2022/9/22 5:28 PM
 * @Version 1.0
 */
public class Problem1691MaximumHeightByStackingCuboids {

    public int maxHeight(int[][] cuboids) {
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
}
