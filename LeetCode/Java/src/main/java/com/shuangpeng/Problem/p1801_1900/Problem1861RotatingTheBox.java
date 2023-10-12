package com.shuangpeng.Problem.p1801_1900;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1861RotatingTheBox（旋转盒子）
 * @date 2023/10/12 3:13 PM
 */
public class Problem1861RotatingTheBox {

    public char[][] rotateTheBox0(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(ans[i], '.');
        }
        for (int i = m - 1, c = 0; i >= 0; i--, c++) {
            for (int j = n - 1, k = n - 1; j >= 0; j--) {
                char ch = box[i][j];
                if (ch == '#') {
                    ans[k--][c] = ch;
                } else if (ch == '*') {
                    ans[j][c] = ch;
                    k = j - 1;
                }
            }
        }
        return ans;
    }

    public char[][] rotateTheBox(char[][] box) {
        int m = box.length, n = box[0].length;
        char[][] ans = new char[n][m];
        for (int i = 0, c = m - 1; i < m; i++, c--) {
            for (int j = 0; j < n; j++) {
                ans[j][c] = box[i][j];
            }
        }
        for (int j = 0; j < m; j++) {
            for (int i = n - 1, k = n - 1; i >= 0; i--) {
                char c = ans[i][j];
                if (c == '#') {
                    ans[i][j] = '.';
                    ans[k--][j] = c;
                } else if (c == '*') {
                    k = i - 1;
                }
            }
        }
        return ans;
    }
}
