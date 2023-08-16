package com.shuangpeng.Problem.p2601_2700;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2661FirstCompletelyPaintedRowOrColumn（找出叠涂元素）
 * @date 2023/8/16 10:19 AM
 */
public class Problem2661FirstCompletelyPaintedRowOrColumn {

    public int firstCompleteIndex(int[] arr, int[][] mat) {
        int N = arr.length, m = mat.length, n = mat[0].length;
        int[][] map = new int[N][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int num = mat[i][j] - 1;
                map[num][0] = i;
                map[num][1] = j;
            }
        }
        int[] row = new int[m], col = new int[n];
        for (int i = 0; i < N; i++) {
            int num = arr[i] - 1;
            int r = map[num][0], c = map[num][1];
            if (++row[r] == n || ++col[c] == m) {
                return i;
            }
        }
        return 0;
    }
}
