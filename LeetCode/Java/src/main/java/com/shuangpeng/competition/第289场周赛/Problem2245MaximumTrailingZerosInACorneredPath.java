package com.shuangpeng.competition.第289场周赛;

/**
 * @Description: Problem2245MaximumTrailingZerosInACorneredPath（转角路径的乘积中最多能有几个尾随0）
 * @Date 2022/5/29 8:54 PM
 * @Version 1.0
 */
public class Problem2245MaximumTrailingZerosInACorneredPath {

    // 比赛时写法
    class Info {
        int two;
        int five;

        public Info(int t, int f) {
            this.two = t;
            this.five = f;
        }
    }

    public int maxTrailingZeros0(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Info[][] row = new Info[m][n];
        Info[][] col = new Info[m][n];
        for (int i = 0; i < m; ++i) {
            row[i][0] = getInfo(grid[i][0]);
            for (int j = 1; j < n; ++j) {
                Info info = getInfo(grid[i][j]);
                row[i][j] = new Info(row[i][j - 1].two + info.two, row[i][j - 1].five + info.five);
            }
        }
        for (int j = 0; j < n; ++j) {
            col[0][j] = getInfo(grid[0][j]);
            for (int i = 1; i < m; ++i) {
                Info info = getInfo(grid[i][j]);
                col[i][j] = new Info(col[i - 1][j].two + info.two, col[i - 1][j].five + info.five);
            }
        }
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                Info info = getInfo(grid[i][j]);
                int t1 = row[i][j].two + col[i][j].two - info.two;
                int f1 = row[i][j].five + col[i][j].five - info.five;
                int c1 = Math.min(t1, f1);

                int t2 = col[i][j].two + row[i][n - 1].two - row[i][j].two;
                int f2 = col[i][j].five + row[i][n - 1].five - row[i][j].five;
                int c2 = Math.min(t2, f2);

                int t3 = row[i][j].two + col[m - 1][j].two - col[i][j].two;
                int f3 = row[i][j].five + col[m - 1][j].five - col[i][j].five;
                int c3 = Math.min(t3, f3);

                int t4 = row[i][n - 1].two - row[i][j].two + col[m - 1][j].two - col[i][j].two + info.two;
                int f4 = row[i][n - 1].five - row[i][j].five + col[m - 1][j].five - col[i][j].five + info.five;
                int c4 = Math.min(t4, f4);

                int c = Math.max(Math.max(c1, c2), Math.max(c3, c4));
                ans = Math.max(ans, c);
            }
        }
        return ans;
    }

    private Info getInfo(int num) {
        int two = 0, five = 0;
        while (num > 0 && (num % 2) == 0) {
            ++two;
            num /= 2;
        }
        while (num > 0 && (num % 5) == 0) {
            ++five;
            num /= 5;
        }
        return new Info(two, five);
    }

    static int[][] c25 = new int[1001][2];

    static {
        for (int i = 2; i <= 1000; ++i) {
            if (i % 2 == 0) {
                c25[i][0] = c25[i / 2][0] + 1;
            }
            if (i % 5 == 0) {
                c25[i][1] = c25[i / 5][1] + 1;
            }
        }
    }

    public int maxTrailingZeros(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][][] s = new int[m][n][2];
        for (int i = 0; i < m; ++i) {
            s[i][0][0] = c25[grid[i][0]][0];
            s[i][0][1] = c25[grid[i][0]][1];
            for (int j = 1; j < n; ++j) {
                int num = grid[i][j];
                s[i][j][0] = s[i][j - 1][0] + c25[num][0];
                s[i][j][1] = s[i][j - 1][1] + c25[num][1];
            }
        }
        int ans = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0, s2 = 0, s5 = 0; i < m; ++i) {
                int num = grid[i][j];
                s2 += c25[num][0];
                s5 += c25[num][1];
                ans = Math.max(ans, Math.max(Math.min(s2 + s[i][j][0] - c25[num][0], s5 + s[i][j][1] - c25[num][1]),
                        Math.min(s2 + s[i][n - 1][0] - s[i][j][0], s5 + s[i][n - 1][1] - s[i][j][1])));
            }
            for (int i = m - 1, s2 = 0, s5 = 0; i >= 0; --i) {
                int num = grid[i][j];
                s2 += c25[num][0];
                s5 += c25[num][1];
                ans = Math.max(ans, Math.max(Math.min(s2 + s[i][j][0] - c25[num][0], s5 + s[i][j][1] - c25[num][1]),
                        Math.min(s2 + s[i][n - 1][0] - s[i][j][0], s5 + s[i][n - 1][1] - s[i][j][1])));
            }
        }
        return ans;
    }
}
