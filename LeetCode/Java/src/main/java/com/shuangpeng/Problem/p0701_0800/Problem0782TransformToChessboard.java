package com.shuangpeng.Problem.p0701_0800;

import java.util.HashMap;
import java.util.Map;

public class Problem0782TransformToChessboard {

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        Map<Integer, Integer> count = new HashMap<>();
        for (int[] row : board) {
            int code = 0;
            for (int num : row) {
                code = (code << 1) + num;
            }
            count.put(code, count.getOrDefault(code, 0) + 1);
        }
        int c1 = analyzeCount(count, n);
        if (c1 == -1) {
            return -1;
        }
        count.clear();
        for (int i = 0; i < n; ++i) {
            int code = 0;
            for (int j = 0; j < n; ++j) {
                code = (code << 1) + board[j][i];
            }
            count.put(code, count.getOrDefault(code, 0) + 1);
        }
        int c2 = analyzeCount(count, n);
        return c2 != -1 ? c1 + c2 : -1;
    }

    private int analyzeCount(Map<Integer, Integer> count, int n) {
        if (count.keySet().size() != 2) {
            return -1;
        }
        int k1 = 0, c1 = 0, k2 = 0, c2 = 0;
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (idx == 0) {
                k1 = entry.getKey();
                c1 = entry.getValue();
            } else {
                k2 = entry.getKey();
                c2 = entry.getValue();
            }
            ++idx;
        }
        if (!(c1 == n >> 1 && c2 == (n + 1) >> 1) && !(c2 == n >> 1 && c1 == (n + 1) >> 1)) {
            return -1;
        }
        int b1 = Integer.bitCount(k1), b2 = Integer.bitCount(k2);
        if (!(b1 == n >> 1 && b2 == (n + 1) >> 1) && !(b2 == n >> 1 && b1 == (n + 1) >> 1)) {
            return -1;
        }
        int M = (1 << n) - 1;
        if ((k1 ^ k2) != M) {
            return -1;
        }
        int M1 = 0xAAAAAAAA & M, M2 = 0x55555555 & M;
        if ((n & 1) == 0) {
            return Math.min(Integer.bitCount(k1 ^ M1), Integer.bitCount(k1 ^ M2)) >> 1;
        }
        k1 = Integer.bitCount(k1) > Integer.bitCount(k2) ? k1 : k2;
        return Integer.bitCount(k1 ^ M2) >> 1;
    }
}
