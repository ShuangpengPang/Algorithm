package com.shuangpeng.Problem.p1201_1300;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1222QueuesThatCanAttackTheKing（可以攻击国王的皇后）
 * @date 2023/6/13 12:09 PM
 */
public class Problem1222QueuesThatCanAttackTheKing {

    public List<List<Integer>> queensAttacktheKing0(int[][] queens, int[] king) {
        Set<Integer> set = new HashSet<>();
        for (int[] q : queens) {
            set.add(q[0] * 8 + q[1]);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (i != 0 || j != 0) {
                    for (int x = king[0] + i, y = king[1] + j; x >= 0 && x < 8 && y >= 0 && y < 8; x += i, y += j) {
                        if (set.contains(x * 8 + y)) {
                            ans.add(Arrays.asList(x, y));
                            break;
                        }
                    }
                }
            }
        }
        return ans;
    }

    public List<List<Integer>> queensAttacktheKing1(int[][] queens, int[] king) {
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

    public List<List<Integer>> queensAttacktheKing(int[][] queens, int[] king) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] q : queens) {
            int x = q[0] - king[0], y = q[1] - king[1];
            if (x == 0 || y == 0 || Math.abs(x) == Math.abs(y)) {
                int k = sign(x) * 10 + sign(y), d = Math.abs(x) + Math.abs(y);
                if (!map.containsKey(k) || map.get(k)[2] > d) {
                    map.put(k, new int[]{q[0], q[1], d});
                }
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int[] p : map.values()) {
            ans.add(Arrays.asList(p[0], p[1]));
        }
        return ans;
    }

    private int sign(int x) {
        return x == 0 ? 0 : x > 0 ? 1 : -1;
    }
}
