package com.shuangpeng.Problem.p1101_1200;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1138AlphabetBoardPath（字母板上的路径）
 * @date 2023/5/20 3:09 PM
 */
public class Problem1138AlphabetBoardPath {

    static int N = 26;
    static String[][] step = new String[N][N];
    static {
        Arrays.setAll(step, i -> new String[N]);
        for (int i = 0; i < N - 1; i++) {
            int x1 = i / 5, y1 = i % 5;
            for (int j = 0; j < N - 1; j++) {
                int x2 = j / 5, y2 = j % 5;
                int n1 = Math.abs(x1 - x2), n2 = Math.abs(y1 - y2), n = n1 + n2;
                char[] cs = new char[n];
                char v = x1 < x2 ? 'D' : 'U', h = y1 < y2 ? 'R' : 'L';
                for (int k = 0; k < n1; k++) {
                    cs[k] = v;
                }
                for (int k = n1; k < n; k++) {
                    cs[k] = h;
                }
                step[i][j] = new String(cs);
            }
        }
        step[N - 1][N - 1] = "";
        for (int i = 0; i < N - 1; i++) {
            step[i][N - 1] = step[i][N - 6] + 'D';
            step[N - 1][i] = 'U' + step[N - 6][i];
        }
    }

    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        int n = target.length();
        for (int i = 0, j = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            sb.append(step[j][c]).append('!');
            j = c;
        }
        return sb.toString();
    }
}
