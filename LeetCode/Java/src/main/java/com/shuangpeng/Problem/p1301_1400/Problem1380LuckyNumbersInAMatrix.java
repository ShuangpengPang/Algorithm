package com.shuangpeng.Problem.p1301_1400;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description:
 * @Date 2022/2/15 11:49 AM
 * @Version 1.0
 */
public class Problem1380LuckyNumbersInAMatrix {

    public List<Integer> luckyNumbers0(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] rows = new int[m];
        int[] cols = new int[n];
        Arrays.fill(rows, Integer.MAX_VALUE);
        Arrays.fill(cols, Integer.MIN_VALUE);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                rows[i] = Math.min(rows[i], matrix[i][j]);
                cols[j] = Math.max(cols[j], matrix[i][j]);
            }
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (matrix[i][j] == rows[i] && matrix[i][j] == cols[j]) {
                    ans.add(matrix[i][j]);
                }
            }
        }
        return ans;
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        List<Integer> ans = new ArrayList<>();
        for (int[] row : matrix) {
            int minValue = Integer.MAX_VALUE, idx = 0;
            for (int i = 0; i < n; ++i) {
                if (row[i] < minValue) {
                    idx = i;
                    minValue = row[i];
                }
            }
            boolean valid = true;
            for (int i = 0; i < m; ++i) {
                if (minValue < matrix[i][idx]) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                ans.add(minValue);
            }
        }
        return ans;
    }
}
