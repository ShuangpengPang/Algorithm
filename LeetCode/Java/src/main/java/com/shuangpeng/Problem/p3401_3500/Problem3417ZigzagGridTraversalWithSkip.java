package com.shuangpeng.Problem.p3401_3500;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem3417ZigzagGridTraversalWithSkip（跳过交替单元格的之字形遍历）
 * @date 2025/3/12 15:56
 */
public class Problem3417ZigzagGridTraversalWithSkip {

    public List<Integer> zigzagTraversal(int[][] grid) {
        List<Integer> ans = new ArrayList<>();
        int m = grid.length, n = grid[0].length, b = n & 1;
        for (int i = 0; i < m; i++) {
            int s = 2 - ((i & 1) << 2);
            for (int j = (i & 1) * (n - 1 - b); j >= 0 && j < n; j += s) {
                ans.add(grid[i][j]);
            }
        }
        return ans;
    }
}
