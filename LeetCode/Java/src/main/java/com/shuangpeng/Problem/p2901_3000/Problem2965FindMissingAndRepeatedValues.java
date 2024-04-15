package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2965FindMissingAndRepeatedValues（找出缺失和重复的数字）
 * @date 2024/4/15 4:47 PM
 */
public class Problem2965FindMissingAndRepeatedValues {

    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length, N = n * n;
        boolean[] visited = new boolean[N + 1];
        int a = 0, sum = 0;
        for (int[] g : grid) {
            for (int num : g) {
                if (visited[num]) {
                    a = num;
                } else {
                    visited[num] = true;
                }
                sum += num;
            }
        }
        return new int[]{a, a + ((N + 1) * N >> 1) - sum};
    }
}
