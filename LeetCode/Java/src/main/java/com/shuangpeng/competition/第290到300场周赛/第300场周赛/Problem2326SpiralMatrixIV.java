package com.shuangpeng.competition.第290到300场周赛.第300场周赛;

import com.shuangpeng.common.ListNode;

import java.util.Arrays;

/**
 * @Description: Problem2326SpiralMatrixIV（旋转矩阵IV）
 * @Date 2022/7/7 6:29 PM
 * @Version 1.0
 */
public class Problem2326SpiralMatrixIV {

    // 比赛时写法
    public int[][] spiralMatrix0(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        int d = 0;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        while (head != null) {
            visited[i][j] = true;
            ans[i][j] = head.val;
            int s = i, t = j;
            if (d == 0) {
                t++;
            } else if (d == 1) {
                s++;
            } else if (d == 2) {
                t--;
            } else {
                s--;
            }
            if (s < 0 || s >= m || t < 0 || t >= n || visited[s][t]) {
                d = (d + 1) % 4;
                if (d == 0) {
                    j++;
                } else if (d == 1) {
                    i++;
                } else if (d == 2) {
                    j--;
                } else {
                    i--;
                }
            } else {
                i = s;
                j = t;
            }
            head = head.next;
        }
        return ans;
    }

    public int[][] spiralMatrix1(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        int t = 0, b = m - 1, l = 0, r = n - 1;
        while (t <= b && l <= r) {
            for (int i = l; i <= r; i++) {
                head = setValue(ans, head, t, i);
            }
            t++;
            for (int i = t; i <= b; i++) {
                head = setValue(ans, head, i, r);
            }
            r--;
            for (int i = r; i >= l && t <= b; i--) {
                head = setValue(ans, head, b, i);
            }
            b--;
            for (int i = b; i >= t && l <= r; i--) {
                head = setValue(ans, head, i, l);
            }
            l++;
        }
        return ans;
    }

    private ListNode setValue(int[][] ans, ListNode head, int i, int j) {
        if (head == null) {
            ans[i][j] = -1;
            return null;
        }
        ans[i][j] = head.val;
        return head.next;
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int i = 0, j = 0;
        int d = 0;
        while (head != null) {
            ans[i][j] = head.val;
            head = head.next;
            int x = i + dirs[d][0], y = j + dirs[d][1];
            if (x < 0 || x >= m || y < 0 || y >= n || ans[x][y] != -1) {
                d = (d + 1) % 4;
            }
            i += dirs[d][0];
            j += dirs[d][1];
        }
        return ans;
    }
}
