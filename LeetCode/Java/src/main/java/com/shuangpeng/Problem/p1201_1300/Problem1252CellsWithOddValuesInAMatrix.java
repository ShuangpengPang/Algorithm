package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1252CellsWithOddValuesInAMatrix（奇数值单元格的数目）
 * @Date 2022/7/12 9:52 AM
 * @Version 1.0
 */
public class Problem1252CellsWithOddValuesInAMatrix {

    public int oddCells0(int m, int n, int[][] indices) {
        boolean[] rows = new boolean[m], cols = new boolean[n];
        for (int[] index : indices) {
            rows[index[0]] = !rows[index[0]];
            cols[index[1]] = !cols[index[1]];
        }
        int cnt1 = 0, cnt2 = 0;
        for (int i = 0; i < m; i++) {
            if (rows[i]) {
                cnt1++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (cols[i]) {
                cnt2++;
            }
        }
        return cnt1 * n + cnt2 * m - cnt1 * cnt2 * 2;
    }

    public int oddCells(int m, int n, int[][] indices) {
        long row = 0, col = 0;
        for (int[] index : indices) {
            row ^= 1L << index[0];
            col ^= 1L << index[1];
        }
        int a = 0, b = 0;
        for (int i = 0; i < m; i++) {
            if (((row >> i) & 1) == 1) {
                a++;
            }
        }
        for (int i = 0; i < n; i++) {
            if (((col >> i) & 1) == 1) {
                b++;
            }
        }
        return a * (n - b) + b * (m - a);
    }
}
