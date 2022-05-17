package com.shuangpeng.Problem.p1001_1100;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: Problem1074NumberOfSubmatricesThatSumToTarget（元素和为目标值的子矩阵数量）
 * @Date 2022/5/17 7:03 PM
 * @Version 1.0
 */
public class Problem1074NumberOfSubmatricesThatSumToTarget {

    // x1, y1   x2, y2
    // (x1 - 1, y2)   (x2, y1 - 1)
    // F(x2, y2) - F(x1 - 1, y2) - F(x2, y1 - 1) + F(x1 - 1, y1 - 1) = target
    // F(x1 - 1, y1 - 1) - F(x1 - 1, y2) + F(x2, y2) - F(x2, y1 - 1) = target
    // F(x2, y2) - F(x2, y1 - 1) - (F(x1 - 1, y2) - F(x1 - 1, y1 - 1)) = target

    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int[][] preSum = new int[m + 1][n + 1];
        for (int i = 1; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                preSum[i][j] = preSum[i][j - 1] + preSum[i - 1][j] + matrix[i - 1][j - 1] - preSum[i - 1][j - 1];
            }
        }
        int ans = 0;
        for (int r = 1; r <= n; ++r) {
            for (int l = 0; l < r; ++l) {
                Map<Integer, Integer> map = new HashMap<>(m + 1);
                map.put(0, 1);
                for (int i = 1; i <= m; ++i) {
                    int sum = preSum[i][r] - preSum[i][l];
                    ans += map.getOrDefault(sum - target, 0);
                    map.put(sum, map.getOrDefault(sum, 0) + 1);
                }
            }
        }
        return ans;
    }
}
