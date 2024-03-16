package com.shuangpeng.Problem.p3001_3100;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3070CountSubmatricesWithTopLeftElementAndSumLessThanK（元素和小于等于k的子矩阵的数目）
 * @date 2024/3/17 12:13 AM
 */
public class Problem3070CountSubmatricesWithTopLeftElementAndSumLessThanK {

    public int countSubmatrices(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        int[] arr = new int[n];
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0, sum = 0; j < n; j++) {
                sum += grid[i][j];
                arr[j] += sum;
                if (arr[j] <= k) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
