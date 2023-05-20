package com.shuangpeng.Problem.p1101_1200;

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
        for (int i = 0; i < N; i++) {
            int x1 = i / 5, y1 = i % 5;
            for (int j = 0; j < N; j++) {
                int x2 = j / 5, y2 = j % 5;
                int n1 = Math.abs(x1 - x2), n2 = Math.abs(y1 - y2), n = n1 + n2 + 1;
                char[] cs = new char[n];
                cs[n - 1] = '!';
                int idx = 0;
                for (int k = 0; k < y1 - y2; k++) {
                    cs[idx++] = 'L';
                }
                for (int k = 0; k < x1 - x2; k++) {
                    cs[idx++] = 'U';
                }
                for (int k = 0; k < y2 - y1; k++) {
                    cs[idx++] = 'R';
                }
                for (int k = 0; k < x2 - x1; k++) {
                    cs[idx++] = 'D';
                }
                step[i][j] = new String(cs);
            }
        }
    }

    public String alphabetBoardPath0(String target) {
        StringBuilder sb = new StringBuilder();
        int n = target.length();
        for (int i = 0, j = 0; i < n; i++) {
            int c = target.charAt(i) - 'a';
            sb.append(step[j][c]);
            j = c;
        }
        return sb.toString();
    }

    public String alphabetBoardPath(String target) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0, x = 0, y = 0; i < target.length(); i++) {
            int num = target.charAt(i) - 'a';
            int nx = num / 5, ny = num % 5;
            while (y > ny) {
                sb.append('L');
                y--;
            }
            while (x > nx) {
                sb.append('U');
                x--;
            }
            while (y < ny) {
                sb.append('R');
                y++;
            }
            while (x < nx) {
                sb.append('D');
                x++;
            }
            sb.append('!');
        }
        return sb.toString();
    }
}
