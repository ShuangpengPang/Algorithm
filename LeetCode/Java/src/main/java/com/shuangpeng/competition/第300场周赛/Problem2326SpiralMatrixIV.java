package com.shuangpeng.competition.第300场周赛;

import com.shuangpeng.common.ListNode;

/**
 * @Description: Problem2326SpiralMatrixIV（旋转矩阵IV）
 * @Date 2022/7/7 6:29 PM
 * @Version 1.0
 */
public class Problem2326SpiralMatrixIV {

    public int[][] spiralMatrix(int m, int n, ListNode head) {
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
}
