package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2672NumberOfAdjacentElementsWithTheSameColor（有相同颜色的相邻元素数目）
 * @date 2023/12/10 11:48 AM
 */
public class Problem2672NumberOfAdjacentElementsWithTheSameColor {

    public int[] colorTheArray(int n, int[][] queries) {
        int m = queries.length, cnt = 0;
        int[] colors = new int[n];
        int[] ans = new int[m];
        for (int i = 0; i < m; i++) {
            int idx = queries[i][0], c = getCount(colors, idx);
            colors[idx] = queries[i][1];
            cnt += getCount(colors, idx) - c;
            ans[i] = cnt;
        }
        return ans;
    }

    private int getCount(int[] colors, int idx) {
        int cnt = 0;
        if (idx < colors.length - 1 && colors[idx] != 0 && colors[idx] == colors[idx + 1]) {
            cnt++;
        }
        if (idx > 0 && colors[idx] != 0 && colors[idx - 1] == colors[idx]) {
            cnt++;
        }
        return cnt;
    }
}
