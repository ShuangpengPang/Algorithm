package com.shuangpeng.Problem.p0701_0800;

import java.util.*;

/**
 * @Description: 变为棋盘
 * @Date 2022/8/23 4:20 PM
 **/
public class Problem0782TransformToChessboard {

    public int movesToChessboard0(int[][] board) {
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

    public int movesToChessboard(int[][] board) {
        int n = board.length;
        int rowMask = 0, colMask = 0;
        for (int i = 0; i < n; i++) {
            rowMask |= board[0][i] << i;
            colMask |= board[i][0] << i;
        }
        if (Math.abs(Integer.bitCount(rowMask) * 2 - n) > 1 || Math.abs(Integer.bitCount(colMask) * 2 - n) > 1) {
            return -1;
        }
        int reverseRowMask = ((1 << n) - 1) ^ rowMask, reverseColMask = ((1 << n) - 1) ^ colMask;
        int rowCnt = 0, colCnt = 0;
        for (int i = 0; i < n; i++) {
            int curRow = 0, curCol = 0;
            for (int j = 0; j < n; j++) {
                curRow |= board[i][j] << j;
                curCol |= board[j][i] << j;
            }
            if (curRow != rowMask && curRow != reverseRowMask || curCol != colMask && curCol != reverseColMask) {
                return -1;
            }
            if (curRow == rowMask) {
                rowCnt++;
            }
            if (curCol == colMask) {
                colCnt++;
            }
        }
        if (Math.abs(rowCnt * 2 - n) > 1 || Math.abs(colCnt * 2 - n) > 1) {
            return -1;
        }
        return getCount(rowMask, n) + getCount(colMask, n);
    }

    private int getCount(int mask, int n) {
        int m1 = 0xAAAAAAAA, m2 = 0x55555555;
        int cnt = Integer.bitCount(mask);
        if ((n & 1) == 1) {
            if (cnt * 2 - n == 1) {
                return cnt - Integer.bitCount(mask & m2);
            }
            return cnt - Integer.bitCount(mask & m1);
        }
        return cnt - Math.max(Integer.bitCount(mask & m1), Integer.bitCount(mask & m2));
    }
}

