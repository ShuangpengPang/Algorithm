package com.shuangpeng.Problem;

public class Problem0059SpiralMatrixII {

    public int[][] generateMatrix0(int n) {
        if (n <= 0) {
            return null;
        }
        int minRow = 0;
        int maxRow = n - 1;
        int minCol = 0;
        int maxCol = n - 1;
        int row = 0;
        int col = 0;
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int index = 0;
        int number = 1;
        int[][] answer = new int[n][n];
        while (minRow <= maxRow && minCol <= maxCol) {
            answer[row][col] = number++;
            int newRow = row + direction[index][0];
            int newCol = col + direction[index][1];
            if (newRow < minRow || newRow > maxRow || newCol < minCol || newCol > maxCol) {
                if (index == 0) {
                    minRow++;
                } else if (index == 1) {
                    maxCol--;
                } else if (index == 2) {
                    maxRow--;
                } else if (index == 3) {
                    minCol++;
                }
                index = (index + 1) % 4;
            }
            row += direction[index][0];
            col += direction[index][1];
        }
        return answer;
    }

    public int[][] generateMatrix1(int n) {
        if (n <= 0) {
            return null;
        }
        int minRow = 0;
        int maxRow = n - 1;
        int minCol = 0;
        int maxCol = n - 1;
        int number = 1;
        int[][] answer = new int[n][n];
        while (minRow <= maxRow && minCol <= maxCol) {
            int row = minRow;
            int col = minCol;
            for (row = minRow, col = minCol; col <= maxCol; col++) {
                answer[row][col] = number++;
            }
            minRow++;
            for (row = minRow, col = maxCol; row <= maxRow; row++) {
                answer[row][col] = number++;
            }
            maxCol--;
            for (row = maxRow, col = maxCol; col >= minCol; col--) {
                answer[row][col] = number++;
            }
            maxRow--;
            for (row = maxRow, col = minCol; row >= minRow; row--) {
                answer[row][col] = number++;
            }
            minCol++;
        }
        return answer;
    }







































    public int[][] generateMatrix(int n) {
        if (n <= 0) {
            return new int[0][0];
        }
        int r = 0, c = 0, minRow = 0, maxRow = n - 1, minCol = 0, maxCol = n - 1;
        int i = 1;
        int[][] answer = new int[n][n];
        while (minRow <= maxRow && minCol <= maxCol) {
            for (r = minRow, c = minCol; c <= maxCol; c++) {
                answer[r][c] = i++;
            }
            minRow++;
            for (r = minRow, c = maxCol; r <= maxRow; r++) {
                answer[r][c] = i++;
            }
            maxCol--;
            if (minRow <= maxRow && minCol <= maxCol) {
                for (r = maxRow, c = maxCol; c >= minCol; c--) {
                    answer[r][c] = i++;
                }
                maxRow--;
                for (r = maxRow, c = minCol; r >= minRow; r--) {
                    answer[r][c] = i++;
                }
                minCol++;
            }
        }
        return answer;
    }
}
