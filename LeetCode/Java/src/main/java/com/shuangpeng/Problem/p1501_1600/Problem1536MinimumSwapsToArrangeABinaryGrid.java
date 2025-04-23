package com.shuangpeng.Problem.p1501_1600;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1536MinimumSwapsToArrangeABinaryGrid（排布二进制网格的最少交换次数）
 * @date 2025/4/23 14:23
 */
public class Problem1536MinimumSwapsToArrangeABinaryGrid {

    public int minSwaps(int[][] grid) {
        int n = grid.length;
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            int j = n - 1;
            while (j >= 0 && grid[i][j] == 0) {
                j--;
            }
            a[i] = j;
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int j = i;
            while (j < n && a[j] > i) {
                j++;
            }
            if (j == n) {
                return -1;
            }
            for (int k = j; k > i; k--) {
                a[k] = a[k - 1];
            }
            ans += j - i;
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Problem1536MinimumSwapsToArrangeABinaryGrid a = new Problem1536MinimumSwapsToArrangeABinaryGrid();
//        int[][] grid = {{0,0,1},{1,1,0},{1,0,0}};
//        a.minSwaps(grid);
//    }
}
