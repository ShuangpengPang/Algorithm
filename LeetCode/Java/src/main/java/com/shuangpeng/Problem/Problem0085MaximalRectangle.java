package com.shuangpeng.Problem;

import java.util.Arrays;

public class Problem0085MaximalRectangle {

    // 暴力求解
    public int maximalRectangle0(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1' && max < (m - i) * (n - j)) {
                    max = Math.max(max, getMaximalRectangle(matrix, i, j));
                }
            }
        }
        return max;
    }

    public int getMaximalRectangle(char[][] matrix, int i, int j) {
        if (matrix[i][j] == '0') {
            return 0;
        }
        int maxRight = matrix[0].length;
        int maxDown = matrix.length;
        int max = 0;
        for (int m = i; m < maxDown; m++) {
            for (int n = j; n < maxRight; n++) {
                if (matrix[m][n] == '1') {
                    int r = m - i + 1;
                    int c = n - j + 1;
                    max = Math.max(max, r * c);
                } else {
                    maxRight = n;
                }
            }
            if (maxRight == j) {
                break;
            }
        }
        return max;
    }

    // 动态规划（暴力求解优化）
    public int maximalRectangle1(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] widths = new int[m][n];
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == '1') {
                widths[i][0] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '1') {
                    widths[i][j] = widths[i][j - 1] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    int minWidth = widths[i][j];
                    for (int k = 0; k <= i; k++) {
                        if (widths[i - k][j] == 0) {
                            break;
                        }
                        minWidth = Math.min(minWidth, widths[i - k][j]);
                        max = Math.max(max, minWidth * (k + 1));
                    }
                }
            }
        }
        return max;
    }

    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxHeight = new int[m][n];
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') {
                maxHeight[0][i] = 1;
            }
        }
        for (int i = 1; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    maxHeight[i][j] = maxHeight[i - 1][j] + 1;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < m; i++) {
            max = Math.max(max, getMaxRectangle(maxHeight[i]));
        }
        return max;
    }

    public int getMaxRectangle(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int[] stack = new int[heights.length + 1];
        int count = 0;
        stack[count++] = -1;
        int max = 0;
        for (int i = 0; i < heights.length; i++) {
            while (count > 1 && heights[stack[count - 1]] >= heights[i]) {
                max = Math.max(max, heights[stack[count - 1]] * (i - stack[count - 2] - 1));
                count--;
            }
            stack[count++] = i;
        }
        for (int i = 1; i < count; i++) {
            max = Math.max(max, heights[stack[i]] * (stack[count - 1] - stack[i - 1]));
        }
        return max;
    }

    public int maximalRectangle3(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[n];
        int[] lefts = new int[n];
        int[] rights = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            int right = n;
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    if (i == 0 || rights[j] == j) {
                        rights[j] = right;
                    } else {
                        rights[j] = Math.min(rights[j], right);
                    }
                } else {
                    rights[j] = j;
                    right = j;
                }
            }
            int left = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    // height
                    heights[j] = heights[j] + 1;
                    // left
                    if (lefts[j] == j + 1) {
                        lefts[j] = left;
                    } else {
                        lefts[j] = Math.max(left, lefts[j]);
                    }
                    max = Math.max(max, heights[j] * (rights[j] - lefts[j]));
                } else {
                    heights[j] = 0;
                    lefts[j] = j + 1;
                    left = j + 1;
                }
            }
        }
        return max;
    }

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;

        int[] left = new int[n]; // initialize left as the leftmost boundary possible
        int[] right = new int[n];
        int[] height = new int[n];

        Arrays.fill(right, n); // initialize right as the rightmost boundary possible

        int maxarea = 0;
        for (int i = 0; i < m; i++) {
            int cur_left = 0, cur_right = n;
            // update height
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            // update left
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else {
                    left[j] = 0;
                    cur_left = j + 1;
                }
            }
            // update right
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else {
                    right[j] = n;
                    cur_right = j;
                }
            }
            // update area
            for (int j = 0; j < n; j++) {
                maxarea = Math.max(maxarea, (right[j] - left[j]) * height[j]);
            }
        }
        return maxarea;
    }

//    public static void main(String[] args) {
//        char[][] matrix = {
//                {'0','1','1','0','0','1','0','1','0','1'},
//                {'0','0','1','0','1','0','1','0','1','0'},
//                {'1','0','0','0','0','1','0','1','1','0'},
//
//                {'0','1','1','1','1','1','1','0','1','0'},
//                {'0','0','1','1','1','1','1','1','1','0'},
//
//                {'1','1','0','1','0','1','1','1','1','0'},
//                {'0','0','0','1','1','0','0','0','1','0'},
//                {'1','1','0','1','1','0','0','1','1','1'},
//                {'0','1','0','1','1','0','1','0','1','1'}};
//
//        Problem0085MaximalRectangle a = new Problem0085MaximalRectangle();
//        int i = a.maximalRectangle(matrix);
//        int j = i;
//    }
}
