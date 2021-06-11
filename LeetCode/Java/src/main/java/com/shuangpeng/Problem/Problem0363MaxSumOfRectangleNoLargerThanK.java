package com.shuangpeng.Problem;

import java.util.TreeSet;

public class Problem0363MaxSumOfRectangleNoLargerThanK {

    public int maxSumSubmatrix0(int[][] matrix, int k) {
        int ans = Integer.MIN_VALUE;
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; ++i) { // 枚举上边界
            int[] sum = new int[n];
            for (int j = i; j < m; ++j) { // 枚举下边界
                for (int c = 0; c < n; ++c) {
                    sum[c] += matrix[j][c]; // 更新每列的元素和
                }
                TreeSet<Integer> sumSet = new TreeSet<Integer>();
                sumSet.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceil = sumSet.ceiling(s - k);
                    if (ceil != null) {
                        ans = Math.max(ans, s - ceil);
                    }
                    sumSet.add(s);
                }
            }
        }
        return ans;
    }

    public int maxSumSubmatrix(int[][] matrix, int k) {
        int m = matrix.length, n = matrix[0].length;
        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < m; i++) {
            int[] sum = new int[n];
            for (int j = i; j < m; j++) {
                for (int c = 0; c < n; c++) {
                    sum[c] += matrix[j][c];
                }
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int s = 0;
                for (int v : sum) {
                    s += v;
                    Integer ceiling = set.ceiling(s - k);
                    if (ceiling != null) {
                        answer = Math.max(answer, s - ceiling);
                    }
                    set.add(s);
                }
            }
        }
        return answer;
    }
}
