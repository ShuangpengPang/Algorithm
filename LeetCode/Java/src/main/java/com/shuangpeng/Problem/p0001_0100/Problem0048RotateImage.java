package com.shuangpeng.Problem.p0001_0100;

public class Problem0048RotateImage {

    public void rotate(int[][] matrix) {

//        [[1,2,3,4,5],[6,7,8,9,10],[11,12,13,14,15],[16,17,18,19,20],[21,22,23,24,25]]

        if (matrix == null || matrix.length == 1) {
            return;
        }
        int n = matrix.length;
        boolean[][] visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (!visited[i][j]) {
                    int[] coord1 = coordConvert(n, i, j);
                    int[] coord2 = coordConvert(n, coord1[0], coord1[1]);
                    int[] coord3 = coordConvert(n, coord2[0], coord2[1]);
                    swap(matrix, i, j, coord1[0], coord1[1]);
                    swap(matrix, i, j, coord2[0], coord2[1]);
                    swap(matrix, i, j, coord3[0], coord3[1]);
                    visited[i][j] = true;
                    visited[coord1[0]][coord1[1]] = true;
                    visited[coord2[0]][coord2[1]] = true;
                    visited[coord3[0]][coord3[1]] = true;
                }
            }
        }
    }

    public int[] coordConvert(int n, int i, int j) {
        return new int[]{j, n - i - 1};
    }

    public void swap(int[][] matrix, int i1, int j1, int i2, int j2) {
        if (i1 == i2 && j1 == j2) {
            return;
        }
        matrix[i1][j1] = matrix[i2][j2] - matrix[i1][j1];
        matrix[i2][j2] = matrix[i2][j2] - matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2] + matrix[i1][j1];
    }
}
