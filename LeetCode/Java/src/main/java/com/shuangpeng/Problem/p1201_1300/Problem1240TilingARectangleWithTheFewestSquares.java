package com.shuangpeng.Problem.p1201_1300;

/**
 * @Description: Problem1240TilingARectangleWithTheFewestSquares（铺瓷砖）
 * @Date 2022/7/26 3:41 PM
 * @Version 1.0
 */
public class Problem1240TilingARectangleWithTheFewestSquares {

    int ans = 0;
    int n, m;
    boolean[][] cover;

    public int tilingRectangle(int n, int m) {
        ans = Math.max(m, n);
        this.n = n;
        this.m = m;
        cover = new boolean[n][m];
        dfs(0);
        return ans;
    }

    private void dfs(int res) {
        if (res >= ans) {
            return;
        }
        int i = 0, j = 0;
        while (i < n && j < m && cover[i][j]) {
            j++;
            if (j == m) {
                j = 0;
                i++;
            }
        }
        if (i == n) {
            ans = res;
            return;
        }
        for (int k = getMaxWidth(i, j); k > 0; k--) {
            setCover(i, j, k, true);
            dfs(res + 1);
            setCover(i, j, k, false);
        }
    }

    private int getMaxWidth(int i, int j) {
        int w = 1;
        while (i + w < n && j + w < m) {
            int r = i + w, c = j + w;
            for (int k = i; k <= r; k++) {
                if (cover[k][c]) {
                    return w;
                }
            }
            for (int k = j; k <= c; k++) {
                if (cover[r][k]) {
                    return w;
                }
            }
            w++;
        }
        return w;
    }

    private void setCover(int i, int j, int w, boolean value) {
        int r = i + w, c = j + w;
        for (int x = i; x < r; x++) {
            for (int y = j; y < c; y++) {
                cover[x][y] = value;
            }
        }
    }

//    public static void main(String[] args) {
//        Problem1240TilingARectangleWithTheFewestSquares a = new Problem1240TilingARectangleWithTheFewestSquares();
//        int res = a.tilingRectangle(43, 24);
//        int i = 1;
//    }
}
