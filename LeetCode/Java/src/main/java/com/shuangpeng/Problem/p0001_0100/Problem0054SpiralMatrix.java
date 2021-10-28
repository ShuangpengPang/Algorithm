package com.shuangpeng.Problem.p0001_0100;

import java.util.ArrayList;
import java.util.List;

public class Problem0054SpiralMatrix {

//            [[1,2,3,4],
//            [5,6,7,8],
//            [9,10,11,12]]

//    public static void main(String[] args) {
//        Problem0054SpiralMatrix a = new Problem0054SpiralMatrix();
//        int[][] matrix = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
//        a.spiralOrder(matrix);
//    }

    public List<Integer> spiralOrder0(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minRow = 0;
        int maxRow = rows - 1;
        int minCol = 0;
        int maxCol = cols - 1;
        List<Integer> answer = new ArrayList<>(rows * cols);
        while (minRow <= maxRow) {
            int row = minRow;
            int col = minCol;
            for (row = minRow, col = minCol; col <= maxCol && minCol <= maxCol && minRow <= maxRow; col++) {
                answer.add(matrix[row][col]);
            }
            minRow++;
            for (row = minRow, col = maxCol; row <= maxRow && minCol <= maxCol && minRow <= maxRow; row++) {
                answer.add(matrix[row][col]);
            }
            maxCol--;
            for (row = maxRow, col = maxCol; col >= minCol && minCol <= maxCol && minRow <= maxRow; col--) {
                answer.add(matrix[row][col]);
            }
            maxRow--;
            for (row = maxRow, col = minCol; row >= minRow && minCol <= maxCol && minRow <= maxRow; row--) {
                answer.add(matrix[row][col]);
            }
            minCol++;
        }
        return answer;
    }

    public List<Integer> spiralOrder1(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        int total = rows * cols;
        List<Integer> answer = new ArrayList<>(total);
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int row = 0;
        int col = 0;
        while (answer.size() < total) {
            answer.add(matrix[row][col]);
            visited[row][col] = true;
            int newRow = row + direction[directionIndex][0];
            int newCol = col + direction[directionIndex][1];
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || visited[newRow][newCol]) {
                directionIndex = (directionIndex + 1) % 4;
                newRow = row + direction[directionIndex][0];
                newCol = col + direction[directionIndex][1];
            }
            row = newRow;
            col = newCol;
        }
        return answer;
    }

    public List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minRow = 0;
        int maxRow = rows - 1;
        int minCol = 0;
        int maxCol = cols - 1;
        List<Integer> answer = new ArrayList<>(rows * cols);
        int[][] direction = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int directionIndex = 0;
        int row = 0;
        int col = 0;
        while (minRow <= maxRow && minCol <= maxCol) {
            answer.add(matrix[row][col]);
            int newRow = row + direction[directionIndex][0];
            int newCol = col + direction[directionIndex][1];
            if (newRow < minRow || newRow > maxRow
                    || newCol < minCol || newCol > maxCol) {
                if (directionIndex == 0) {
                    minRow++;
                } else if (directionIndex == 1) {
                    maxCol--;
                } else if (directionIndex == 2) {
                    maxRow--;
                } else if (directionIndex == 3) {
                    minCol++;
                }
                directionIndex = (directionIndex + 1) % 4;
            }
            row += direction[directionIndex][0];
            col += direction[directionIndex][1];
        }
        return answer;
    }

    public List<Integer> spiralOrder3(int[][] matrix) {
        if (matrix == null || matrix.length == 0
                || matrix[0] == null || matrix[0].length == 0) {
            return new ArrayList<>();
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int minRow = 0;
        int maxRow = rows - 1;
        int minCol = 0;
        int maxCol = cols - 1;
        List<Integer> answer = new ArrayList<>(rows * cols);
        while (minRow <= maxRow && minCol <= maxCol) {
            int row = minRow;
            int col = minCol;
            for (row = minRow, col = minCol; col <= maxCol; col++) {
                answer.add(matrix[row][col]);
            }
            minRow++;
            for (row = minRow, col = maxCol; row <= maxRow; row++) {
                answer.add(matrix[row][col]);
            }
            maxCol--;
            if (minRow <= maxRow && minCol <= maxCol) {
                for (row = maxRow, col = maxCol; col >= minCol; col--) {
                    answer.add(matrix[row][col]);
                }
                maxRow--;
                for (row = maxRow, col = minCol; row >= minRow; row--) {
                    answer.add(matrix[row][col]);
                }
                minCol++;
            }
        }
        return answer;
    }

    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new ArrayList<>();
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int minRow = 0, maxRow = m - 1, minCol = 0, maxCol = n - 1;
        List<Integer> answer = new ArrayList<>(m * n);
        while (minRow <= maxRow && minCol <= maxCol) {
            int r = minRow;
            int c = minCol;
            for (; c <= maxCol; c++) {
                answer.add(matrix[r][c]);
            }
            minRow++;
            c = maxCol;
            r = minRow;
            if (minRow > maxRow) {
                break;
            }
            for (; r <= maxRow; r++) {
                answer.add(matrix[r][c]);
            }
            maxCol--;
            c = maxCol;
            r = maxRow;
            if (minCol > maxCol) {
                break;
            }
            for (; c >= minCol; c--) {
                answer.add(matrix[r][c]);
            }
            maxRow--;
            c = minCol;
            r = maxRow;
            if (minRow > maxRow) {
                break;
            }
            for (; r >= minRow; r--) {
                answer.add(matrix[r][c]);
            }
            minCol++;
        }
        return answer;
    }
}
