package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3000MaximumAreaOfLongestDiagonalRectangle（对角线最长的矩形的面积）
 * @date 2024/4/16 4:53 PM
 */
public class Problem3000MaximumAreaOfLongestDiagonalRectangle {

    public int areaOfMaxDiagonal(int[][] dimensions) {
        int diagonal = 0, ans = 0;
        for (int[] r : dimensions) {
            int d = r[0] * r[0] + r[1] * r[1], a = r[0] * r[1];
            if (d > diagonal || d == diagonal && a > ans) {
                diagonal = d;
                ans = a;
            }
        }
        return ans;
    }
}
