package com.shuangpeng.interview;

import java.util.HashSet;
import java.util.Set;

/**
 * @Description: Question0108SetZeroes（面试题01.08 零矩阵）
 * @Date 2022/10/4 3:45 PM
 * @Version 1.0
 */
public class Question0108SetZeroes {

    public void setZeroes(int[][] matrix) {
        Set<Integer> rows = new HashSet<>(), cols = new HashSet<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    rows.add(i);
                    cols.add(j);
                }
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rows.contains(i) || cols.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }
    }
}
