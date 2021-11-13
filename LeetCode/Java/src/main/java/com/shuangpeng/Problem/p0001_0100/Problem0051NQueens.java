package com.shuangpeng.Problem.p0001_0100;

import java.util.*;

public class Problem0051NQueens {

    public List<List<String>> solveNQueens0(int n) {
        char[][] chars = new char[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(chars[i], '.');
        }
        List<List<String>> ans = new ArrayList<>();
        dfs(chars, 0, ans);
        return ans;
    }

    private void dfs(char[][] chars, int r, List<List<String>> ans) {
        int n = chars.length;
        if (r == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; ++i) {
                char[] copy = Arrays.copyOf(chars[i], chars.length);
                for (int j = 0; j < n; ++j) {
                    if (copy[j] != 'Q') {
                        copy[j] = '.';
                    }
                }
                String str = new String(copy);
                list.add(str);
            }
            ans.add(list);
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (chars[r][i] == '.') {
                chars[r][i] = 'Q';
                change(chars, r, i, '.', (char) ('0' + r));
                dfs(chars, r + 1, ans);
                change(chars, r, i, (char) ('0' + r), '.');
                chars[r][i] = '.';
            }
        }
    }

    private void change(char[][] chars, int i, int j, char o, char c) {
        int n = chars.length;
        for (int d = 1; i + d < n; ++d) {
            if (chars[i + d][j] == o) {
                chars[i + d][j] = c;
            }
            if (j - d >= 0 && chars[i + d][j - d] == o) {
                chars[i + d][j - d] = c;
            }
            if (j + d < n && chars[i + d][j + d] == o) {
                chars[i + d][j + d] = c;
            }
        }
    }

    public List<List<String>> solveNQueens1(int n) {
        List<List<String>> ans = new ArrayList<>();
        dfs(new int[n], 0, new HashSet<>(), new HashSet<>(), new HashSet<>(), ans);
        return ans;
    }

    private void dfs(int[] queue, int row, Set<Integer> columns, Set<Integer> diagonalSet1, Set<Integer> diagonalSet2, List<List<String>> ans) {
        int n = queue.length;
        if (row == n) {
            ans.add(generateBoard(queue));
            return;
        }
        for (int i = 0; i < n; ++i) {
            if (columns.contains(i)) {
                continue;
            }
            int diagonal1 = row - i;
            if (diagonalSet1.contains(diagonal1)) {
                continue;
            }
            int diagonal2 = row + i;
            if (diagonalSet2.contains(diagonal2)) {
                continue;
            }
            columns.add(i);
            diagonalSet1.add(diagonal1);
            diagonalSet2.add(diagonal2);
            queue[row] = i;
            dfs(queue, row + 1, columns, diagonalSet1, diagonalSet2, ans);
            queue[row] = -1;
            columns.remove(i);
            diagonalSet1.remove(diagonal1);
            diagonalSet2.remove(diagonal2);
        }
    }

    private List<String> generateBoard(int[] queue) {
        int n = queue.length;
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            char[] chars = new char[n];
            Arrays.fill(chars, '.');
            chars[queue[i]] = 'Q';
            ans.add(new String(chars));
        }
        return ans;
    }

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        dfs(new int[n], 0, 0, 0, 0, ans);
        return ans;
    }

    private void dfs(int[] queue, int row, int columns, int diagonal1, int diagonal2, List<List<String>> ans) {
        int n = queue.length;
        if (row == n) {
            ans.add(generateBoard(queue));
        }
        int mask = ((1 << n) - 1) & (~(columns | diagonal1 | diagonal2));
        while (mask != 0) {
            int position = mask & (-mask);
            mask = mask & (mask - 1);
            int col = Integer.bitCount(position - 1);
            queue[row] = col;
            dfs(queue, row + 1, columns | position, (diagonal1 | position) << 1, (diagonal2 | position) >> 1, ans);
            queue[row] = -1;
        }
    }
}
