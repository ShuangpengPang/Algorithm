package com.shuangpeng.Problem.p0801_0900;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: Problem0886PossibleBipartition（可能的二分法）
 * @Date 2022/10/16 5:26 PM
 * @Version 1.0
 */
public class Problem0886PossibleBipartition {

    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] graph = new List[n];
        Arrays.setAll(graph, i -> new ArrayList<>());
        for (int[] arr : dislikes) {
            int a = arr[0] - 1, b = arr[1] - 1;
            graph[a].add(b);
            graph[b].add(a);
        }
        int[] g = new int[n];
        for (int i = 0; i < n; i++) {
            if (g[i] != 0) {
                continue;
            }
            if (!check(graph, g, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean check(List<Integer>[] graph, int[] g, int x, int idx) {
        if (g[x] != 0 && g[x] != idx) {
            return false;
        }
        if (g[x] == idx) {
            return true;
        }
        g[x] = idx;
        for (int y : graph[x]) {
            if (!check(graph, g, y, 3 - idx)) {
                return false;
            }
        }
        return true;
    }
}
