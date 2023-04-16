package com.shuangpeng.Problem.p1001_1100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1042FlowerPlantingWithNoAdjacent（不邻接植花）
 * @date 2023/4/16 12:05 AM
 */
public class Problem1042FlowerPlantingWithNoAdjacent {

    public int[] gardenNoAdj0(int n, int[][] paths) {
        boolean[][] color = new boolean[n][4];
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            if (ans[i] == 0) {
                dfs(color, g, i, ans);
            }
        }
        return ans;
    }

    private void dfs(boolean[][] color, List<Integer>[] g, int x, int[] ans) {
        int c = 0;
        for (int i = 0; i < 4; i++) {
            if (!color[x][i]) {
                c = i;
                color[x][i] = true;
                break;
            }
        }
        ans[x] = c + 1;
        for (int y : g[x]) {
            if (ans[y] == 0) {
                color[y][c] = true;
            }
        }
        for (int y : g[x]) {
            if (ans[y] == 0) {
                dfs(color, g, y, ans);
            }
        }
    }

    public int[] gardenNoAdj(int n, int[][] paths) {
        List<Integer>[] g = new List[n];
        Arrays.setAll(g, i -> new ArrayList<>());
        for (int[] p : paths) {
            int x = p[0] - 1, y = p[1] - 1;
            g[x].add(y);
            g[y].add(x);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            boolean[] color = new boolean[5];
            for (int j : g[i]) {
                color[ans[j]] = true;
            }
            for (int j = 1; j < 5; j++) {
                if (!color[j]) {
                    ans[i] = j;
                    break;
                }
            }
        }
        return ans;
    }
}
