package com.shuangpeng.Problem.p1001_1100;

import java.util.Arrays;

/**
 * @author ShuangPengPang
 * @version 1.0
 * @description: Problem1079LetterTilePossibilities（活字印刷）
 * @date 2023/5/18 4:39 PM
 */
public class Problem1079LetterTilePossibilities {

    char[] cs;
    boolean[] visited;
    int n;
    int count;

    public int numTilePossibilities(String tiles) {
        cs = tiles.toCharArray();
        n = cs.length;
        visited = new boolean[n];
        Arrays.sort(cs);
        count = 0;
        for (int i = 1; i <= n; i++) {
            dfs(0, new char[i]);
        }
        return count;
    }

    private void dfs(int index, char[] ans) {
        if (index == ans.length) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || (i > 0 && !visited[i - 1] && cs[i] == cs[i - 1])) {
                continue;
            }
            visited[i] = true;
            ans[index] = cs[i];
            dfs(index + 1, ans);
            visited[i] = false;
        }
    }
}
