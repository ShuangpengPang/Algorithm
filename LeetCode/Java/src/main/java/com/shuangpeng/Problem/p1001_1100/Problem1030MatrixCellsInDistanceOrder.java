package com.shuangpeng.Problem.p1001_1100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1030MatrixCellsInDistanceOrder（距离顺序排列矩阵单元格）
 * @date 2024/1/6 4:47 PM
 */
public class Problem1030MatrixCellsInDistanceOrder {

    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        int n = rows * cols;
        int[][] ans = new int[n][2];
        int index = 0;
        for (int d = 0; index < n; d++) {
            for (int r = rCenter - d; r <= rCenter + d; r++) {
                if (r >= 0 && r < rows) {
                    int len = d - Math.abs(r - rCenter), c1 = cCenter - len, c2 = cCenter + len;
                    if (c1 >= 0 && c1 < cols) {
                        ans[index][0] = r;
                        ans[index][1] = c1;
                        index++;
                    }
                    if (c1 != c2 && c2 >= 0 && c2 < cols) {
                        ans[index][0] = r;
                        ans[index][1] = c2;
                        index++;
                    }
                }
            }
        }
        return ans;
    }
}
