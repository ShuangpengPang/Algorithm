package com.shuangpeng.Problem.p2901_3000;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem2924FindChampionII（找到冠军II）
 * @date 2024/1/5 11:40 PM
 */
public class Problem2924FindChampionII {

    public int findChampion(int n, int[][] edges) {
        boolean[] visited = new boolean[n];
        int cnt = n;
        for (int[] e : edges) {
            if (!visited[e[1]]) {
                cnt--;
                visited[e[1]] = true;
            }
        }
        if (cnt != 1) {
            return -1;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return i;
            }
        }
        return 0;
    }
}
