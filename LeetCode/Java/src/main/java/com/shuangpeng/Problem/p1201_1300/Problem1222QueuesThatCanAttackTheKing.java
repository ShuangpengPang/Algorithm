package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1222QueuesThatCanAttackTheKing（可以攻击国王的皇后）
 * @date 2023/6/13 12:09 PM
 */
public class Problem1222QueuesThatCanAttackTheKing {

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        int N = 8;
        boolean[][] board = new boolean[N][N];
        for (int[] q : queens) {
            board[q[0]][q[1]] = true;
        }
        int a = king[0], b = king[1];
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i != 0 || j != 0) {
                    for (int x = a + i, y = b + j; x >= 0 && x < N && y >= 0 && y < N; x += i, y += j) {
                        if (board[x][y]) {
                            ans.add(Arrays.asList(x, y));
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }
}
