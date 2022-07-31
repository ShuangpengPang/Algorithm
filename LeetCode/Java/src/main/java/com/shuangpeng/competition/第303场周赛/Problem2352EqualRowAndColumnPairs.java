package com.shuangpeng.competition.第303场周赛;

/**
 * @Description: Problem2352EqualRowAndColumnPairs（相等行列对）
 * @Date 2022/7/31 5:45 PM
 * @Version 1.0
 */
public class Problem2352EqualRowAndColumnPairs {

    public int equalPairs(int[][] grid) {
        int n = grid.length;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean valid = true;
                for (int k = 0; k < n; k++) {
                    if (grid[i][k] != grid[k][j]) {
                        valid = false;
                        break;
                    }
                }
                if (valid) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
