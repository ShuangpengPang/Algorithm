package com.shuangpeng.Problem.p1601_1700;

import java.util.Arrays;

/**
 * @Description: Problem1640CheckArrayFormationThroughConcatenation（是否连接形成数组）
 * @Date 2022/9/22 10:17 AM
 * @Version 1.0
 */
public class Problem1640CheckArrayFormationThroughConcatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        int[] map = new int[101];
        int n = arr.length;
        Arrays.fill(map, -1);
        for (int i = 0; i < n; i++) {
            map[arr[i]] = i;
        }
        for (int[] p : pieces) {
            int m = p.length;
            if (map[p[0]] == -1) {
                return false;
            }
            for (int i = 1; i < m; i++) {
                if (map[p[i]] != map[p[i - 1]] + 1) {
                    return false;
                }
            }
        }
        return true;
    }
}
