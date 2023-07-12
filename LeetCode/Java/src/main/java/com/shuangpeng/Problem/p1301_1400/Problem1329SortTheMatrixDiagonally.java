package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1329SortTheMatrixDiagonally（将矩阵按对角线排序）
 * @date 2023/7/12 11:38 AM
 */
public class Problem1329SortTheMatrixDiagonally {

    public int[][] diagonalSort(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        List<Integer> list = new ArrayList<>(Math.max(m, n));
        int i = m - 1, j = 0;
        while (j < n) {
            list.clear();
            for (int x = i, y = j; x < m && y < n; x++, y++) {
                list.add(mat[x][y]);
            }
            list.sort(Comparator.comparingInt(a -> a));
            for (int x = i, y = j, k = 0; k < list.size(); x++, y++, k++) {
                mat[x][y] = list.get(k);
            }
            if (i > 0) {
                i--;
            } else {
                j++;
            }
        }
        return mat;
    }
}
