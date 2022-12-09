package com.shuangpeng.Problem.P2201_2300;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2272SubstringWithLargestVariance（最大波动的子字符串）
 * @date 2022/12/9 10:01 AM
 */
public class Problem2272SubstringWithLargestVariance {

    public int largestVariance(String s) {
        int n = s.length(), N = 26;
        int[][] diff = new int[N][N], diffWithB = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(diffWithB[i], -n);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < N; j++) {
                ++diff[c][j];
                ++diffWithB[c][j];
                diffWithB[j][c] = --diff[j][c];
                diff[j][c] = Math.max(diff[j][c], 0);
                ans = Math.max(ans, Math.max(diffWithB[c][j], diffWithB[j][c]));
            }
        }
        return ans;
    }
}
